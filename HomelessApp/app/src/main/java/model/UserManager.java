package model;

import android.util.Log;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Emily Wang on 3/26/2018.
 */

public class UserManager {
    private FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference myRef;


    private static final String TAG = "userManager";
    public UserManager() {

    }

    public void addNewUser(String id, String name, int age, String gender, String type) {
        UserInfo userInfo = new UserInfo(id, name, age, gender, type);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

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
        myRef.child("shelters").child("test1").setValue(6);
    }


    private void showData(DataSnapshot dataSnapshot, String userID) {
        UserInfo uInfo = new UserInfo();
        System.out.println(dataSnapshot.child(userID));
        uInfo.setName(dataSnapshot.child(userID).getValue(UserInfo.class).getName());
        uInfo.setNumberOfBeds(dataSnapshot.child(userID).getValue(UserInfo.class).getNumberOfBeds());
        Log.d(TAG, "showData: name: " + uInfo.getName());
        Log.d(TAG, "showData: bed; " + uInfo.getNumberOfBeds());
    }
}
