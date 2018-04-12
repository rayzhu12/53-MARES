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
 * @author Michelle
 */
public class ShelterManagerFindStringTest {
    private ShelterManager shelter;
    /*
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
            */
    String[] allShelterNames;
    String[] hasStringF;
    String[] hasStringH;
    String[] empty = new String[0];




    @Before
    public void setUp() {
        shelter = new ShelterManager();
        allShelterNames = new String[shelter.getShelterList().size()];
        for (int i = 0; i < shelter.getShelterList().size(); i++) {
            allShelterNames[i] = shelter.getShelterList().get(i).getName();
        }
        hasStringF = new String[]{allShelterNames[1], allShelterNames[3], allShelterNames[12]};
        hasStringH = new String[]{allShelterNames[0], allShelterNames[1], allShelterNames[2],
                allShelterNames[3], allShelterNames[4], allShelterNames[6], allShelterNames[7],
                allShelterNames[8], allShelterNames[9], allShelterNames[0], allShelterNames[12]};
    }

    @Test
    public void testStringF() {
        List<Shelter> sheltersWithF = shelter.findShelterByString("f");
        String[] shelterNamesWithF = new String[sheltersWithF.size()];
        for (int i = 0; i < sheltersWithF.size(); i++) {
            shelterNamesWithF[i] = sheltersWithF.get(i).getName();
        }
        assertArrayEquals(hasStringF, shelterNamesWithF);
    }

    @Test
    public void testStringH() {
        List<Shelter> sheltersWithH = shelter.findShelterByString("h");
        String[] shelterNamesWithH = new String[sheltersWithH.size()];
        for (int i = 0; i < sheltersWithH.size(); i++) {
            shelterNamesWithH[i] = sheltersWithH.get(i).getName();
        }
        assertArrayEquals(hasStringH, shelterNamesWithH);
    }

    @Test
    public void testNotThere() {
        List<Shelter> sheltersWithZ = shelter.findShelterByString("z");
        String[] shelterNamesWithZ = new String[sheltersWithZ.size()];
        for (int i = 0; i < sheltersWithZ.size(); i++) {
            shelterNamesWithZ[i] = sheltersWithZ.get(i).getName();
        }
        assertArrayEquals(empty, shelterNamesWithZ);
    }

    @Test
    public void testEmptyString() {
        List<Shelter> sheltersWithEmpty = shelter.findShelterByString("");
        String[] shelterNamesWithEmpty = new String[sheltersWithEmpty.size()];
        for (int i = 0; i < sheltersWithEmpty.size(); i++) {
            shelterNamesWithEmpty[i] = sheltersWithEmpty.get(i).getName();
        }
        assertArrayEquals(empty, shelterNamesWithEmpty);
    }

    @Test
    public void testStringSpace() {
        List<Shelter> sheltersWithSpace = shelter.findShelterByString(" ");
        String[] shelterNamesWithSpace = new String[sheltersWithSpace.size()];
        for (int i = 0; i < sheltersWithSpace.size(); i++) {
            shelterNamesWithSpace[i] = sheltersWithSpace.get(i).getName();
        }
        assertArrayEquals(allShelterNames, shelterNamesWithSpace);
    }
}