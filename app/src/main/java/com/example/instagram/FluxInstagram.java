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
    List<Postare> postari;
    private ListView listView;
    private Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flux_instagram);
        postari = new ArrayList<>();
        postari.add(new Postare("https://www.bahrain-confidential.com/wp-content/uploads/2020/12/image.jpg","dobrinlaura07","Merry Christmas!"));
        postari.add(new Postare("https://nationaltoday.com/wp-content/uploads/2019/12/christmas-1.jpg","gabiionescu","Wishing you a Christmas that's merry and bright!"));
        postari.add(new Postare("https://www.history.com/.image/ar_4:3%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTY4OTA4MzI0ODc4NjkwMDAw/christmas-tree-gettyimages-1072744106.jpg","bogdanionut16","Happy Holidays!"));

        FeedAdapter feedAdapter = new FeedAdapter(postari,this);
        listView = findViewById(R.id.listView);
        listView.setAdapter(feedAdapter);

        JSONReader reader = new JSONReader();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reader.read("https://jsonkeeper.com/b/D2ZC", new IResponse() {
                    @Override
                    public void onSuccess(List<Postare> postari) {
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
    }
}