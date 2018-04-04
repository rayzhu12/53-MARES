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


public class MainActivity extends AppCompatActivity {

    // Creating EditText .
    private EditText email, password ;

    // Creating button.
    Button LogIn, ButtonGoToRegistration;

    // Creating string to hold email and password .
    private String EmailHolder, PasswordHolder ;

    //Creating Boolean to hold EditText empty true false value
    private Boolean EditTextEmptyCheck;

    // Creating Progress dialog.
    private ProgressDialog progressDialog;

    // Creating FirebaseAuth object.
    private FirebaseAuth firebaseAuth ;
    //private FirebaseAuth.AuthStateListener mAuthListener;
    //FirebaseDatabase userDatabase;
    //DatabaseReference userDatabaseReference;

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
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        // Assign button layout ID.
        LogIn = findViewById(R.id.sign_in);
        ButtonGoToRegistration = findViewById(R.id.new_user);

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
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling method to check EditText is empty or no status.
                CheckEditTextIsEmptyOrNot();

                // If EditText is true then this block with execute.
                if(EditTextEmptyCheck){

                    // If EditText is not empty than UserRegistrationFunction method will call.
                    LoginFunction();

                }
                // If EditText is false then this block with execute.
                else {

                    Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }

            }
        });


        // Adding click listener to ButtonGoToLoginActivity button.
        ButtonGoToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Finishing current Main Activity.
                finish();

                // Opening the Login Activity using Intent.
                Intent intent = new Intent(MainActivity.this, FirstRegistration.class);
                startActivity(intent);

            }
        });


    }


    /**
     * Checks whether or not the input is empty.
     */
    public void CheckEditTextIsEmptyOrNot(){
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

    /**
     * Sign-in with Fire Base.
     */
    public void LoginFunction() {

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
                        if (task.isSuccessful()) {

                            // Hiding the progress dialog.
                            progressDialog.dismiss();

                            // Closing the current Login Activity.
                            finish();


                            // Opening the UserProfileActivity.
                            Intent intent = new Intent(MainActivity.this, AppActivity.class);
                            startActivity(intent);
                        } else {

                            // Hiding the progress dialog.
                            progressDialog.dismiss();

                            // Showing toast message when email or password not found in Firebase Online database.
                            Toast.makeText(MainActivity.this, "Email or Password Not found, Please Try Again", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
