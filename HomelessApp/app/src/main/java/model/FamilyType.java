package model;

/**
 * Created by michelleliu on 3/16/18.
 */

public enum FamilyType {
    INDIVIDUAL ("Individual"), FAMILY ("Family");

    private final String name;

    FamilyType(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
