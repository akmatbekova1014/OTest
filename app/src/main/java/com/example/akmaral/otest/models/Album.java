package com.example.akmaral.otest.models;

import com.google.gson.annotations.SerializedName;

public class Album {
    @SerializedName("userId")
    private String userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    public Album(String userId, int id, String title) {
        this.userId = userId;
        this.id = id;
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
