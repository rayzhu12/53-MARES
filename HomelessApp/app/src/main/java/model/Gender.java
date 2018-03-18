package model;

/**
 * Created by michelleliu on 3/16/18.
 */

public enum Gender {
    MALE("Male"), FEMALE("Female"), NONBINARY("Nonbinary");

    private final String name;

    Gender(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}
