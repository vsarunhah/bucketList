package com.android.varun.bucketlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.varun.bucketlist.model.BucketCategoryItem;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.common.api.BooleanResult;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class categoryListItem extends AppCompatActivity {
    FloatingActionButton fab;
    ListView lv;
    FirebaseListAdapter<BucketCategoryItem> mAdapter;
    FirebaseDatabase database;
    Integer checkedForId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list_item);
        lv = (ListView) findViewById(R.id.detail_list);

        String id = getIntent().getStringExtra(constants.KEY_ID);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference childRef = database.getReference(constants.FIREBASE_LOCATION_ITEMS).child(id);
        mAdapter = new FirebaseListAdapter<BucketCategoryItem>(this, BucketCategoryItem.class
                , R.layout.category_list_item_row, childRef) {
            @Override
            protected void populateView(View v, BucketCategoryItem model, int position) {
                ((TextView) v.findViewById(R.id.titleCategoryItemList)).setText(model.getTitle());
                ((TextView) v.findViewById(R.id.numberCheckedCategoryItemList)).setText(
                        String.valueOf(model.getNumberChecked()));
                ((TextView) v.findViewById(R.id.ownerCategoryListItem)).setText(model.getCreator());
            }
        };

        lv.setAdapter(mAdapter);


        fab = (FloatingActionButton) findViewById(R.id.addChildButton);
        setTitle("Items");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(categoryListItem.this);

                alert.setTitle("Enter the activity you want to do");

                final EditText input = new EditText(categoryListItem.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String id = getIntent().getStringExtra(constants.KEY_ID);
                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference ref = database.getReference(constants.FIREBASE_LOCATION_ITEMS).child(id);
                        ref.push().setValue(new BucketCategoryItem(input.getText().toString(), "Anonymous", 0));
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
