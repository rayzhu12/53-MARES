package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import model.Shelter;

public class DetailActivity extends AppCompatActivity {
    private Shelter shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        shelter = new Shelter(intent.getStringArrayExtra("shelter_info"));

        //todo: change to not-grey?
        FloatingActionButton returnToList = (FloatingActionButton) findViewById(R.id.fab);
        returnToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                //startActivity(new Intent(DetailActivity.this, DataActivity.class));
            }
        });

//        if (savedInstanceState == null) {
//            // Create the detail fragment and add it to the activity
//            // using a fragment transaction.  Pass the course info to
//            //the fragment
//            Bundle arguments = new Bundle();
//            arguments.putInt(ShelterDetailFragment.ARG_SHELTER_ID,
//                    getIntent().getIntExtra(ShelterDetailFragment.ARG_SHELTER_ID, 0));
//
//            ShelterDetailFragment fragment = new ShelterDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.____, fragment)
//                    .commit();
//        }
        // todo: fix 3 lines up, shelterName def not right

//        returnToList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Return to shelter list", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
