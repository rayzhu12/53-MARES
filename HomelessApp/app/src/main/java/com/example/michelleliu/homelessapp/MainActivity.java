package com.example.michelleliu.homelessapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONObject;

/**
 * Main activity
 * @author snack overflow
 */
public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;

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

    private int counter;

    private boolean run = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        callbackManager = CallbackManager.Factory.create();

        LoginButton fbLogIn = (LoginButton) findViewById(R.id.fb_login_button);
        fbLogIn.setReadPermissions("email", "public_profile");
        fbLogIn.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.i("Facebook Auth", "Hello" + loginResult.getAccessToken().getToken());
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("FacebookAuth", "onCancel section");
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("Facebook Auth", "in error section", error);
            }
        });

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
                LoginFunction(logIn);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("Facebook Auth", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Log.w("Facebook Auth", "signInWithCredential", task.getException());
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                } else {
                    String uid = task.getResult().getUser().getUid();
                    String name = task.getResult().getUser().getDisplayName();
                    String email = task.getResult().getUser().getEmail();
                    Log.d("Facebook Auth", "Name: " + name + ", Email: " + email);

                    // Finishing current Main Activity.
                    finish();

                    // Opening the Login Activity using Intent.
                    Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                    startActivity(intent);
                }
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

    private void LoginFunction(Button logIn) {

        // Setting up message in progressDialog.
        progressDialog.setMessage("Please Wait");

        // Showing progressDialog.
        progressDialog.show();

        // Calling  signInWithEmailAndPassword function with Firebase object
        // and passing EmailHolder and PasswordHolder inside it.
        firebaseAuth.signInWithEmailAndPassword(EmailHolder, PasswordHolder)
                .addOnCompleteListener(this, task -> {

                    if (run) {
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
                            if (counter == 2) {
                                Toast alert = Toast.makeText(MainActivity.this, "Login Disabled for 1 minute.", Toast.LENGTH_SHORT);
                                alert.show();

                                run = false;
                                final Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {   @Override
                                    public void run() {
                                        counter = 0;
                                        run = true;
                                        logIn.setEnabled(true);
                                    }
                                }, 60000);
                            } else {
                                // Hiding the progress dialog.
                                progressDialog.dismiss();
                                Toast alert = Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT);
                                alert.show();
                                counter++;
                            };
                        }
                    } else {
                        Toast alert = Toast.makeText(MainActivity.this, "Login Disabled for 1 minute.", Toast.LENGTH_SHORT);
                        alert.show();
                    }

                });
    }
}
