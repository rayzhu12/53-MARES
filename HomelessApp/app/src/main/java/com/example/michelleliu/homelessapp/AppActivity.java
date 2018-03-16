package com.example.michelleliu.homelessapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AppActivity extends AppCompatActivity {

    // Creating FirebaseAuth.
    FirebaseAuth firebaseAuth ;

    // Creating FirebaseAuth.
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        Button updateData = (Button) findViewById(R.id.updatedata);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppActivity.this, ShelterListActivity.class));
            }
        });

        // Adding FirebaseAuth instance to FirebaseAuth object.
        firebaseAuth = FirebaseAuth.getInstance();

        // On activity start check whether there is user previously logged in or not.
        if(firebaseAuth.getCurrentUser() == null){

            // Finishing current Profile activity.
            finish();

            // If user already not log in then Redirect to LoginActivity .
            Intent intent = new Intent(AppActivity.this, LoginActivity.class);
            startActivity(intent);

            // Showing toast message.
            Toast.makeText(AppActivity.this, "Please Log in to continue", Toast.LENGTH_LONG).show();

        }

        // Adding firebaseAuth current user info into firebaseUser object.
        firebaseUser = firebaseAuth.getCurrentUser();

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
                Intent intent = new Intent(AppActivity.this, LoginActivity.class);
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
    }

}
