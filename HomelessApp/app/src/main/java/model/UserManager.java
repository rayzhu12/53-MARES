package model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * @author Emily Wang
 */
public class UserManager {
    private final FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();


    private static final String TAG = "userManager";

    /**
     * the default constructor
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

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = mFirebaseDatabase.getReference();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.child("test1").exists()) {
                    Log.d(TAG, "true");
                }
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
        myRef.child("shelters").child("test1").setValue(6);
    }

//    private void showData(DataSnapshot dataSnapshot, String userID) {
//        UserInfo uInfo = new UserInfo();
//        uInfo.setName(dataSnapshot.child(userID).getValue(UserInfo.class).getName());
//        uInfo.setNumberOfBeds(dataSnapshot.child(userID).getValue(
//                UserInfo.class).getNumberOfBeds());
//        Log.d(TAG, "showData: name: " + uInfo.getName());
//        Log.d(TAG, "showData: bed; " + uInfo.getNumberOfBeds());
//    }
}
