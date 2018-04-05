package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import model.CSVFile;
import model.FireBaseCallBack;
import model.Shelter;
import model.ShelterManager;
import model.UserInfo;
import model.UserManager;

/**
 * App activity
 * @author snack overflow
 */
public class AppActivity extends AppCompatActivity {

    private static final String TAG = "AppActivity";

    private FirebaseDatabase mFirebaseDatabase;
    // Creating FirebaseAuth.
    private FirebaseAuth firebaseAuth ;

    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private DatabaseReference secondRef;
    private String userID;

    private static final ShelterManager sm = ShelterManager.getInstance();
    private static List<Shelter> shelterList;

    private final List<String> shelterNames = new ArrayList<>();


    private final int[] nBed = new int[1];
    private final String[] sName = new String[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button updateData = findViewById(R.id.updatedata);
        updateData.setOnClickListener(view -> {
            finish();
            startActivity(new Intent(AppActivity.this, ShelterListActivity.class));
        });

        if (shelterList == null) {
            InputStream inputStream = getResources().openRawResource(R.raw.stats);
            CSVFile csvFile = new CSVFile(inputStream);
            shelterList = csvFile.read();
            sm.setShelterList(shelterList);
        }

        // Adding FirebaseAuth instance to FirebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");
        // FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // On activity start check whether there is user previously logged in or not.
        if (firebaseAuth.getCurrentUser() == null) {

            // Finishing current Profile activity.
            finish();

            // If user already not log in then Redirect to MainActivity .
            Intent intent = new Intent(AppActivity.this, MainActivity.class);
            startActivity(intent);

            // Showing toast message.
            Toast.makeText(AppActivity.this, "Please Log in to continue", Toast.LENGTH_LONG).show();

        }

        Button logout = findViewById(R.id.logout);
        // Adding click listener on logout button.
        logout.setOnClickListener(view -> {
            // Destroying login season.
            firebaseAuth.signOut();

            // Finishing current User Profile activity.
            finish();

            // Redirect to Login Activity after click on logout button.
            Intent intent = new Intent(AppActivity.this, MainActivity.class);
            startActivity(intent);

            // Showing toast message on logout.
            Toast.makeText(AppActivity.this, "Logged Out Successfully.", Toast.LENGTH_LONG).show();
        });

        mAuthListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                userID = user.getUid();
                Toast.makeText(AppActivity.this, "Successfully signed in with: "
                        + user.getEmail(), Toast.LENGTH_LONG).show();
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
                Toast.makeText(AppActivity.this, "Successfully signed out.",
                        Toast.LENGTH_LONG).show();
            }
            // ...
        };

        Button releaseBed = findViewById(R.id.release);
        // Adding click listener on logout button.
        releaseBed.setOnClickListener(view -> {
            UserManager manager = new UserManager();

            // Read from the database testing if this works
           myRef.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot dataSnapshot) {
                   nBed[0] = dataSnapshot.child(userID).getValue(UserInfo.class).getNumberOfBeds();

                   if (dataSnapshot.child(userID).child("currentShelter").getValue() != null) {
                       sName[0] = dataSnapshot.child(userID).child("currentShelter")
                               .getValue().toString();
                   }
                   myRef.child(userID).child("numberOfBeds").setValue(0);
                   myRef.child(userID).child("currentShelter").setValue(null);
                   if ((sName[0] != null) && (nBed[0] > 0)) {
                       updateShelter();
                   }
                   showData(dataSnapshot);
               }

               @Override
               public void onCancelled(DatabaseError databaseError) {
                   Log.d(TAG, "failed to read value");
               }
           });
        });

        secondRef = mFirebaseDatabase.getReference("shelters");
        readData(new FireBaseCallBack() {
            @Override
            public void onCallBack(List<String> list) {
                Log.d(TAG, shelterNames.toString());
            }
        });
    }

    /**
     * Uses the map
     * @param view the view
     */
    public void useMap(View view) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    private void updateShelter() {
        secondRef = mFirebaseDatabase.getReference("shelters");
        secondRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d("AppActivity", "secondRef");
                int cap = Integer.parseInt(dataSnapshot.child(sName[0]).getValue(Shelter.class)
                        .getCapacity());
                secondRef.child(sName[0]).child("capacity")
                        .setValue(Integer.toString(nBed[0] + cap));
                Toast.makeText(AppActivity.this, "You've released " + nBed[0]
                        + " bed(s) from " + sName[0], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void readData(FireBaseCallBack firebaseCallBack) {
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String shelterName = ds.child("name").getValue(String.class);
                    shelterNames.add(shelterName);
                }

                firebaseCallBack.onCallBack(shelterNames);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        };
        secondRef.addListenerForSingleValueEvent(valueEventListener);
    }


    private void showData(DataSnapshot dataSnapshot) {
        UserInfo uInfo = new UserInfo();
        uInfo.setName(dataSnapshot.child(userID).getValue(UserInfo.class).getName());
        uInfo.setNumberOfBeds(dataSnapshot.child(userID)
                .getValue(UserInfo.class).getNumberOfBeds());
        Log.d(TAG, "showData: name: " + uInfo.getName());
        Log.d(TAG, "showData: bed; " + uInfo.getNumberOfBeds());
    }

    @Override
    public void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            firebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
