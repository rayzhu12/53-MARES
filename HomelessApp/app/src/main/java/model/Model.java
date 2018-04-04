package model;

import android.support.compat.BuildConfig;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Emily Wang
 * @date 2/19/2018
 */
public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all users */
    private List<UserInfo> userInfo;
    private List<Shelter> shelterList;

    /**
     * make a new model
     */
    private Model() {
        userInfo = new ArrayList<>();
        shelterList = new ArrayList<>();
    }

    /**
     * @return a list of the users in the app
     */
    public List<UserInfo> getUserInfo() { return userInfo; }

    /**
     * @return a list of the shelters as Shelter objects
     */
    public List<Shelter> getShelterList() { return shelterList; }

    /**
     * add a user to the app.  checks if the user is already entered
     *
     * uses O(n) linear search for user
     *
     * @param user  the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean checkUser(UserInfo user) {
//        for (UserInfo u: userInfo) {
////            System.out.println(userInfo.size());
////            System.out.println(u.equals(user));
//            if (u.getEmail().toString().equals(user.getEmail().toString())) {
//                return false;
//            }
//        }
        Log.d("test", "added");
        userInfo.add(user);
        return true;
    }

    /**
     * add a shelter to the app.  checks if the shelter is already entered
     *
     * uses O(n) linear search for user
     *
     * @param shelter  the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean checkShelter(Shelter shelter) {
        for (Shelter s : shelterList) {
            if (shelter.getKey() == s.getKey()) {
                return false;
            }
        }
        shelterList.add(shelter);
        Log.d("test", "added Shelter " + shelter.getName());
        return true;
    }

    /**
     * Returns the Shelter object given a key
     * @param key the key that is searched for
     * @return the Shelter that corresponds to the input key
     */
    public Shelter findShelterById(int key) {
        Shelter returned = null;
        for (Shelter s : shelterList) {
            if (s.getKey() == key) {
                returned = s;
            }
        }
        return returned;
    }

    public void printArray() {

        for (UserInfo u: userInfo) {
            String us = u.toString();
            Log.d("users", us);
        }
    }
}
