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
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import model.FamilyType;
import model.Gender;
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

        Button search = (Button) findViewById(R.id.detSearchButton);
        search.setOnClickListener(new View.OnClickListener() {
            String entry;
            @Override
            public void onClick(View view) {
                List<Shelter> newShelterList = new ArrayList<>();
                entry = bar.getText().toString().toLowerCase();
                nameMatchList = sm.findShelterByString(entry);

                if (nameMatchList != null) {
                    if (restrictionMatchList != null) {
                        List<Shelter> combinedShelterList = new ArrayList<Shelter>();
                        for (Shelter s: nameMatchList) {
                            if (restrictionMatchList.contains(s)) {
                                combinedShelterList.add(s);
                            }
                        }
                        populateList(combinedShelterList);
                    } else {
                        populateList(nameMatchList);
                    }
                }
            }
        });

        shelters = findViewById(R.id.shelters);
        shelters.setVisibility(View.VISIBLE);
        populateList(shelterList);

        //todo: clear should not bring up same page again, just clear the values
        Button clear = (Button) findViewById(R.id.clearButton);
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

    //todo: connect it with app/button listener
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_male:
                if (checked) {
                    restrictionMatchList = sm.findShelterByRestriction(Restriction.MALE);
                }
                break;
            case R.id.radio_female:
                if (checked) {
                    restrictionMatchList = sm.findShelterByRestriction(Restriction.FEMALE);
                }
                break;
            case R.id.radio_nonbinary:
                if (checked) {
                    restrictionMatchList = sm.findShelterByRestriction(Restriction.NONBINARY);
                }
                break;
            case R.id.radio_families:
                if (checked) {
                    restrictionMatchList = sm.findShelterByRestriction(Restriction.FAMILIES);
                }
                break;
            case R.id.radio_ya:
                if (checked) {
                    restrictionMatchList = sm.findShelterByRestriction(Restriction.YOUNG_ADULTS);
                }
                break;
            case R.id.radio_children:
                if (checked) {
                    restrictionMatchList = sm.findShelterByRestriction(Restriction.CHILDREN);
                }
                break;
        }
    }
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