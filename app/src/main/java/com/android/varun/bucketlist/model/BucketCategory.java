package com.android.varun.bucketlist.model;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class BucketCategory
{

    public BucketCategory(){

    }
    String owner, title;
    HashMap<String, Object> createdAt;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HashMap<String, Object> getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(HashMap<String, Object> createdAt) {
        this.createdAt = createdAt;
    }

    public BucketCategory(String owner, String title) {
        this.owner = owner;
        this.title = title;
        HashMap<String, Object> time = new HashMap<>();
        time.put("timestamp", ServerValue.TIMESTAMP);
        this.createdAt = time;
    }

}
