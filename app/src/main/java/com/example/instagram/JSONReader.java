package com.example.instagram;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class JSONReader {
    public void read(String urlPath, IResponse response){
        try {
            URL url = new URL(urlPath);
            HttpsURLConnection  connection = (HttpsURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);

            StringBuilder result = new StringBuilder();
            String line = "";
            while ((line= reader.readLine())!=null){
                result.append(line);
            }
            List<Postare> postari = parsare(result.toString());
            response.onSuccess(postari);
            reader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            response.onError(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            response.onError(e.getMessage());
        }
    }

    public List<Postare> parsare(String jsonString){
        List<Postare> postari = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("postari");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject obj = jsonArray.getJSONObject(i);
                String url = obj.getString("image");
                String user = obj.getString("user");
                String description = obj.getString("description");

                Postare postare = new Postare(url,user,description);
                postari.add(postare);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return postari;
    }
}
