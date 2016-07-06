package com.android.varun.bucketlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    EditText editText;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.nodeEntry);
        submit = (Button) findViewById(R.id.enterNode);

        database = FirebaseDatabase.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference categoryRef = database.getReference(constants.FIREBASE_LOCATION_CATEGORY);
                categoryRef.push().setValue(editText.getText().toString());
                editText.setText("");
            }
        });
    }
}
