package model;

import android.util.Log;

import com.example.michelleliu.homelessapp.CSVFile;

import java.io.InputStream;
import java.util.ArrayList;
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
        List<Shelter> matchingShelters = new ArrayList<>();
        matchingShelters.add(shelterList.get(0));
        return matchingShelters;
    }

    // families, newborns
    public List<Shelter> findShelterByType() {
        //TODO fix stub
        return null;
    }

    public Shelter findShelterByKey(int key) {
        //TODO fix stub
        Shelter foundShelter = null;
        if (key >= shelterList.size()) {
            throw new IllegalArgumentException("Key is not in shelter list.");
        }
        for (Shelter s : shelterList) {
            if (s.getKey() == key) {
                foundShelter = s;
            }
        }
        return foundShelter;
    }



    //todo: add findShelterByType
}
