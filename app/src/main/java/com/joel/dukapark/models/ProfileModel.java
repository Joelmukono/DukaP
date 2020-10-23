package com.joel.dukapark.models;

import com.google.gson.annotations.SerializedName;

public class ProfileModel {
    @SerializedName("id")
    private int id;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("profile_pic")
    private String profile_pic;
    @SerializedName("profile_owner")
    private int profile_owner;

    public ProfileModel(int id, String username, String email, String profile_pic, int profile_owner) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.profile_pic = profile_pic;
        this.profile_owner = profile_owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public int getProfile_owner() {
        return profile_owner;
    }

    public void setProfile_owner(int profile_owner) {
        this.profile_owner = profile_owner;
    }
}
