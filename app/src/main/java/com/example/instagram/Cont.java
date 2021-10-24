package com.example.instagram;

import java.io.Serializable;

public class Cont implements Serializable {
    private String userData;
    private String userName;
    private String fullName;
    private String password;

    public Cont() {
    }

    public Cont(String userData, String userName, String fullName, String password) {
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
