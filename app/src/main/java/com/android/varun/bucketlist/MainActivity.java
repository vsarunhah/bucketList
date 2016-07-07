package com.android.varun.bucketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.varun.bucketlist.model.BucketCategory;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    EditText value;
    Button submit;
    ListView categories;
    FirebaseListAdapter<BucketCategory> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        value = (EditText) findViewById(R.id.nodeEntry);
        submit = (Button) findViewById(R.id.enterNode);
        categories = (ListView) findViewById(R.id.categoryShow);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference categoryRef = database.getReference(constants.FIREBASE_LOCATION_CATEGORY);

        database = FirebaseDatabase.getInstance();

        mAdapter = new FirebaseListAdapter<BucketCategory>(this, BucketCategory.class,
                android.R.layout.simple_list_item_1,categoryRef) {
            @Override
            protected void populateView(View v, BucketCategory model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getTitle());
            }
        };
        categories.setAdapter(mAdapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String category = value.getText().toString();
                String owner = "Anonymous";
                BucketCategory newCategory = new BucketCategory(owner, category);
                categoryRef.push().setValue(newCategory);
                value.setText("");
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }
}
