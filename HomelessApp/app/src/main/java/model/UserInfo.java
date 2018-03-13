package model;

/**
 * Created by Emily Wang on 2/19/2018.
 */

public class UserInfo {
    private String name;
    private String gender;
    private int age;
    private String phoneNumber;
    private int numberOfChildren;
    private String typeOfUser;
    private int numberOfBeds;
    private String currentShelter;


    public UserInfo() {

    }
    public UserInfo(String name, int age, String gender, String phoneNumber, int numberofChildren, String typeOfUser) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.typeOfUser = typeOfUser;
        this.gender = gender;
        this.numberOfChildren = numberofChildren;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {return this.age;}
    public void setAge(int age) {this.age = age;}

    public String getPhoneNumber() {return this.phoneNumber;}
    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getGender() {return this.gender;}
    public void setGender(String gender) {this.gender = gender;}

    public int getNumberofChildren() {return numberOfChildren;}
    public void setNumberofChildren(int numberOfChildren) {numberOfChildren = numberOfChildren;}

    public String getUserType() {
        return this.typeOfUser;
    }
    public void setUserType(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    public int getNumberOfBeds() {return numberOfBeds;}
    public void setNumberOfBeds(int beds) {this.numberOfBeds = beds;}

    private String getCurrentShelter() {return currentShelter;}
    private void setCurrentShelter(String shelter) {this.currentShelter = shelter;}

    public String toString() {
        return ("Name: " + name + " User Type: " + typeOfUser);
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
        return (user2.getName().equals(this.name) && user2.getUserType().equals(this.typeOfUser));
    }
 }
