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
        for (String str : female) {
            assertTrue(Arrays.asList(shelterNames).contains(str));
        }
    }

    @Test
    public void correctMale() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.MALE);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        for (String str : male) {
            assertTrue(Arrays.asList(shelterNames).contains(str));
        }
    }

    @Test
    public void correctFamilies() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.FAMILIES);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        for (String str : families) {
            assertTrue(Arrays.asList(shelterNames).contains(str));
        }
    }

    @Test
    public void correctYa() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.YOUNG_ADULTS);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        for (String str : ya) {
            assertTrue(Arrays.asList(shelterNames).contains(str));
        }
    }

    @Test
    public void correctChildren() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.CHILDREN);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        for (String str : children) {
            assertTrue(Arrays.asList(shelterNames).contains(str));
        }
    }

    @Test
    public void correctNonbinary() {
        List<Shelter> shelters = shelter.findShelterByRestriction(Restriction.NONBINARY);
        String[] shelterNames = new String[shelters.size()];
        for (int i = 0; i < shelters.size(); i++) {
            shelterNames[i] = shelters.get(i).getName();
        }
        for (String str : nonbinary) {
            assertTrue(Arrays.asList(shelterNames).contains(str));
        }
    }
}