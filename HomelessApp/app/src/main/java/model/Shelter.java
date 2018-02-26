package model;

/**
 * Created by michelleliu on 2/26/18.
 */

public class Shelter {
    private int key;
    private String name;
    private String capacity;
    private String restriction;
    private float longitude;
    private float latitude;
    private String address;
    private String specialNotes;
    private String phoneNumber;

    private String[] arrayInfo;

    public Shelter(String[] info) {
        if (info == null) {
            throw new NullPointerException("Null information cannot be made into a Shelter.");
        }
        if (info.length == 9) {
            key = Integer.parseInt(info[0]);
            name = info[1];
            capacity = info[2];
            restriction = info[3];
            longitude = Float.parseFloat(info[4]);
            latitude = Float.parseFloat(info[5]);
            address = info[6];
            specialNotes = info[7];
            phoneNumber = info[8];

            arrayInfo = info;
        }
    }

    //todo: add other getters
    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCapacity() {
        return capacity;
    }

    public String getRestriction() {
        return restriction;
    }

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


