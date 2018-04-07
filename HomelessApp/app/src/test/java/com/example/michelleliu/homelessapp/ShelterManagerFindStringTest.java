package com.example.michelleliu.homelessapp;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Shelter;
import model.ShelterManager;

import static org.junit.Assert.*;

/**
 * @author Michelle Liu
 */

public class ShelterManagerFindStringTest {
    static List<Shelter> fullList;
    static ShelterManager sm;
    static ShelterManager smTest;

    @Before
    public void setUp() {
        sm = ShelterManager.getInstance();
        fullList = sm.getShelterList();
        List<Shelter> first3shelterList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            first3shelterList.add(fullList.get(i));
        }
        smTest.setShelterList(first3shelterList);
    }

    /*
    public List<Shelter> findShelterByString(String input) {
        List<Shelter> matchingShelters = new ArrayList<>();
        for (Shelter s : shelterList) {
            if (s.getName().toLowerCase().contains(input.toLowerCase())) {
                matchingShelters.add(s);
            }
        }
        return matchingShelters;
    }
    */

    @Test
    public void findShelterThere() throws Exception {
        List<Shelter> shelterListFirstOnly = new ArrayList<>();
        shelterListFirstOnly.add(fullList.get(0));
        assertEquals(shelterListFirstOnly, smTest.findShelterByString("Atlanta"));
    }

    @Test
    public void findShelterNotThere() throws Exception {
        assertEquals(new ArrayList<Shelter>(), smTest.findShelterByString("Fuqua"));
    }
}
