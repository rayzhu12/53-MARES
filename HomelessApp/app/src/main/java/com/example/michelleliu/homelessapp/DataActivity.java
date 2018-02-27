package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Parcelable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import model.Shelter;

public class DataActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        listView = (ListView) findViewById(R.id.listView);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);
        listView.setOnItemClickListener(this);

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();

        for(String[] scoreData:scoreList) {
            itemArrayAdapter.add(scoreData);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position != 0) {
            Intent intent = new Intent(DataActivity.this, DetailActivity.class);
            String[] selection = (String[]) parent.getItemAtPosition(position);
            intent.putExtra("shelter_info", selection);

            startActivity(intent);
        }
    }
}
