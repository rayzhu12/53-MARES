package com.example.michelleliu.homelessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    // Creating EditText .
    EditText email, password;

    // Creating button.
    Button LogIn, ButtonGoToRegistration;

    // Creating string to hold email and password .
    String EmailHolder, PasswordHolder ;

    // Creating Boolean to hold EditText empty true false value.
    Boolean EditTextEmptyCheck;

    // Creating Progress dialog.
    ProgressDialog progressDialog;

    // Creating FirebaseAuth object.
    FirebaseAuth firebaseAuth ;

    FirebaseDatabase userDatabase;
    DatabaseReference userDatabaseReference;

    // Creating Boolean variable that holds EditText is empty or not status.
    Boolean EditTextStatus ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
//        userDatabase = FirebaseDatabase.getInstance();
//        userDatabaseReference = userDatabase.getReference("users");

        // Assigning layout email ID and Password ID.
        email = (EditText)findViewById(R.id.EditText_User_EmailID);
        password = (EditText)findViewById(R.id.EditText_User_Password);
        //checkPassword = (EditText)findViewById(R.id.confirmPass);

        // Assign button layout ID.
        LogIn = (Button)findViewById(R.id.Button_SignIn);
        ButtonGoToRegistration = (Button)findViewById(R.id.Button_RegistrationActivity);

        // Creating object instance.
        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(MainActivity.this);


         //Checking if user already logged in before and not logged out properly.
        if(firebaseAuth.getCurrentUser() != null){

            // Finishing current Login Activity.
            finish();

            // Opening UserProfileActivity .
            Intent intent = new Intent(MainActivity.this, AppActivity.class);
            startActivity(intent);
        }

        // Adding click listener to Sign Up Button.
//        SignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                // Calling method to check EditText is empty or no status.
//                CheckEditTextIsEmptyOrNot();
//
//                // If EditText is true then this block with execute.
//                if(EditTextStatus){
//
//                    // If EditText is not empty than UserRegistrationFunction method will call.
//                    UserRegistrationFunction();
//
//                }
//                // If EditText is false then this block with execute.
//                else {
//
//                    Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();
//
//                }
//
//            }
//        });


        // Adding click listener to ButtonGoToLoginActivity button.
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CheckEditTextIsEmptyOrNot();

                if(EditTextEmptyCheck) {
                    LoginFunction();
                } else {
                    // If  EditTextEmptyCheck == false then toast display on screen.
                    Toast.makeText(MainActivity.this, "Please Fill All the Fields", Toast.LENGTH_LONG).show();
                }
            }
        });

        ButtonGoToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                Intent intent = new Intent(MainActivity.this, TestRegister.class);
                startActivity(intent);
            }
        });
    }

    public void CheckEditTextIsEmptyOrNot() {
        // Getting value form Email's EditText and fill into EmailHolder string variable.
        EmailHolder = email.getText().toString().trim();

        // Getting value form Password's EditText and fill into PasswordHolder string variable.
        PasswordHolder = password.getText().toString().trim();

        // Checking Both EditText is empty or not.
        if(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder))
        {

            // If any of EditText is empty then set value as false.
            EditTextEmptyCheck = false;

        }
        else {

            // If any of EditText is empty then set value as true.
            EditTextEmptyCheck = true ;

        }
    }

    // Creating login function.
    public void LoginFunction(){

        // Setting up message in progressDialog.
        progressDialog.setMessage("Please Wait");

        // Showing progressDialog.
        progressDialog.show();

        // Calling  signInWithEmailAndPassword function with firebase object and passing EmailHolder and PasswordHolder inside it.
        firebaseAuth.signInWithEmailAndPassword(EmailHolder, PasswordHolder)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        // If task done Successful.
                        if(task.isSuccessful()){

                            // Hiding the progress dialog.
                            progressDialog.dismiss();

                            // Closing the current Login Activity.
                            finish();


                            // Opening the UserProfileActivity.
                            Intent intent = new Intent(MainActivity.this, AppActivity.class);
                            startActivity(intent);
                        }
                        else {

                            // Hiding the progress dialog.
                            progressDialog.dismiss();

                            // Showing toast message when email or password not found in Firebase Online database.
                            Toast.makeText(MainActivity.this, "Email or Password Not found, Please Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    /*********************************************************************************************
     * NEED TO ADD METHODS THAT CHECK FOR PASSWORD RESTRICTIONS EX: 8 CHARACTERS, NEED A #....ETC
     ********************************************************************************************/
//
//    // Creating UserRegistrationFunction
//    public void UserRegistrationFunction(){
//
//        // Showing progress dialog at user registration time.
//        progressDialog.setMessage("Please Wait, We are Registering Your Data on Server");
//        progressDialog.show();
//
//        // Creating createUserWithEmailAndPassword method and pass email and password inside it.
//        firebaseAuth.createUserWithEmailAndPassword(EmailHolder, PasswordHolder).
//                addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        // Checking if user is registered successfully.
//                        if(task.isSuccessful()){
//
//                            // If user registered successfully then show this toast message.
//                            Toast.makeText(MainActivity.this,"User Registration Successfully",Toast.LENGTH_LONG).show();
//                            Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
//                            startActivity(intent);
//                            //firebaseAuth.signOut();
//
//                        }else{
//
//                            // If something goes wrong.
//                            Toast.makeText(MainActivity.this,"Something Went Wrong.",Toast.LENGTH_LONG).show();
//                        }
//
//                        // Hiding the progress dialog after all task complete.
//                        progressDialog.dismiss();
//
//                    }
//                });
//    }
//
//    public void CheckEditTextIsEmptyOrNot(){
//
//        boolean cancel = false;
//        View focusView = null;
//
//        // Check for a valid password, if the user entered one.
//        if (!isPasswordValid(password.getText().toString())) {
//            password.setError(getString(R.string.error_invalid_password));
//            focusView = password;
//            cancel = true;
//        }
//        if (TextUtils.isEmpty(password.getText().toString())) {
//            password.setError(getString(R.string.error_field_required));
//            focusView = password;
//            cancel = true;
//        }
//
//        if (TextUtils.isEmpty(checkPassword.getText().toString())) {
//            checkPassword.setError("This field is required.");
//            focusView = checkPassword;
//            cancel = true;
//        }
//
//        if (! (password.getText().toString().equals(checkPassword.getText().toString()))) {
//            checkPassword.setError("Passwords do not match.");
//            focusView = checkPassword;
//            cancel = true;
//        }
//
//        // Check for a valid email address.
//        if (TextUtils.isEmpty(email.getText().toString())) {
//            email.setError(getString(R.string.error_field_required));
//            focusView = email;
//            cancel = true;
//        }
//        if (!isEmailValid(email.getText().toString())) {
//            email.setError(getString(R.string.error_invalid_email));
//            focusView = email;
//            cancel = true;
//        }
//
//        if (cancel) {
//            // There was an error; don't attempt login and focus the first
//            // form field with an error.
//            focusView.requestFocus();
//        } else {
//
//            // Getting name and email from EditText and save into string variables.
//            EmailHolder = email.getText().toString().trim();
//            PasswordHolder = password.getText().toString().trim();
//        }
//
//            if (TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder)) {
//
//                EditTextStatus = false;
//
//            } else {
//
//                EditTextStatus = true;
//            }
//
//    }
//
//    private boolean isEmailValid(String email) {
//        //TODO: Replace this with your own logic
//        // return email.equals("user");
//        return email.contains("@");
//    }
//
//    private boolean isPasswordValid(String password) {
//        //TODO: Replace this with your own logic
//        // return password.equals("pass");
//        return password.length() >= 8;
//    }

}
