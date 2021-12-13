package com.example.instagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int mainActivityRequest = 100;
    private EditText userData;
    private EditText password;
    private Button logIn;
    private TextView clickOnRegister;
    public DAO dao;
    private FirebaseDatabase database;
    private DatabaseReference refToUserTb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = Database.getDatabase(MainActivity.this).dao();

        database = FirebaseDatabase.getInstance();
        refToUserTb = database.getReference("Users");

        userData = findViewById(R.id.userData);
        password = findViewById(R.id.userPassword);
        logIn = (findViewById(R.id.LogIn_button));

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                Log.v("users", dao.users().toString());
            }
        });


        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String usTxt = userData.getText().toString();
                String psTxt = password.getText().toString();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (!usTxt.isEmpty() && !psTxt.isEmpty()) {
                            User user = dao.checkUser(usTxt);
                            if(user!=null){
                                if (user.getPassword().compareTo(psTxt)==0) {
                                    Intent intent = new Intent(MainActivity.this, FluxInstagram.class);
                                    intent.putExtra("username", usTxt);
                                    startActivity(intent);
                                }
                                else {
                                    MainActivity.this.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            password.setError("Parola nu este corecta.");
                                        }
                                    });


                                }
                            }
                            else {

                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        userData.setError("Utilizatorul nu exista");
                                    }
                                });
                            }
                        } else {
                            if (usTxt.isEmpty()) {
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        userData.setError("Introduceti numele de utilizator");
                                    }
                                });

                            }
                            if (psTxt.isEmpty()) {
                                MainActivity.this.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        password.setError("Introduceti parola");
                                    }
                                });

                            }
                        }
                    }

                }).start();



            }
        });

        clickOnRegister = (findViewById(R.id.toRegisterPage));
        clickOnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivityForResult(intent,mainActivityRequest);
            }
        });

        readFromDatabase();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==mainActivityRequest){
            if(resultCode==RESULT_OK){
                if(data!=null){
                    Bundle bundle = data.getBundleExtra("raspuns");
                    User user = (User)  bundle.getSerializable("cont");

                    refToUserTb.push().setValue(user);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            dao.insertUser(user);
                        }
                    }).start();

                    userData.setText(user.getUserData());
                    password.setText(user.getPassword());
                }
            }
        }
    }

    private void readFromDatabase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myReftoUsers = database.getReference("Users"), myRefToPosts = database.getReference("Posts");
        myReftoUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();
                for(DataSnapshot dsp : dataSnapshot.getChildren())
                    list.add(String.valueOf(dsp.getValue()));
                Log.d("usersFromFireBase", list.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("cancelled", "Failed to read value.", error.toException());
            }
        });


        myRefToPosts.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<String> list = new ArrayList<>();
                for(DataSnapshot dsp : dataSnapshot.getChildren())
                    list.add(String.valueOf(dsp.getValue()));
                Log.d("postsFromFireBase", list.toString());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("cancelled", "Failed to read value.", error.toException());
            }
        });
    }

}