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

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import model.UserManager;

/**
 * First registration
 * @author snack overflow
 */
public class FirstRegistration extends AppCompatActivity {

    private EditText email;
    private EditText password;

    private String emailHolder;
    private String passwordHolder;
    private boolean EditTextStatus;

    UserManager manager = new UserManager();

    private ProgressDialog progressDialog;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_registration);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(FirstRegistration.this);

        Button nextRegister = findViewById(R.id.nextRegister);
        nextRegister.setOnClickListener(view -> {
            emailHolder = email.getText().toString().trim();
            passwordHolder = password.getText().toString().trim();
            String error = manager.checkRegisterInfo(emailHolder, passwordHolder);

            CheckErrorMessage(error);

            // If EditText is true then this block with execute.
            if(EditTextStatus) {
                // Showing progress dialog at user registration time.
                progressDialog.setMessage("Please Wait, We are Registering Your Data on Server");
                progressDialog.show();

                Task<AuthResult> t = manager.registerUser(emailHolder, passwordHolder);
                t.addOnCompleteListener(FirstRegistration.this, task -> {
                    // Checking if user is registered successfully.
                    if (task.isSuccessful()) {

                        // If user registered successfully then show this toast message.
                        Toast.makeText(FirstRegistration.this
                                , "User Registration Successfully", Toast.LENGTH_LONG).show();
                        finish();
                        Intent intent = new Intent(FirstRegistration.this
                                , RegistrationActivity.class);
                        startActivity(intent);


                    } else {
                        // If something goes wrong.
                        Toast.makeText(FirstRegistration.this, "Something Went Wrong."
                                , Toast.LENGTH_LONG).show();
                    }
                    // Hiding the progress dialog after all task complete.
                    progressDialog.dismiss();

                });
            }
        });

        Button logIn = findViewById(R.id.logIn);
        logIn.setOnClickListener(view -> {
            finish();
            Intent intent = new Intent(FirstRegistration.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void CheckErrorMessage(String error){

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (error.equals("Password must have at least 8 characters")) {
            password.setError(getString(R.string.error_invalid_password));
            focusView = password;
            cancel = true;
        }
        if (error.equals("Password cannot be empty")) {
            password.setError(getString(R.string.error_field_required));
            focusView = password;
            cancel = true;
        }

        // Check for a valid email address.
        if (error.equals("Email cannot be empty")) {
            email.setError(getString(R.string.error_field_required));
            focusView = email;
            cancel = true;
        }
        if (error.equals("Email must contain @")) {
            email.setError(getString(R.string.error_invalid_email));
            focusView = email;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            EditTextStatus = true;
        }
    }
}
