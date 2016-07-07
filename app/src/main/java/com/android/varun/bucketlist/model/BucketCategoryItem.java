package com.android.varun.bucketlist.model;

public class BucketCategoryItem
{
    public BucketCategoryItem(){

    }

    String creator, title;
    int numberChecked;

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

    public BucketCategoryItem(String title) {
        this.title = title;
    }
}
