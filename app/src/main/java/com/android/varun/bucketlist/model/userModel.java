package com.android.varun.bucketlist.model;

public class userModel
{
    String emailID, username;

    public userModel(String emailID, String username) {
        this.emailID = emailID;
        this.username = username;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
