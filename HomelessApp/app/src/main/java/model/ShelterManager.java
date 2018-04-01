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
 * Created by michelleliu on 3/14/18.
 */

public class ShelterManager {
    private static final ShelterManager _instance = new ShelterManager();
    public static ShelterManager getInstance() { return _instance; }

    //private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;

    //replace with database
    List<Shelter> shelterList;




    public List<Shelter> getShelterList() {
        return shelterList;
    }
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

    // individual, family
    /*
    public List<Shelter> findShelterByFamilyType(FamilyType familyType) {
        List<Shelter> matches = new ArrayList<>();
        if (familyType.equals(FamilyType.FAMILY)) {
            for (Shelter s : shelterList) {
                if (s.getRestrictionList().contains(Restriction.FAMILIES)
                        || s.getRestrictionList().contains(Restriction.NEWBORNS)) {
                    matches.add(s);
                }
            }

        } else if (familyType.equals(FamilyType.INDIVIDUAL)) {
            for (Shelter s : shelterList) {
                if (s.getRestrictionList().contains(Restriction.WOMEN)
                        || s.getRestrictionList().contains(Restriction.MEN)) {
                    matches.add(s);
                }
            }
        }
        return matches;
    }


    // male, female, nonbinary
    public List<Shelter> findShelterByGender(Gender gender) {
        List<Shelter> matches = new ArrayList<>();
        for (Shelter s : shelterList) {
            if (s.getRestrictionList().contains(Restriction.FAMILIES)) {
                matches.add(s);
            } else if (gender.equals(Gender.MALE) && s.getRestrictionList()
                    .contains(Restriction.MEN)) {
                matches.add(s);
            } else if (gender.equals(Gender.FEMALE) && s.getRestrictionList()
                    .contains(Restriction.WOMEN)) {
                matches.add(s);
            }
        }

        return matches;
    }
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
