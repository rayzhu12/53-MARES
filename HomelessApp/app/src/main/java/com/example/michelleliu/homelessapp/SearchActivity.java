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
import android.widget.Spinner;

import java.io.InputStream;
import java.util.List;

import model.CSVFile;
import model.FamilyType;
import model.Gender;
import model.Shelter;
import model.ShelterManager;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    final String entry = "";
    private ShelterManager sm = ShelterManager.getInstance();
    private List<Shelter> shelterList = sm.getShelterList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //final String entry;

        EditText bar = findViewById(R.id.detsearch2);

        getSupportActionBar().setTitle("Detailed Search");

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);

        Spinner gender = (Spinner) findViewById(R.id.familySpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Gender.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        Spinner familyType = (Spinner) findViewById(R.id.familySpinner);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, FamilyType.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter2);

        Log.d("selected gender", gender.toString());

        // uncomment out
        /*


        final List<String> scores = new ArrayList<>();

        Button search = (Button) findViewById(R.id.search2);
        search.setOnClickListener(new View.OnClickListener() {
            String entry;
            @Override
            public void onClick(View view) {
                entry = bar.getText().toString().toLowerCase();
//                System.out.println("a " + entry);
                for(String[] score : scoreList) {
                    for(String s : score) {
                        s = score[3].toLowerCase();
                        entry = entry.toLowerCase();
//                        System.out.println("BBBBB " + s);

                        if (entry.equals("male")) {
                            if (s.contains("men") && !s.contains("women") && !scores.contains(score[1])) {
//                            System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                scores.add(score[1]);
//                            for (String a : scores) {
//                                System.out.println("a " + a);
//                            }
                            }
                        } else if (entry.equals("female")) {
                            if (s.contains("women") && !scores.contains(score[1])) {
//                            System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                scores.add(score[1]);
//                            for (String a : scores) {
//                                System.out.println("a " + a);
//                            }
                            }
                        } else if (entry.equals("family")) {
                            if ((s.contains("family") || s.contains("families")) && !scores.contains(score[1])) {
//                            System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                                scores.add(score[1]);
//                            for (String a : scores) {
//                                System.out.println("a " + a);
//                            }
                            }
                        } else if (s.contains(entry) && !scores.contains(score[1])) {
//                            System.out.println("HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            scores.add(score[1]);
//                            for (String a : scores) {
//                                System.out.println("a " + a);
//                            }
                        }
                    }
                }
            }
        });


        ListView shelters = findViewById(R.id.shelters);

        ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this, R.layout.textview_layout, scores);

        shelters.setAdapter(adapter);
        shelters.setVisibility(View.VISIBLE);

        shelters.setOnItemClickListener(this);
        */

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

        Button clear = (Button) findViewById(R.id.button2);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SearchActivity.this, SearchActivity.class));
//                ListView shelters = findViewById(R.id.shelters);
//
//                shelters.setAdapter(null);
//                shelters.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            Intent intent = new Intent(SearchActivity.this, DetailActivity.class);

            //move sm to class var
            ShelterManager sm = ShelterManager.getInstance();
            Shelter selectedShelter = sm.findShelterByKey(position); // todo: check position + 1??
            intent.putExtra("passed shelter", selectedShelter);
            startActivity(intent);
        }
    }

}