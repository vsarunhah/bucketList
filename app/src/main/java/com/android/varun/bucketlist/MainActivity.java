package com.android.varun.bucketlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.varun.bucketlist.model.BucketCategory;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    FloatingActionButton fab;
    ListView categories;
    FirebaseListAdapter<BucketCategory> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        categories = (ListView) findViewById(R.id.categoryShow);
        fab = (FloatingActionButton) findViewById(R.id.addCategoryButton);

        database = FirebaseDatabase.getInstance();
        final DatabaseReference categoryRef = database.getReference(constants.FIREBASE_LOCATION_CATEGORY);
        setTitle("Categories");

        database = FirebaseDatabase.getInstance();

        mAdapter = new FirebaseListAdapter<BucketCategory>(this, BucketCategory.class,
                android.R.layout.simple_list_item_1,categoryRef) {
            @Override
            protected void populateView(View v, BucketCategory model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getTitle());
            }
        };
        categories.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Enter a category");

                final EditText input = new EditText(MainActivity.this);
                alert.setView(input);

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        String category = input.getText().toString();
                        if (!category.equals(null) && !category.equals("") && !category.equals(" ")) {
                            String owner = "Anonymous";
                            BucketCategory newCategory = new BucketCategory(owner, category);
                            categoryRef.push().setValue(newCategory);

                        } else{
                            Toast.makeText(MainActivity.this, "Category cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {public void onClick(DialogInterface dialog, int whichButton)
                {
                }
                });
                alert.show();
            }
        });



        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, categoryListItem.class);
                String key = mAdapter.getRef(position).getKey();
                i.putExtra(constants.KEY_ID,key);
                i.putExtra(getTitle().toString(), "title");
                startActivity(i);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

}
