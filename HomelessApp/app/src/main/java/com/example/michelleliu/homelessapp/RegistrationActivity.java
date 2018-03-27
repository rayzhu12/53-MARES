package com.example.michelleliu.homelessapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

import model.Model;
import model.UserInfo;
import model.UserManager;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "registration";

    private EditText name;
    private EditText gender;
    private EditText age;

    private String userID;
    private String nameHolder;
    private String genderHolder;
    private int ageHolder;

    private Boolean EditTextStatus;

    private Spinner typeOfUser;
    private Button register;
    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    public enum adminOrUser {
        Admin,
        User;
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        // return email.equals("user");
        if (email.equals("user")) {
            return true;
        }
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        // return password.equals("pass");
        if (password.equals("pass")) {
            return true;
        }
        return password.length() >= 8;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        name = (EditText) findViewById(R.id.Name);
        age = (EditText) findViewById(R.id.Age);
        gender = (EditText) findViewById(R.id.Gender);

        typeOfUser = (Spinner) findViewById(R.id.typeOfUser);
        ArrayAdapter<String> adapter2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, adminOrUser.values());
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeOfUser.setAdapter(adapter2);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    userID = user.getUid();
                    Toast.makeText(RegistrationActivity.this, "Successfully signed in with: " + user.getEmail(), Toast.LENGTH_LONG).show();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    Toast.makeText(RegistrationActivity.this, "Successfully signed out.", Toast.LENGTH_LONG).show();
                }
                // ...
            }
        };

        register = (Button) findViewById(R.id.create_account);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: Submit pressed.");
                otherFields();

                Log.d(TAG, "onClick: Attempting to submit to database: \n" +
                        "name: " + nameHolder + "\n" +
                        "gender: " + genderHolder + "\n" +
                        "age: " + ageHolder + "\n"
                );

                if (EditTextStatus) {
                    UserManager manage = new UserManager();
                    manage.addNewUser(userID, nameHolder, ageHolder, genderHolder, typeOfUser.getSelectedItem().toString());
                    Toast.makeText(RegistrationActivity.this, "User added to database", Toast.LENGTH_LONG).show();
                    name.setText("");
                    gender.setText("");
                    age.setText("");

                    // Finishing current Login Activity.
                    finish();

                    // Opening UserProfileActivity .
                    Intent intent = new Intent(RegistrationActivity.this, AppActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(RegistrationActivity.this, "Fill out all the fields", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void otherFields(){

        boolean cancel = false;
        View focusView = null;


        if (TextUtils.isEmpty(name.getText().toString())) {
            name.setError(getString(R.string.error_field_required));
            focusView = name;
            cancel = true;
        }
        if (TextUtils.isEmpty(age.getText().toString())) {
            age.setError(getString(R.string.error_field_required));
            focusView = age;
            cancel = true;
        }
        if (TextUtils.isEmpty(gender.getText().toString())) {
            gender.setError(getString(R.string.error_field_required));
            focusView = gender;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            nameHolder = name.getText().toString();
            ageHolder = Integer.parseInt(age.getText().toString());
            genderHolder = gender.getText().toString();
        }

        if (TextUtils.isEmpty(nameHolder) || TextUtils.isEmpty(genderHolder)) {

            EditTextStatus = false;

        } else {

            EditTextStatus = true;
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

}
