package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michelle Liu
 */
public class Shelter implements Serializable {
    private int key;
    private String name;
    private String capacity;
    private String restriction;
    private List<Restriction> restrictionList;
    private float longitude;
    private float latitude;
    private String address;
    private String specialNotes;
    private String phoneNumber;

    /**
     * default constructor for this class
     */
    public Shelter() {

    }

    /**
     * constructor for this class
     * @param info the array of attributes of each shelter
     */
    public Shelter(String[] info) {
        if (info == null) {
            throw new NullPointerException("Null information cannot be made into a Shelter.");
        }
        if (info.length == 9) {
            key = Integer.parseInt(info[0]);
            name = info[1];
            capacity = info[2];
            restriction = info[3];
            restrictionList = parseRestrictions(info[3]);
            longitude = Float.parseFloat(info[4]);
            latitude = Float.parseFloat(info[5]);
            address = info[6];
            specialNotes = info[7];
            phoneNumber = info[8];

        }
    }

    /**
     * parses strings into restriction objects
     * @param inputString the string to be parsed
     * @return list of parsed restrictions
     */
    private List<Restriction> parseRestrictions(String inputString) {
        List<Restriction> restrictionList = new ArrayList<>();
        if (inputString.toLowerCase().contains("women")) {
            restrictionList.add(Restriction.FEMALE);
        } else if (inputString.toLowerCase().contains("men")) {
            restrictionList.add(Restriction.MALE);
        }
        if (inputString.toLowerCase().contains("families") ||
                inputString.toLowerCase().contains("anyone")) {
            restrictionList.add(Restriction.FAMILIES);
        }
        if (inputString.toLowerCase().contains("newborn")) {
            restrictionList.add(Restriction.CHILDREN);
        }
        if (inputString.toLowerCase().contains("adult") ||
                inputString.toLowerCase().contains("veteran")) {
            restrictionList.add(Restriction.YOUNG_ADULTS);
            restrictionList.add(Restriction.FEMALE);
            restrictionList.add(Restriction.MALE);
        }

        return restrictionList;
    }

    /**
     * gets the key of the shelter
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * gets the name of the shelter
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * gets the capacity of the shelters
     * @return the capacity
     */
    public String getCapacity() {
        return capacity;
    }

    /**
     * gets the restriction list of the shelters
     * @return the restriction list
     */
    public List<Restriction> getRestrictionList() {
        return restrictionList;
    }

    /**
     * gets the restrictions of the shelter
     * @return the restrictions
     */
    public String getRestriction() { return restriction; }

    /**
     * gets the longitude of the shelter
     * @return the longitude
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * gets the latitude of the shelter
     * @return the latitude
     */
    public float getLatitude() {
        return latitude;
    }

    /**
     * gets the address of the shelter
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * gets special notes from the shelter
     * @return the special notes
     */
    public String getSpecialNotes() {
        return specialNotes;
    }

    /**
     * gets the phone number of the shelter
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "[" + key + " " + name + " " + capacity + " " + restriction + " " + longitude + " "
                + latitude + " " + address + " " + specialNotes + " " + phoneNumber + "]";
    }

//    /**
//     * prints the array in a manner readable to the user
//     * @return a returnable version of the array
//     */
//    public String[] toArray() {
//        return arrayInfo;
//    }
}


