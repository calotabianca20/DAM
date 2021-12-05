package com.example.instagram;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AddPhoto extends AppCompatActivity {
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.add_photo);

          EditText urlString = findViewById(R.id.url), description = findViewById(R.id.description);

          Intent intent = getIntent();
          String username = intent.getStringExtra("username");

          ImageView moveForward_bt = findViewById(R.id.moveForward_bt), cancel_bt= findViewById(R.id.cancel_bt);

          moveForward_bt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    String url = urlString.getText().toString();
                    String descrip = description.getText().toString();
                    if(!url.isEmpty() && !descrip.isEmpty()) {

                         Post post = new Post(url, username, descrip);
//                         Toast.makeText(AddPhoto.this, post.toString(), Toast.LENGTH_SHORT).show();
                         Bundle bundle = new Bundle();
                         bundle.putSerializable("post", post);
                         Intent intent = new Intent();
                         intent.putExtra("raspuns", bundle);
                         setResult(RESULT_OK, intent);
                         finish();
                    }
               }
          });

          cancel_bt.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                    finish();
               }
          });
     }
}
