package com.example.instagram;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(MainActivity2.this, "Inscriere!", Toast.LENGTH_SHORT).show();

        TextView login = (findViewById(R.id.LogIn_Reg));
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerPage=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(registerPage);

            }
        });
    }
}
