package com.example.instagram;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class UserPosts {
    @Embedded
    public User user;

    @Relation(
            parentColumn = "user_name",
            entityColumn = "user"
    )
    public List<Post> posts;
}
