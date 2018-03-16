package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.CSVFile;
import model.Shelter;
import model.ShelterManager;

public class ShelterListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    private ArrayAdapter adapter;
    private ShelterManager sm = ShelterManager.getInstance();
    private List<Shelter> shelterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        //adds the initial shelter data if not already populated
        if (shelterList == null) {
            InputStream inputStream = getResources().openRawResource(R.raw.stats);
            CSVFile csvFile = new CSVFile(inputStream);
            shelterList = csvFile.read();
            sm.setShelterList(shelterList);
        }

        Button detailSearchButton = findViewById(R.id.detailsearch);
        detailSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShelterListActivity.this, SearchActivity.class));
            }
        });

        //adding each name from shelter list to shelterNames
        //eventually switch out of listview?? (pls!!!!)

        listView = findViewById(R.id.listView);
        populateList(shelterList);


        // search bar stuff
        // keep generic search by name function in ShelterListA or move to SearchA and call from here?
        EditText bar = findViewById(R.id.searchbar);
        bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ShelterListActivity.this.adapter.getFilter().filter(charSequence);
                //populateList(sm.findShelterByName(charSequence.toString()));
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    /**
     * Populates the class listView with elements of input shelter List. Can be changed if switch
     * out of listview
     * @param newShelterList list of shelters to be added
     */
    private void populateList(List<Shelter> newShelterList) {
        List<String> shelterNames = new ArrayList<>();
        for (Shelter shelter : newShelterList) {
            shelterNames.add(shelter.getName());
        }

        adapter = new ArrayAdapter(this, R.layout.listview_layout, shelterNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("selected shelter", (String) parent.getItemAtPosition(position));
        if (!parent.getItemAtPosition(position).equals("Shelter Name")) {
            Intent intent = new Intent(ShelterListActivity.this, DetailActivity.class);
            String shelterName = (String) parent.getItemAtPosition(position);
            Shelter selectedShelter = sm.findShelterByName(shelterName);
            Log.d("selected shelter", (String) parent.getItemAtPosition(position));
            intent.putExtra("passed shelter", selectedShelter);
            startActivity(intent);
        }
    }

}
