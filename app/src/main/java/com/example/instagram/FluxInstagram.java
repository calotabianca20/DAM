package com.example.instagram;

import android.content.AsyncQueryHandler;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FluxInstagram extends AppCompatActivity {
    List<Post> postari;
    private ListView listView;
    private final int fluxRequest = 200;
    public DAO dao;
    private FeedAdapter feedAdapter;
    private FirebaseDatabase database;
    private DatabaseReference refToPostTb;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flux_instagram);

        database = FirebaseDatabase.getInstance();
        refToPostTb = database.getReference("Posts");


        dao = Database.getDatabase(FluxInstagram.this).dao();
        postari = new ArrayList<>();
        postari.add(new Post("https://www.bahrain-confidential.com/wp-content/uploads/2020/12/image.jpg","dobrinlaura07","Merry Christmas!"));
        postari.add(new Post("https://nationaltoday.com/wp-content/uploads/2019/12/christmas-1.jpg","gabiionescu","Wishing you a Christmas that's merry and bright!"));
        postari.add(new Post("https://www.history.com/.image/ar_4:3%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTY4OTA4MzI0ODc4NjkwMDAw/christmas-tree-gettyimages-1072744106.jpg","bogdanionut16","Happy Holidays!"));
        feedAdapter = new FeedAdapter(postari,this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Post> posts =  dao.allPosts();
                if(posts!=null){
                    feedAdapter.update(posts);
                }
            }
        }).start();

        listView = findViewById(R.id.listView);
        listView.setAdapter(feedAdapter);


        Intent intent  = getIntent();
        String userName = intent.getStringExtra("username");


        ImageView addPohoto = findViewById(R.id.addPhotos);
        addPohoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FluxInstagram.this, AddPhoto.class);
                intent.putExtra("username",  userName);
                startActivityForResult(intent,fluxRequest);
            }
        });

        JSONReader reader = new JSONReader();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reader.read("https://jsonkeeper.com/b/D2ZC", new IResponse() {
                    @Override
                    public void onSuccess(List<Post> postari) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                feedAdapter.update(postari);

                            }
                        });
                    }

                    @Override
                    public void onError(String mesaj) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });
                    }
                });
            }
        });

        thread.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.v("posts", dao.allPosts().toString());
            }
        }).start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==fluxRequest){
            if(resultCode==RESULT_OK){
                if(data!=null){
                    Bundle bundle = data.getBundleExtra("raspuns");
                    Post post = (Post)  bundle.getSerializable("post");


                    refToPostTb.push().setValue(post);

                    Toast.makeText(FluxInstagram.this, post.toString(), Toast.LENGTH_SHORT).show();
                    List<Post> addedPhoto = new ArrayList<>();
                    addedPhoto.add(post);
                    feedAdapter.update(addedPhoto);

                    AsyncTask.execute(new Runnable() {
                        @Override
                        public void run() {
                            dao.insertPost(post);
                        }
                    });
                }
            }
        }
    }

}