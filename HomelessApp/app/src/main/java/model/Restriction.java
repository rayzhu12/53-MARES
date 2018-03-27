package model;

/**
 * Created by michelleliu on 3/26/18.
 */

public enum Restriction {
    WOMEN ("Women"), FAMILIES ("Families with children"), MEN("Men"), NEWBORNS("Newborn");

    private final String name;

    Restriction(String name) {
        this.name = name;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }
}
