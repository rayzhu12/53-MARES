package model;

import android.support.compat.BuildConfig;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emily Wang on 2/19/2018.
 */

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    /** holds the list of all courses */
    private List<UserInfo> userInfo;

    /**
     * make a new model
     */
    private Model() {
        userInfo = new ArrayList<>();
    }

    /**
     * @return a list of the users in the app
     */
    public List<UserInfo> getUserInfo() { return userInfo; }

    /**
     * add a user to the app.  checks if the user is already entered
     *
     * uses O(n) linear search for user
     *
     * @param user  the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean checkUser(UserInfo user) {
        for (UserInfo u: userInfo) {
            System.out.println(userInfo.size());
            System.out.println(u.equals(user));
            if (u.equals(user)) {
                return false;
            }
        }
        Log.d("test", "added");
        userInfo.add(user);
        return true;
    }

    public void printArray() {

        for (UserInfo u: userInfo) {
            String us = u.toString();
            Log.d("users", us);
        }
    }
}
