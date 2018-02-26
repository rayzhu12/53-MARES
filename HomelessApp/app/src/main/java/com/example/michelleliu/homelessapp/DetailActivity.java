package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        Object object = intent.getStringExtra("key");

        FloatingActionButton returnToList = (FloatingActionButton) findViewById(R.id.fab);
        returnToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(DetailActivity.this, DataActivity.class));
            }
        });
//
//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.  Pass the course info to
//            //the fragment
//            Bundle arguments = new Bundle();
//            arguments.putInt(ShelterDetailFragment.ARG_COURSE_ID,
//                    getIntent().getIntExtra(ShelterDetailFragment.ARG_COURSE_ID, 0));
//
//            ShelterDetailFragment fragment = new ShelterDetailFragment();
//            fragment.setArguments(arguments);
//
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.course_detail_container, fragment)
//                    .commit();
//        }

//        returnToList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Return to shelter list", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
