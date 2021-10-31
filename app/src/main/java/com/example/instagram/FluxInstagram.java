package com.example.instagram;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class FluxInstagram extends AppCompatActivity {
    private int[] images = {R.drawable.image, R.drawable.image2, R.drawable.image3};
    private String[] users ={"dobrinlaura07","gabiionescu","bogdanionut16"};
    private String[] photoDescriptions ={"Merry Christmas!","Wishing you a Christmas that's merry and bright!","Happy Holidays!"};

    private ListView listView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flux_instagram);

        FeedAdapter feedAdapter = new FeedAdapter(this, images,users,photoDescriptions);
        listView = findViewById(R.id.listView);
        listView.setAdapter(feedAdapter);
    }
}
