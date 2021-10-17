package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logIn = (findViewById(R.id.LogIn_button));
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Conectat cu succes.", Toast.LENGTH_SHORT).show();
            }
        });

        TextView clickOnRegister = (findViewById(R.id.toRegisterPage));
        clickOnRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerPage=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(registerPage);

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.v("lifecycle","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("lifecycle","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.v("lifecycle","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v("lifecycle","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v("lifecycle","onDestroy");
    }
}