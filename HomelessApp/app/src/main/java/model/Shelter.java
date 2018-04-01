package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by michelleliu on 2/26/18.
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

    private String[] arrayInfo;

    public Shelter() {

    }

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

            arrayInfo = info;
        }
    }

    public List<Restriction> parseRestrictions(String inputString) {
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

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCapacity() {
        return capacity;
    }

    public List<Restriction> getRestrictionList() {
        return restrictionList;
    }

    public String getRestriction() { return restriction; }

    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getSpecialNotes() {
        return specialNotes;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String toString() {
        return "[" + key + " " + name + " " + capacity + " " + restriction + " " + longitude + " "
                + latitude + " " + address + " " + specialNotes + " " + phoneNumber + "]";
    }

    public String[] toArray() {
        return arrayInfo;
    }
}


