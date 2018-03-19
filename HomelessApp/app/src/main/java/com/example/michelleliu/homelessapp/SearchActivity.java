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
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import model.FamilyType;
import model.Gender;
import model.Shelter;
import model.ShelterManager;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final String entry = "";
    private ShelterManager sm = ShelterManager.getInstance();
    private List<Shelter> shelterList = sm.getShelterList();
    private ListView shelters;
    private ArrayAdapter<Shelter> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //final String entry;

        final EditText bar = findViewById(R.id.detsearch2);

        getSupportActionBar().setTitle("Detailed Search");

        /*
        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);
        */

        final Spinner gender = findViewById(R.id.genderSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Gender.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        //can do without ifs?
        Gender selectedGender;
        String genderString = gender.getSelectedItem().toString();
        if (genderString.equals("Male")) {
            selectedGender = Gender.MALE;
        } else if (genderString.equals("Female")) {
            selectedGender = Gender.FEMALE;
        } else {
            selectedGender = Gender.NONBINARY;
        }



        final Spinner familyType = findViewById(R.id.familySpinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, FamilyType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyType.setAdapter(adapter2);

        FamilyType selectedFamilyType;
        String familyString = familyType.getSelectedItem().toString();
        if (familyString.equals("Individual")) {
            selectedFamilyType = FamilyType.INDIVIDUAL;
        } else {
            selectedFamilyType = FamilyType.FAMILY;
        }

        Log.d("selected gender", selectedGender.toString());
        Log.d("selected family type", selectedFamilyType.toString());

        final List<Shelter> newShelterList = null;
        Button search = (Button) findViewById(R.id.detSearchButton);
        search.setOnClickListener(new View.OnClickListener() {
            String entry;
            @Override
            public void onClick(View view) {
                entry = bar.getText().toString().toLowerCase();
                List<Shelter> nameMatchList = sm.findShelterByString(entry);
                List<Shelter> genderMatchList = sm.findShelterByGender(selectedGender);
                List<Shelter> familyTypeMatchList = sm.findShelterByFamilyType(selectedFamilyType);

                //TODO: add no selection option
                if (nameMatchList != null) {
                    for (Shelter s : nameMatchList) {
                        if ((genderMatchList == null || genderMatchList.contains(s))
                                && (familyTypeMatchList == null || familyTypeMatchList.contains(s))) {
                            newShelterList.add(s);
                        }
                    }
                } else if (genderMatchList != null){
                    for (Shelter s : genderMatchList) {
                        if (familyTypeMatchList == null || familyTypeMatchList.contains(s)) {
                            newShelterList.add(s);
                        }
                    }
                }

            /*
            System.out.println("a " + entry);
            for(String[] score : scoreList) {
                for(String s : score) {
                    s = score[3].toLowerCase();
                    entry = entry.toLowerCase();
                    System.out.println("BBBBB " + s);

                    if (entry.equals("male")) {
                        if (s.contains("men") && !s.contains("women") && !scores.contains(score[1])) {
                        System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            scores.add(score[1]);
                        for (String a : scores) {
                            System.out.println("a " + a);
                        }
                        }
                    } else if (entry.equals("female")) {
                        if (s.contains("women") && !scores.contains(score[1])) {
                        System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            scores.add(score[1]);
                        for (String a : scores) {
                            System.out.println("a " + a);
                        }
                        }
                    } else if (entry.equals("family")) {
                        if ((s.contains("family") || s.contains("families")) && !scores.contains(score[1])) {
                        System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            scores.add(score[1]);
                        for (String a : scores) {
                            System.out.println("a " + a);
                        }
                        }
                    } else if (s.contains(entry) && !scores.contains(score[1])) {
                        System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        scores.add(score[1]);
                        for (String a : scores) {
                            System.out.println("a " + a);
                        }
                    }
                }
            }
            */
            }
        });

        shelters = findViewById(R.id.shelters);
        shelters.setVisibility(View.VISIBLE);
        populateList(newShelterList);
        //shelters.setAdapter(adapter);
        //shelters.setOnItemClickListener(this);

//        for (String s : scores) {
//            System.out.println("CCCCC " + s);
//        }

//                (view) -> {
//            entry = search.getText().toString();
//            System.out.println(entry);
//            for(String[] score : scoreList) {
//                for(String s : score) {
//                    if (s.contains(entry)) {
//                        scores.add(score[1]);
//                    }
//                }
//            }
//        });

        Button clear = (Button) findViewById(R.id.clearButton);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, SearchActivity.class));
                ListView shelters = findViewById(R.id.shelters);

                shelters.setAdapter(null);
                shelters.setVisibility(View.VISIBLE);
            }
        });

    }


    //idk how to use lmao
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
            //move sm to class var

            intent.putExtra("passed shelter", selectedShelter);
            startActivity(intent);
        }
    }

}