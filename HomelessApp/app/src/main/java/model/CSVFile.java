package model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Raymond Zhu
 */
public class CSVFile {
    private static InputStream inputStream;
    private static List<Shelter> shelterList;

    /**
     * the constructor for this class
     * @param inputStream the input stream
     */
    public CSVFile(InputStream inputStream){
        this.inputStream = inputStream;
    }

    /**
     * Parses raw csv file to extract information in String[]. Then passes String[] to Shelter
     * constructor to create List of Shelter objects using ArrayList.
     *
     * @return List of all Shelter objects parsed from raw csv file
     */
    public List<Shelter> read(){
        List resultList = new ArrayList();
        shelterList = new ArrayList<>();
        Log.d("3/14", "i reach here");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String csvLine;
            while ((csvLine = reader.readLine()) != null) {
                String[] row = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (!"Unique Key".equals(row[0])) {
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

// --Commented out by Inspection START (4/4/2018 3:23 PM):
    /**
     * return Shelter List
     * @return shelter list
     */
    public List<Shelter> returnShelterList() {
        if (shelterList == null) {
            shelterList = read();
        }
        return shelterList;
    }
// --Commented out by Inspection STOP (4/4/2018 3:23 PM)

//    String printRow(String[] row) {
//        String returned = "[";
//        for (String s : row) {
//            returned += s + "| ";
//        }
//        returned += "]";
//        return returned;
//    }
}