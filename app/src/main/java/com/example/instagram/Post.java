package com.example.instagram;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "posts")
public class Post implements Serializable {
    @ColumnInfo(name = "photo")
    @PrimaryKey @NonNull
    private String url;
    @ColumnInfo(name="user")
    private String user;
    @ColumnInfo(name = "photo_description")
    private String description;

    public Post(String url, String user, String description) {
        this.url = url;
        this.user = user;
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Postare{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
