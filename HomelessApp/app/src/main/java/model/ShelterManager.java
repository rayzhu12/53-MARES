package model;

import android.util.Log;

import com.example.michelleliu.homelessapp.AppActivity;
import com.example.michelleliu.homelessapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.CSVFile;

/**
 * @author Michelle
 */
public class ShelterManager {
    private static final ShelterManager _instance = new ShelterManager();

    /**
     * gets the instance
     * @return the instance
     */
    public static ShelterManager getInstance() { return _instance; }

    //private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //private final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

    private List<Shelter> shelterList;
    //replace with database
    public ShelterManager() {
        try {
            //InputStream inputStream = getResources().openRawResource(R.raw.stats);
            InputStream inputstream = new FileInputStream("C:\\0RAYZHU12\\GitHub\\SNACK-OVERFLOW\\HomelessApp\\app\\src\\main\\res\\raw\\stats.csv");
            //InputStream inputstream = new FileInputStream("C:\\Users\\michelleliu\\Documents\\Code\\SNACK-OVERFLOW\\HomelessApp\\app\\src\\main\\res\\raw\\stats.csv");
            CSVFile csvFile = new CSVFile(inputstream);
            shelterList = csvFile.returnShelterList();
        } catch (FileNotFoundException e){
            throw new RuntimeException("CSV file does not exist.");
        }
    }

    /**
     * gets the shelter list
     * @return the shelter list
     */
    public List<Shelter> getShelterList() {
        return shelterList;
    }

    /**
     * sets the shelter list
     * @param shelterList the shelter list
     */
    public void setShelterList(List<Shelter> shelterList) {
        this.shelterList = shelterList;
    }

    /**
     * Returns the Shelter whose name matches the input String exactly.
     * @param name of Shelter
     * @return Shelter object in shelterList
     */
    public Shelter findShelterByName(String name) {
        Log.d("u got here", name.toLowerCase());
        Shelter[] foundShelter = new Shelter[1];

        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference("shelters");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("s.getName", "I'm here");
//                for (DataSnapshot item: dataSnapshot.getChildren()) {
//                    Shelter s = item.getValue(Shelter.class);
//                    Log.d("s.getName", s.getName());
//                    Log.d("name.toLowercase", name.toLowerCase());
//                    if (s.getName().toLowerCase().equals(name.toLowerCase())) {
//                        foundShelter[0] = s;
//                    }
//
//                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        for (Shelter s : shelterList) {
            if (s.getName().equals(name)) {
                foundShelter[0] = s;
            }
        }

        return foundShelter[0];
    }

    /**
     * Returns the List of Shelters whose names include String s
     * @param input String that is searched for
     * @return list of Shelters whose names include s
     */
    public List<Shelter> findShelterByString(String input) {
        if (input.equals("")) {
            return new ArrayList<>();
        }
        List<Shelter> matchingShelters = new ArrayList<>();
        for (Shelter s : shelterList) {
            if (s.getName().toLowerCase().contains(input.toLowerCase())) {
                matchingShelters.add(s);
            }
        }
        return matchingShelters;

    }

    /**
     * finds shelter by restriction
     * @param rest the restriction we are filtering shelters by
     * @return the list of shelters that satisfy the restriction
     */
    public List<Shelter> findShelterByRestriction(Restriction rest) {
        List<Shelter> matches = new ArrayList<>();
        for (Shelter s : shelterList) {
            if (s.getRestrictionList().contains(rest)) {
                matches.add(s);
            }
        }
        return matches;
    }

//    /**
//     * finds the shelter by key
//     * @param key the key of the shelter we are searching for
//     * @return the shelter associated with the given key
//     */
//    public Shelter findShelterByKey(int key) {
//        Shelter foundShelter = null;
//        if (key >= shelterList.size()) {
//            throw new IllegalArgumentException("Key is not in shelter list.");
//        }
//        for (Shelter s : shelterList) {
//            if (s.getKey() == key) {
//                foundShelter = s;
//            }
//        }
//        return foundShelter;
//    }
}
