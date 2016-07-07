package com.android.varun.bucketlist;

import android.content.Intent;
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
                if (!category.equals(null) && !category.equals("") && !category.equals(" ")) {
                    String owner = "Anonymous";
                    BucketCategory newCategory = new BucketCategory(owner, category);
                    categoryRef.push().setValue(newCategory);
                    value.setText("");
                } else{
                    Toast.makeText(MainActivity.this, "Category cannot be empty", Toast.LENGTH_SHORT).show();
                }

            }
        });

        categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, categoryListItem.class);
                String key = mAdapter.getRef(position).getKey();
                i.putExtra(constants.KEY_ID,key);
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
