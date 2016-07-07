package com.android.varun.bucketlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.varun.bucketlist.model.BucketCategoryItem;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class categoryListItem extends AppCompatActivity {
    FloatingActionButton fab;
    ListView lv;
    FirebaseListAdapter<BucketCategoryItem> mAdapter;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list_item);
        lv = (ListView) findViewById(R.id.detail_list);

        fab = (FloatingActionButton) findViewById(R.id.addChildButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(categoryListItem.this);

                alert.setTitle("Add Entry");
                alert.setMessage("Enter the activity you want to do");

                final EditText input = new EditText(categoryListItem.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String id = getIntent().getStringExtra(constants.KEY_ID);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference ref = database.getReference(constants.FIREBASE_LOCATION_ITEMS).child(id);
                        ref.push().setValue(input.getText().toString());
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int whichButton)
                    {
                    }
                });
                alert.show();
            }
        });
    }
}
