package com.example.michelleliu.homelessapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import model.Restriction;
import model.Shelter;
import model.ShelterManager;

import static org.junit.Assert.*;
/**
 * @author Sarah
 */

public class ShelterManagerFindRestrictionTest {
    private ShelterManager shelter;
    String[] female = {"My Sister's House", "The Atlanta Day Center for Women & Children",
            "Eden Village", "Hope Atlanta"};
    String[] male = {"The Shepherd's Inn", "Fuqua Hall", "Hope Atlanta", "Gateway Center"};
    String[] nonbinary = {"Hope Atlanta"};
    String[] families = {"Atlanta's Children Center", "Our House", "Nicholas House",
            "Hope Atlanta"};
    String[] ya = {"Covenant House Georgia", "Hope Atlanta", "Young Adult Guidance Center"};
    String[] children = {"My Sister's House", "The Atlanta Day Center for Women & Children",
            "Atlanta's Children Center", "Eden Village", "Our House",
            "Covenant House Georgia", "Hope Atlanta"};
    @Before
    public void setUp() {shelter = new ShelterManager();}
    @Test
    public void correctFemale() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.FEMALE);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        assertArrayEquals(female, shelterNames);
    }

    @Test
    public void correctMale() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.MALE);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        assertArrayEquals(male, shelterNames);
    }

    @Test
    public void correctFamilies() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.FAMILIES);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        assertArrayEquals(families, shelterNames);
    }

    @Test
    public void correctYa() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.YOUNG_ADULTS);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        assertArrayEquals(ya, shelterNames);
    }

    @Test
    public void correctChildren() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.CHILDREN);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        assertArrayEquals(children, shelterNames);
    }

    @Test
    public void correctNonbinary() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.NONBINARY);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        assertArrayEquals(nonbinary, shelterNames);
    }
}