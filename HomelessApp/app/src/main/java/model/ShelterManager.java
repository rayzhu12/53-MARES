package model;

import android.util.Log;

import com.example.michelleliu.homelessapp.CSVFile;

import java.io.InputStream;
import java.util.List;

/**
 * Created by michelleliu on 3/14/18.
 */

public class ShelterManager {
    private static final ShelterManager _instance = new ShelterManager();
    public static ShelterManager getInstance() { return _instance; }

    List<Shelter> shelterList;

    public List<Shelter> getShelterList() {
        return shelterList;
    }

    public void setShelterList(List<Shelter> shelterList) {
        this.shelterList = shelterList;
    }

    // list of shelters that have this as the name or within name
    public List<Shelter> findShelterByName(String s) {
        //TODO
        return null;
    }

    public List<Shelter> findShelterByType() {
        //TODO
        return null;
    }

    public Shelter findShelterByID(int id) {
        //TODO
        return null;
    }



    //todo: add findShelterByType
}
