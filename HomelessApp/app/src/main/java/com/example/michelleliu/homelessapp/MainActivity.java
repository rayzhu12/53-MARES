package com.example.michelleliu.homelessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Main activity
 * @author snack overflow
 */
public class MainActivity extends AppCompatActivity {

    // Creating EditText .
    private EditText email;
    private EditText password ;

    // Creating string to hold email and password .
    private String EmailHolder;
    private String PasswordHolder ;

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
    //Boolean EditTextStatus ;

    // animation
    Animation frombottom;

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
        Button logIn = findViewById(R.id.sign_in);
        Button buttonGoToRegistration = findViewById(R.id.new_user);
        Button forgotPassword = findViewById(R.id.forgotPass);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.frombottom);
        logIn.setAnimation(frombottom);
        buttonGoToRegistration.setAnimation(frombottom);
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
        logIn.setOnClickListener(view -> {

            // Calling method to check EditText is empty or no status.
            CheckEditTextIsEmptyOrNot();

            // If EditText is true then this block with execute.
            if(EditTextEmptyCheck){

                // If EditText is not empty than UserRegistrationFunction method will call.
                LoginFunction();

            }
            // If EditText is false then this block with execute.
            else {

                Toast.makeText(MainActivity.this, "Please fill all form fields.",
                        Toast.LENGTH_LONG).show();

            }

        });


        // Adding click listener to ButtonGoToLoginActivity button.
        buttonGoToRegistration.setOnClickListener(view -> {

            // Finishing current Main Activity.
            finish();

            // Opening the Login Activity using Intent.
            Intent intent = new Intent(MainActivity.this, FirstRegistration.class);
            startActivity(intent);

        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

                Intent intent = new Intent(MainActivity.this, PasswordRecoveryActivity.class);
                startActivity(intent);
            }
        });
    }


    private void CheckEditTextIsEmptyOrNot(){
        // Getting value form Email's EditText and fill into EmailHolder string variable.
        EmailHolder = email.getText().toString().trim();

        // Getting value form Password's EditText and fill into PasswordHolder string variable.
        PasswordHolder = password.getText().toString().trim();

        // Checking Both EditText is empty or not.
        EditTextEmptyCheck = !(TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(PasswordHolder));
    }

    private void LoginFunction() {

        // Setting up message in progressDialog.
        progressDialog.setMessage("Please Wait");

        // Showing progressDialog.
        progressDialog.show();

        // Calling  signInWithEmailAndPassword function with Firebase object
        // and passing EmailHolder and PasswordHolder inside it.
        firebaseAuth.signInWithEmailAndPassword(EmailHolder, PasswordHolder)
                .addOnCompleteListener(this, task -> {

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

                        // Showing toast message when email or password not found in
                        // Firebase Online database.
                        Toast.makeText(MainActivity.this,
                                "Email or Password Not found, Please Try Again",
                                Toast.LENGTH_LONG).show();
                    }
                });
    }
}
