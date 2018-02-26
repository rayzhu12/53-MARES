package com.example.michelleliu.homelessapp;

/**
 * Created by Raymond on 2/23/2018.
 */
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import model.Shelter;

public class CSVFile {
    InputStream inputStream;

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    public List read(){
        List resultList = new ArrayList();
        List<Shelter> shelterList = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                if (!row[0].equals("Unique Key")) {
                    shelterList.add(new Shelter(row));
                }
                resultList.add(row);
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: "+ ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+ e);
            }
        }
        return resultList;
    }

    //todo: debug print
    String printRow(String[] row) {
        String returned = "[";
        for (String s : row) {
            returned += s + "| ";
        }
        returned += "]";
        return returned;
    }
}