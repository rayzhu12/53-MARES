package model;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Emily Wang on 2/19/2018.
 */

public class UserInfo {
    private String name;
    private String gender;
//    private String email;
//    private String password;
    private int age;
    private String number;
    private String typeOfUser;
    private int numberOfChildren;
    private int numberOfBeds;
    private String currentShelter;
    private String userID;

    public UserInfo(){

    }

    public UserInfo(String userID, String name, int age, String number, String typeOfUser) {
        this.name = name;
//        this.email = email;
//        this.password = password;
        this.age = age;
        this.number = number;
        this.typeOfUser = typeOfUser;
        this.numberOfBeds = 0;
        this.currentShelter = null;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    public String getEmail() {
//        return this.email;
//    }
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPassword() {
//        return this.password;
//    }
//    public void setPassword(String password) {
//        this.password = password;
//    }

    public String getUserID(){return this.userID;}

    public int getAge(){return this.age;}
    public void setAge(int age){this.age = age;}

    public String getNumber(){return this.number;}
    public void setNumber(String phoneNumber){number = phoneNumber;}

    public String getGender(){return this.gender;}
    public void setGender(String gender) {this.gender = gender;}

    public int getNumberOfChildren(){return numberOfChildren;}
    public void setNumberOfChildren(int numberOfChildren){this.numberOfChildren = numberOfChildren;}

    public String getUserType() {
        return this.typeOfUser;
    }
    public void setUserType(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public int getNumberOfBeds(){return numberOfBeds;}
    public void setNumberOfBeds(int beds){numberOfBeds = beds;}

    public String getCurrentShelter(){return currentShelter;}
    public void setCurrentShelter(String shelter){currentShelter = shelter;}

    public String toString() {
        return ("Name: " + name + " User Type: " + typeOfUser);
    }

    /***************************************************************
     * Need to update this if it's still necessary
     ***************************************************************/
    @Override
    public boolean equals(Object u2) {
        if (u2 == this) {
            return true;
        }
        if (! (u2 instanceof UserInfo)) {
            return false;
        }
        UserInfo user2 = (UserInfo) u2;
        return (user2.getName().equals(this.name) && user2.getUserType().equals(this.typeOfUser));
    }
}


