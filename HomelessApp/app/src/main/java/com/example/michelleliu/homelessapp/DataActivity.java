package com.example.michelleliu.homelessapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Parcelable;
import android.os.Bundle;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;

public class DataActivity extends AppCompatActivity {
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

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVFile csvFile = new CSVFile(inputStream);
        List<String[]> scoreList = csvFile.read();


        for(String[] scoreData:scoreList ) {
            itemArrayAdapter.add(scoreData);
        }
    }

}
