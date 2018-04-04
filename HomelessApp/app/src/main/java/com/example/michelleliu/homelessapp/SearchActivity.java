package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;

import java.util.ArrayList;
import java.util.List;

import model.Restriction;
import model.Shelter;
import model.ShelterManager;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final String entry = "";
    private ShelterManager sm = ShelterManager.getInstance();
    private List<Shelter> shelterList = sm.getShelterList();

    private ListView shelters;
    private ArrayAdapter<Shelter> arrayAdapter;

    private List<Shelter> nameMatchList;
    private List<Shelter> restrictionMatchList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final EditText bar = findViewById(R.id.detsearch2);

        getSupportActionBar().setTitle("Detailed Search");

        Button search = findViewById(R.id.detSearchButton);
        search.setOnClickListener(new View.OnClickListener() {
            String entry;
            @Override
            public void onClick(View view) {
                //List<Shelter> newShelterList = new ArrayList<>();
                entry = bar.getText().toString().toLowerCase();
                nameMatchList = sm.findShelterByString(entry);

                if (nameMatchList != null) {
                    Log.d("testing", "here1");
                    if (restrictionMatchList != null) {
                        Log.d("testing", "here2");
                        List<Shelter> combinedShelterList = new ArrayList<>();
                        for (Shelter s: nameMatchList) {
                            if (restrictionMatchList.contains(s)) {
                                Log.d("testing", s + " added");
                                combinedShelterList.add(s);
                            }
                        }
                        populateList(combinedShelterList);
                    } else {
                        Log.d("testing", "here3");
                        populateList(nameMatchList);
                    }
                } else {
                    Log.d("testing", "here4");
                    if (restrictionMatchList != null) {
                        Log.d("testing", "here5");
                        populateList(restrictionMatchList);
                    }
                }
            }
        });

        RadioButton rb1 = findViewById(R.id.radio_male);
        rb1.setOnClickListener(view -> restrictionMatchList = sm.findShelterByRestriction(Restriction.MALE));
        RadioButton rb2 = findViewById(R.id.radio_female);
        rb2.setOnClickListener(view -> restrictionMatchList = sm.findShelterByRestriction(Restriction.FEMALE));
        RadioButton rb3 = findViewById(R.id.radio_nonbinary);
        rb3.setOnClickListener(view -> restrictionMatchList = sm.findShelterByRestriction(Restriction.NONBINARY));
        RadioButton rb4 = findViewById(R.id.radio_families);
        rb4.setOnClickListener(view -> restrictionMatchList = sm.findShelterByRestriction(Restriction.FAMILIES));
        RadioButton rb5 = findViewById(R.id.radio_ya);
        rb5.setOnClickListener(view -> restrictionMatchList = sm.findShelterByRestriction(Restriction.YOUNG_ADULTS));
        RadioButton rb6 = findViewById(R.id.radio_children);
        rb6.setOnClickListener(view -> restrictionMatchList = sm.findShelterByRestriction(Restriction.CHILDREN));

        shelters = findViewById(R.id.shelters);
        shelters.setVisibility(View.VISIBLE);
        populateList(shelterList);

        //todo: clear should not bring up same page again, just clear the values
        Button clear = findViewById(R.id.clearButton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(SearchActivity.this, SearchActivity.class));
                //ListView shelters = findViewById(R.id.shelters);

                shelters.setAdapter(null);
                shelters.setVisibility(View.VISIBLE);
                populateList(shelterList);
            }
        });
    }
//    RadioGroup radioGroup = (RadioGroup) findViewById(R.id.filter);

    private void populateList(List<Shelter> newShelterList) {
        List<String> shelterNames = new ArrayList<>();
        if (newShelterList != null) {
            for (Shelter shelter : newShelterList) {
                shelterNames.add(shelter.getName());
            }
            arrayAdapter = new ArrayAdapter(this, R.layout.listview_layout, shelterNames);
            shelters.setAdapter(arrayAdapter);
            shelters.setOnItemClickListener(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!parent.getItemAtPosition(position).equals("Shelter Name")) {
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);
            String shelterName = (String) parent.getItemAtPosition(position);
            Shelter selectedShelter = sm.findShelterByName(shelterName);
            intent.putExtra("passed shelter", selectedShelter);
            startActivity(intent);
        }
    }

}