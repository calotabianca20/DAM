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

import java.io.Serializable;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final int mainActivityRequest = 100;
    private EditText userData;
    private EditText password;
    private Button logIn;
    private TextView clickOnRegister;
    public DAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = Database.getDatabase(MainActivity.this).dao();

        userData = findViewById(R.id.userData);
        password = findViewById(R.id.userPassword);
        logIn = (findViewById(R.id.LogIn_button));

//        Log.v("users", dao.users().toString());

        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String usTxt = userData.getText().toString();
                String psTxt = password.getText().toString();
                User user = dao.checkUser(usTxt);
                if(user!=null) {
                    if (!usTxt.isEmpty() && !psTxt.isEmpty()) {
                        if (user.getPassword().compareTo(psTxt)==0) {
                            Intent intent = new Intent(MainActivity.this, FluxInstagram.class);
                            intent.putExtra("username", usTxt);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Parola nu este corecta.", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        if (usTxt.isEmpty()) {
                            userData.setError("Introduceti numele de utilizator");
                        }
                        if (psTxt.isEmpty()) {
                            password.setError("Introduceti parola");
                        }
                    }
                }
                else {
                    Toast.makeText(MainActivity.this, "Utilizatorul nu exista", Toast.LENGTH_SHORT).show();
                }
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
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==mainActivityRequest){
            if(resultCode==RESULT_OK){
                if(data!=null){
                    Bundle bundle = data.getBundleExtra("raspuns");
                    User user = (User)  bundle.getSerializable("cont");
                    dao.insertUser(user);
                    userData.setText(user.getUserData());
                    password.setText(user.getPassword());
                }
            }
        }
    }


}