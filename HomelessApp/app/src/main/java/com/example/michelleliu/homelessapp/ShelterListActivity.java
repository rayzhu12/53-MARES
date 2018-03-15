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
        List<String> shelterNames = new ArrayList<>();
        for (Shelter shelter : shelterList) {
            shelterNames.add(shelter.getName());
        }

        listView = findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, R.layout.listview_layout, shelterNames);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

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
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (!parent.getItemAtPosition(position).equals("Shelter Name")) {
            Log.d("clicked shelter", (String) parent.getItemAtPosition(position));
            Intent intent = new Intent(ShelterListActivity.this, DetailActivity.class);
            Shelter selectedShelter = sm.findShelterByKey(position); // todo: fix stubbed method
            intent.putExtra("passed shelter", selectedShelter);
            startActivity(intent);
        }
    }

}
