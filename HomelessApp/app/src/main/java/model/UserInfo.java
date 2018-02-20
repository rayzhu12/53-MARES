package model;

/**
 * Created by Emily Wang on 2/19/2018.
 */

public class UserInfo {
    private String name;
    private String email;
    private String password;
    private String typeOfUser;

    public UserInfo(String name, String email, String password, String typeOfUser) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.typeOfUser = typeOfUser;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUserType() {
        return this.typeOfUser;
    }
    public void setUserType(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public String toString() {
        return ("Name: " + name + " Username: " + email + " Password: " + password + " User Type: " + typeOfUser);
    }

    @Override
    public boolean equals(Object u2) {
        if (u2 == this) {
            return true;
        }
        if (! (u2 instanceof UserInfo)) {
            return false;
        }
        UserInfo user2 = (UserInfo) u2;
        return (user2.getName().equals(this.name) && user2.getEmail().equals(this.email) && user2.getPassword().equals(this.password) && user2.getUserType().equals(this.typeOfUser));
    }
 }