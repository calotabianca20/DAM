package com.example.instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "users")
public class User implements Serializable {
    @ColumnInfo(name = "email_or_numb")
    private String userData;
    @PrimaryKey @NonNull
    @ColumnInfo(name = "user_name")
    private String userName;
    @ColumnInfo(name = "full_name")
    private String fullName;
    @ColumnInfo(name = "password")
    private String password;

    public User() {
    }

    public User(String userData, String userName, String fullName, String password) {
        this.userData = userData;
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(String userData) {
        this.userData = userData;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Cont{" +
                "userData='" + userData + '\'' +
                ", userName='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
