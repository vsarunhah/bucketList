package com.android.varun.bucketlist.model;

import java.util.HashMap;

public class BucketCategoryItem
{
    public BucketCategoryItem(){

    }

    String creator, title;
    int numberChecked;
    HashMap<String, Integer> numberCheckHash;

    public HashMap<String, Integer> getNumberCheckHash() {
        return numberCheckHash;
    }

    public void setNumberCheckHash(HashMap<String, Integer> numberCheckHash) {
        this.numberCheckHash = numberCheckHash;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberChecked() {
        return numberChecked;
    }

    public void setNumberChecked(int numberChecked) {
        this.numberChecked = numberChecked;
    }

    public BucketCategoryItem(String title, String owner, Integer checked) {
        this.title = title;
        this.numberChecked = checked;
        this.creator = owner;

    }
}
