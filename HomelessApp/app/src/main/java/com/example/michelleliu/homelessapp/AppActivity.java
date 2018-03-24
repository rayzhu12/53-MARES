package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import model.UserInfo;


public class AppActivity extends AppCompatActivity {

    private static final String TAG = "ViewDatabsae";

    private FirebaseDatabase mFirebaseDatabase;
    // Creating FirebaseAuth.
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    String userID;

    // Creating FirebaseAuth.
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button updateData = (Button) findViewById(R.id.updatedata);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppActivity.this, DataActivity.class));
            }
        });

        // Adding FirebaseAuth instance to FirebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");

        // On activity start check whether there is user previously logged in or not.
        if (firebaseAuth.getCurrentUser() == null) {

            // Finishing current Profile activity.
            finish();

            // If user already not log in then Redirect to LoginActivity .
            Intent intent = new Intent(AppActivity.this, MainActivity.class);
            startActivity(intent);

            // Showing toast message.
            Toast.makeText(AppActivity.this, "Please Log in to continue", Toast.LENGTH_LONG).show();

        }

        // Adding firebaseAuth current user info into firebaseUser object.
        firebaseUser = firebaseAuth.getCurrentUser();
        userID = firebaseUser.getUid();

        Button logout = (Button) findViewById(R.id.logout);
        // Adding click listener on logout button.
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Destroying login season.
                firebaseAuth.signOut();

                // Finishing current User Profile activity.
                finish();

                // Redirect to Login Activity after click on logout button.
                Intent intent = new Intent(AppActivity.this, MainActivity.class);
                startActivity(intent);

                // Showing toast message on logout.
                Toast.makeText(AppActivity.this, "Logged Out Successfully.", Toast.LENGTH_LONG).show();
            }
        });


//        Button logout = (Button) findViewById(R.id.logout);
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(AppActivity.this, MainActivity.class));
//            }
//        });

    mAuthListener =new FirebaseAuth.AuthStateListener()

    {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                toastMessage("Successfully signed in with: " + user.getEmail());
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out");
                toastMessage("Successfully signed out.");
            }
            // ...
        }

    };

        Button reserveBed = (Button) findViewById(R.id.reserve);
        // Adding click listener on logout button.
        reserveBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange (DataSnapshot dataSnapshot){
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        showData(dataSnapshot);
                    }

                    @Override
                    public void onCancelled (DatabaseError error){
                        // Failed to read value
                        Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });



//        // Read from the database
//        myRef.addValueEventListener(new
//
//        ValueEventListener() {
//            @Override
//            public void onDataChange (DataSnapshot dataSnapshot){
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                showData(dataSnapshot);
//            }
//
//            @Override
//            public void onCancelled (DatabaseError error){
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            UserInfo uInfo = new UserInfo();
            System.out.println(ds.child(userID));
            uInfo.setName(ds.child(userID).getValue(UserInfo.class).getName());
            uInfo.setNumberOfBeds(ds.child(userID).getValue(UserInfo.class).getNumberOfBeds());
            Log.d(TAG, "showData: name: " + uInfo.getName());
            Log.d(TAG, "showData: bed; " + uInfo.getNumberOfBeds());
        }
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


    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
