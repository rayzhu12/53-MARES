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

    public Shelter(String[] info) {
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
        }
    }

    public String toString() {
        return "[" + key + " " + name + " " + capacity + " " + restriction + " " + longitude + " "
                + latitude + " " + address + " " + specialNotes + " " + phoneNumber + "]";
    }
}


