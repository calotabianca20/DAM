package com.example.instagram;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface DAO {
    @Insert
    void insertUser(User... users);

    @Insert
    void insertPost(Post ...posts);

    @Query("SELECT * FROM users WHERE user_name=:userName")
    public User checkUser(String userName);

    @Query("SELECT * FROM posts WHERE user=:userName")
    public List<Post> posts(String userName);

    @Query("SELECT * FROM users")
    public List<User> users();

    @Query("SELECT * FROM posts")
    public List<Post> allPosts();
}
