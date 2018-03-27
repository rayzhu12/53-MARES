package com.example.michelleliu.homelessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirstRegistration extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText confirmPass;
    private Button nextRegister;
    private Button logIn;

    private String emailHolder;
    private String passwordHolder;
    private boolean EditTextStatus;

    private ProgressDialog progressDialog;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       email = (EditText) findViewById(R.id.email);
       password = (EditText) findViewById(R.id.password);

       nextRegister = (Button) findViewById(R.id.nextRegister);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(FirstRegistration.this);

        nextRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

            }
        });

        logIn = (Button) findViewById(R.id.logIn);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(FirstRegistration.this, MainActivity.class);
                startActivity(intent);
            }

        });
    }

    public void UserRegistrationFunction() {

        // Showing progress dialog at user registration time.
        progressDialog.setMessage("Please Wait, We are Registering Your Data on Server");
        progressDialog.show();

        // Creating createUserWithEmailAndPassword method and pass email and password inside it.
        mAuth.createUserWithEmailAndPassword(emailHolder, passwordHolder).
                addOnCompleteListener(FirstRegistration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

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

                    }
                });
    }

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

        if (TextUtils.isEmpty(emailHolder) || TextUtils.isEmpty(passwordHolder)) {

            EditTextStatus = false;

        } else {

            EditTextStatus = true;
        }

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() >= 8;
    }

}
