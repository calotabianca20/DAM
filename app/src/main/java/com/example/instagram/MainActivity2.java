package com.example.instagram;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;
public class MainActivity2 extends AppCompatActivity {
    private EditText et_userName;
    private EditText et_fullName;
    private EditText et_userData;
    private EditText et_password;
    private Button forwardButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toast.makeText(MainActivity2.this, "Inscriere!", Toast.LENGTH_SHORT).show();
        TextView login = (findViewById(R.id.logIn_Reg));
        et_userData = (findViewById(R.id.userDataReg));
        et_password = (findViewById(R.id.userPasswordReg));
        et_fullName = (findViewById(R.id.fullNameReg));
        et_userName = (findViewById(R.id.usernameReg));
        forwardButton = (findViewById(R.id.forwardButton));
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent registerPage=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(registerPage);
            }
        });
        forwardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()){
                   Cont cont = new Cont();
                   cont.setUserData(et_userData.getText().toString());
                   cont.setUserName(et_userName.getText().toString());
                   cont.setFullName(et_fullName.getText().toString());
                   cont.setPassword(et_password.getText().toString());
                   Toast.makeText(MainActivity2.this, cont.toString(), Toast.LENGTH_SHORT).show();
                   Bundle bundle = new Bundle();
                   bundle.putSerializable("cont", cont);
                   Intent intent = new Intent();
                   intent.putExtra("raspuns",bundle);
                   setResult(RESULT_OK,intent);
                   finish();
            }
                else { Toast.makeText(MainActivity2.this, "Datele nu sunt introduse corect", Toast.LENGTH_SHORT).show();}

            }
        });
    }

    private boolean isValid(){
        if(et_userData.getText().toString().isEmpty()){
            Toast.makeText(MainActivity2.this, "Introduceti email sau numar de telefon", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!et_userData.getText().toString().matches("^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$")){
            String maybeANumber = et_userData.getText().toString();
            if(maybeANumber.charAt(0)!='0'){
                Toast.makeText(MainActivity2.this, "Introduceti email sau numar de telefon", Toast.LENGTH_SHORT).show();
                return false;
            }
            else{
                if(maybeANumber.charAt(1)!='7'&& maybeANumber.charAt(1)!='2'&& maybeANumber.charAt(1)!='3'){
                    Toast.makeText(MainActivity2.this, "Introduceti email sau numar de telefon", Toast.LENGTH_SHORT).show();
                    return false;
                }
                else if(maybeANumber.charAt(2)!='2'&& maybeANumber.charAt(2)!='3'&& maybeANumber.charAt(2)!='4'&&
                        maybeANumber.charAt(2)!='5'&& maybeANumber.charAt(2)!='7'&& maybeANumber.charAt(2)!='8'){
                    Toast.makeText(MainActivity2.this, "Introduceti email sau numar de telefon", Toast.LENGTH_SHORT).show();
                    return false;
                }

                else if(maybeANumber.length()!=10){
                    Toast.makeText(MainActivity2.this, "Introduceti email sau numar de telefon", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }
        }
        if(et_fullName.getText().toString().isEmpty()){
            Toast.makeText(MainActivity2.this, "Introduceti numele complet", Toast.LENGTH_SHORT).show();
            return false;}
        else if(!et_fullName.getText().toString().matches("^[a-zA-Z\\s]*$")){
            Toast.makeText(MainActivity2.this, "\"Introduceti numele complet\"", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_userName.getText().toString().isEmpty()){
            Toast.makeText(MainActivity2.this, "Introduceti nume de utilizator", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(!et_userData.getText().toString().matches("^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$")){
            Toast.makeText(MainActivity2.this, "Introduceti nume  de utilizator", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(et_password.getText().toString().isEmpty()){
            Toast.makeText(MainActivity2.this, "Introduceti parola", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
