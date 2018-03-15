package com.example.michelleliu.homelessapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import model.Shelter;

public class DetailActivity extends AppCompatActivity {
    private Shelter shelter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //idk what toolbar does
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        shelter = (Shelter) getIntent().getSerializableExtra("passed shelter");

        // replace with something bc this looks ugly af lmao
        TextView nameTextView = (TextView) findViewById(R.id.name);
        nameTextView.setText(shelter.getName());
        TextView keyTextView = (TextView) findViewById(R.id.key);
        keyTextView.setText("Key: " + Integer.toString(shelter.getKey()));
        TextView capacityTextView = (TextView) findViewById(R.id.capacity);
        capacityTextView.setText("Capacity: " + shelter.getCapacity());
        TextView restrictionsTextView = (TextView) findViewById(R.id.restrictions);
        restrictionsTextView.setText("Restrictions: " + shelter.getRestriction());
        TextView coordinatesTextView = (TextView) findViewById(R.id.coordinates);
        coordinatesTextView.setText("Coordinates: (" + Float.toString(shelter.getLongitude())
                + ", " + Float.toString(shelter.getLatitude()) + ")");
        TextView addressTextView = (TextView) findViewById(R.id.address);
        addressTextView.setText("Address: " + shelter.getAddress());
        TextView specialNotesTextView = (TextView) findViewById(R.id.specialNotes);
        specialNotesTextView.setText("Special Notes: " + shelter.getSpecialNotes());
        TextView phoneNumberTextView = (TextView) findViewById(R.id.phoneNumber);
        phoneNumberTextView.setText("Phone Number: " + shelter.getPhoneNumber());

        FloatingActionButton returnToList = findViewById(R.id.fab);
        returnToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
