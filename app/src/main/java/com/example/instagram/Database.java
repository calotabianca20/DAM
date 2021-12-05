package com.example.instagram;
import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

@androidx.room.Database(entities = {User.class, Post.class}, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {

    private static Database database;
    public abstract DAO dao();


    public static Database getDatabase(Context context){
        synchronized (Database.class) {
            if (database == null) {
                database = Room.databaseBuilder(context, Database.class, "users_db").allowMainThreadQueries().build();
            }
            return database;
        }
    }
}
