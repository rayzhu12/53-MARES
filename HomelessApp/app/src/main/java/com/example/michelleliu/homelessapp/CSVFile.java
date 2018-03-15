package com.example.michelleliu.homelessapp;

/**
 * Created by Raymond the dummy on 2/23/2018.
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
    static InputStream inputStream;
    static List resultList;
    static List<Shelter> shelterList;

    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    /**
     * Parses raw csv file to extract information in String[]. Then passes String[] to Shelter
     * constructor to create List of Shelter objects using ArrayList.
     *
     * @return List of all Shelter objects parsed from raw csv file
     */
    public static List<Shelter> read(){
        resultList = new ArrayList();
        shelterList = new ArrayList<>();
        Log.d("3/14", "i reach here");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (!row[0].equals("Unique Key")) {
                    shelterList.add(new Shelter(row));
                }
            }
        }
        catch (IOException ex) {
            throw new RuntimeException("Error in reading CSV file: " + ex);
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+ e);
            }
        }
        return shelterList;
    }

    // not sure what this does/why its needed but too scared to delete -m
    public static List<Shelter> returnShelterList() {
        if (shelterList == null) {
            shelterList = read();
        }
        return shelterList;
    }

//    debug printmethod
//    String printRow(String[] row) {
//        String returned = "[";
//        for (String s : row) {
//            returned += s + "| ";
//        }
//        returned += "]";
//        return returned;
//    }
}