package com.example.michelleliu.homelessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class FirstRegistration extends AppCompatActivity {

    private EditText email;
    private EditText password;
    //private EditText confirmPass;

    private String emailHolder;
    private String passwordHolder;
    private boolean EditTextStatus;

    private ProgressDialog progressDialog;

    //private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       email = findViewById(R.id.email);
       password = findViewById(R.id.password);

        Button nextRegister = findViewById(R.id.nextRegister);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(FirstRegistration.this);

        nextRegister.setOnClickListener(view -> {
            // Calling method to check EditText is empty or no status.
            CheckEditTextIsEmptyOrNot();

            // If EditText is true then this block with execute.
            if(EditTextStatus){
                // If EditText is not empty than UserRegistrationFunction method will call.
                UserRegistrationFunction();
            }
            // If EditText is false then this block with execute.
            else {
                Toast.makeText(FirstRegistration.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
            }

        });

        Button logIn = findViewById(R.id.logIn);
        logIn.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(FirstRegistration.this, MainActivity.class);
            startActivity(intent);
        });
    }

    /**
     * Function that handles user registration.
     */
    public void UserRegistrationFunction() {

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Registering Your Data on Server");
        progressDialog.show();

        // Creating createUserWithEmailAndPassword method and pass email and password inside it.
        mAuth.createUserWithEmailAndPassword(emailHolder, passwordHolder).
                addOnCompleteListener(FirstRegistration.this, task -> {

                    // Checking if user is registered successfully.
                    if(task.isSuccessful()){

                        // If user registered successfully then show this toast message.
                        Toast.makeText(FirstRegistration.this,"User Registration Successfully",Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(FirstRegistration.this, RegistrationActivity.class);
                        startActivity(intent);


                    }else{

                        // If something goes wrong.
                        Toast.makeText(FirstRegistration.this,"Something Went Wrong.",Toast.LENGTH_LONG).show();
                    }

                    // Hiding the progress dialog after all task complete.
                    progressDialog.dismiss();

                });
    }

    /**
     * Checks whether or not email and password are valid
     * upon registration before adding user to the database.
     */
    public void CheckEditTextIsEmptyOrNot(){

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password.getText().toString())) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            cancel = true;
        }
        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
        }

//        if (!(password.getText().toString().equals(confirmPass.getText().toString()))) {
//            confirmPass.setError("The passwords must match.");
//            focusView = confirmPass;
//            cancel = true;
//        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email.getText().toString())) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        }
        if (!isEmailValid(email.getText().toString())) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            // Getting name and email from EditText and save into string variables.
            emailHolder = email.getText().toString().trim();
            passwordHolder = password.getText().toString().trim();
        }


        EditTextStatus = !(TextUtils.isEmpty(emailHolder) || TextUtils.isEmpty(passwordHolder));

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

}
