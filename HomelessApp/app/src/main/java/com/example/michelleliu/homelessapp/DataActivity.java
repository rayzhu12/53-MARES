package com.example.michelleliu.homelessapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Parcelable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;
    //private ItemArrayAdapter itemArrayAdapter;
    private ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        Button search = (Button) findViewById(R.id.detailsearch);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DataActivity.this, SearchActivity.class));
            }
        });

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();


        List<String> scores = new ArrayList<>();
        for (String[] score : scoreList) {
            if (score[0] != "Unique Key") {
                scores.add(score[1]);
            }
        }

//        for(String[] scoreData:scoreList) {
//            itemArrsyAdapter.add(scoreData);
//        }
        listView = (ListView) findViewById(R.id.listView);
        adapter = new ArrayAdapter(this, R.layout.listview_layout, scores);
        //itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);
        //itemArrayAdapter = new ItemArrayAdapter(this,R.layout.item_layout, scoreList);


        //Parcelable state = listView.onSaveInstanceState();
        //listView.setAdapter(itemArrayAdapter);
        //listView.onRestoreInstanceState(state);
        listView.setAdapter(adapter);
        EditText bar = findViewById(R.id.searchbar);
        bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                DataActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        listView.setOnItemClickListener(this);

//        InputStream inputStream = getResources().openRawResource(R.raw.stats);
//        CSVFile csvFile = new CSVFile(inputStream);
//        List<String[]> scoreList = csvFile.read();

    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            Intent intent = new Intent(DataActivity.this, DetailActivity.class);
            //String[] selection = (String[]) parent.getItemAtPosition(position);
            String selection = (String) parent.getItemAtPosition(position);

            InputStream inputStream = getResources().openRawResource(R.raw.stats);
            CSVFile csvFile = new CSVFile(inputStream);
            List<String[]> scoreList = csvFile.read();

            String[] shelterInfo= null;

            for (String[] shelter : scoreList) {
                if (shelter[1].equals(selection)) {
                    shelterInfo = shelter;
                    break;
                }
            }

            //System.out.println("SELECTION " + selection);
            intent.putExtra("shelter_info", shelterInfo);
            startActivity(intent);
        }
    }

}
