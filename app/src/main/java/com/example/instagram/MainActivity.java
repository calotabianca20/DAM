package com.example.instagram;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    private final int mainActivityRequest = 100;
    private EditText userData;
    private EditText password;
    private Button logIn;
    private TextView clickOnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userData = findViewById(R.id.userData);
        password = findViewById(R.id.userPassword);
        logIn = (findViewById(R.id.LogIn_button));
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Conectat cu succes.", Toast.LENGTH_SHORT).show();
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
                    Cont cont  = (Cont)  bundle.getSerializable("cont");
                    userData.setText(cont.getUserData());
                    password.setText(cont.getPassword());
                }
            }
        }
    }
}