package model;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.example.michelleliu.homelessapp.FirstRegistration;
import com.example.michelleliu.homelessapp.R;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.Task;

/**
 * @author Emily Wang
 */
public class UserManager {
    private String email;
    private String password;


    private static final String TAG = "userManager";

    /**
     * the default constructor needed for Firebase
     */
    public UserManager() {

    }

    /**
     * the constructor
     * @param id the ID
     * @param name the name
     * @param age the age
     * @param gender the gender
     * @param type the type
     */
    public void addNewUser(String id, String name, int age, String gender, String type) {
        UserInfo userInfo = new UserInfo(name, age, gender, type);
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be usable.
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Log.d(TAG, "onDataChange: Added information to database: \n" +
                        dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        myRef.child("users").child(id).setValue(userInfo);
    }

    /**\
     * Registers the user in Firebase
     * @param email the email of the user
     * @param password the password of the user
     * @return the task auth result
     */
    public Task<AuthResult> registerUser(String email, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    /**
     * check if the register email works
     * @param email the email
     * @param password the password
     * @return a string
     */
    public String checkRegisterInfo(String email, String password) {
        this.email = email;
        this.password = password;

        return CheckEditTextIsEmptyOrNot();
    }


    private String CheckEditTextIsEmptyOrNot(){
        if (password.equals("")) {
            return "Password cannot be empty";
        }

        // Check for a valid password, if the user entered one.
        if (!isPasswordValid(password)) {
            return "Password must have at least 8 characters";
        }

        if (email.equals("")) {
            return "Email cannot be empty";
        }

        if (!isEmailValid(email)) {
            return "Email must contain @";
        }

        return "";
    }

    /**
     * checks if email is valid or not
     * @param email the email provided
     * @return whether or not the email is valid
     */
    public boolean isEmailValid(String email) {
        return email.contains("@") && !(email.contains("!") || email.contains("?"));
    }

    private boolean isPasswordValid(CharSequence password) {
        return password.length() >= 8;
    }

}
