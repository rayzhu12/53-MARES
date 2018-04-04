package model;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Michelle
 * @date 3/14/18
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
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;

    //replace with database
    List<Shelter> shelterList;


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

        myRef = mFirebaseDatabase.getReference("shelters");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("s.getName", "I'm here");
//                for (DataSnapshot item: dataSnapshot.getChildren()) {
//                    Shelter s = item.getValue(Shelter.class);
//                    Log.d("s.getName", s.getName());
//                    Log.d("name.toLowercase", name.toLowerCase());
//                    if (s.getName().toLowerCase().equals(name.toLowerCase())) {
//                        Log.d("hereherehere", name.toLowerCase());
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
        List<Shelter> matchingShelters = new ArrayList<>();
        for (Shelter s : shelterList) {
            if (s.getName().toLowerCase().contains(input.toLowerCase())) {
                matchingShelters.add(s);
            }
        }
        if (matchingShelters == null) {
            return new ArrayList<>();
        } else {
            return matchingShelters;
        }
    }

    public List<Shelter> findShelterByRestriction(Restriction rest) {
        List<Shelter> matches = new ArrayList<>();
        for (Shelter s : shelterList) {
            if (s.getRestrictionList().contains(rest)) {
                matches.add(s);
            }
        }
        return matches;
    }

    /**
     * finds the shelter by key
     * @param key the key of the shelter we are searching for
     * @returnthe shelter associated with the given key
     */
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
}
