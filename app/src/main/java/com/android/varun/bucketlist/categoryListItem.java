package com.android.varun.bucketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class categoryListItem extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list_item);
        String id = getIntent().getStringExtra(constants.KEY_ID);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(constants.FIREBASE_LOCATION_ITEMS).child(id);
        ref.push().setValue("testing");
    }
}
