package model;

/**
 * @author Emily Wang
 * @date 2/19/2018
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

    /**
     * the default constructor
     */
    public UserInfo(){

    }

    /**
     * the constructor
     * @param userID the user ID
     * @param name the name
     * @param age the age
     * @param number the number
     * @param typeOfUser the type of user
     */
    public UserInfo(String userID, String name, int age, String number, String typeOfUser) {
        this.name = name;
//        this.email = email;
//        this.password = password;
        this.age = age;
        this.number = number;
        this.typeOfUser = typeOfUser;
        this.numberOfBeds = 0;
    }

    /**
     * gets the name
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * sets the name
     * @param name the name
     */
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

    /**
     * gets the user ID
     * @return the user ID
     */
    public String getUserID(){return this.userID;}

    /**
     * gets the age
     * @return the age
     */
    public int getAge(){return this.age;}

    /**
     * sets the age
     * @param age the age
     */
    public void setAge(int age){this.age = age;}

    /**
     * gets the phone number
     * @return the phone number
     */
    public String getNumber(){return this.number;}

    /**
     * sets the phone number
     * @param phoneNumber the phone number
     */
    public void setNumber(String phoneNumber){number = phoneNumber;}

    /**
     * gets the gender
     * @return the gender
     */
    public String getGender(){return this.gender;}

    /**
     * sets the gender
     * @param gender the gender
     */
    public void setGender(String gender) {this.gender = gender;}

    /**
     * gets the number of children per shelter
     * @return the number of children per shelter
     */
    public int getNumberOfChildren(){return numberOfChildren;}

    /**
     * sets the number of children per shelter
     * @param numberOfChildren the number of children per shelter
     */
    public void setNumberOfChildren(int numberOfChildren){this.numberOfChildren = numberOfChildren;}

    /**
     * gets the user type
     * @return the type of user
     */
    public String getUserType() {
        return this.typeOfUser;
    }

    /**
     * sets the user type
     * @param typeOfUser the type of user
     */
    public void setUserType(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    /**
     * gets the number of beds
     * @return the number of beds
     */
    public int getNumberOfBeds(){return numberOfBeds;}

    /**
     * sets the number of beds
     * @param beds the number of beds
     */
    public void setNumberOfBeds(int beds){numberOfBeds = beds;}

    /**
     * gets the current shelter
     * @return the current shelter
     */
    public String getCurrentShelter(){return currentShelter;}

    /**
     * sets the current shelter
     * @param shelter the current shelter
     */
    public void setCurrentShelter(String shelter){currentShelter = shelter;}

    @Override
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


