package com.example.instagram;



public class Postare {
    private String url;
    private String user;
    private String description;

    public Postare(String url, String user, String description) {
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
