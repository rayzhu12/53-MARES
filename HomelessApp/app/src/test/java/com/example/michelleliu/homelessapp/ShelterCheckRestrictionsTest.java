package com.example.michelleliu.homelessapp;

import org.junit.Before;
import org.junit.Test;

import model.Restriction;
import model.Shelter;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * @author Angelina Suwoto
 */

public class ShelterCheckRestrictionsTest {
    private Shelter shelter;

    @Before
    public void setUp() {
        shelter = new Shelter();
    }

    @Test
    public void femaleTest() {
        assertTrue(shelter.parseRestrictions("womenyay").contains(Restriction.FEMALE));
        assertTrue(shelter.parseRestrictions("hello woman").contains(Restriction.FEMALE));
        assertTrue(shelter.parseRestrictions("for the female").contains(Restriction.FEMALE));
        assertFalse(shelter.parseRestrictions("for men").contains(Restriction.FEMALE));
    }

    @Test
    public void maleTest() {
        assertTrue(shelter.parseRestrictions("men's shelter").contains(Restriction.MALE));
        assertTrue(shelter.parseRestrictions("hello man").contains(Restriction.MALE));
        assertTrue(shelter.parseRestrictions("for the males").contains(Restriction.MALE));
        assertFalse(shelter.parseRestrictions("for females").contains(Restriction.MALE));
    }

    @Test
    public void familyTest() {
        assertTrue(shelter.parseRestrictions("we welcome families").contains(Restriction.FAMILIES));
        assertTrue(shelter.parseRestrictions("perfect for a family").contains(Restriction.FAMILIES));
        assertFalse(shelter.parseRestrictions("only for children").contains(Restriction.FAMILIES));
    }

    @Test
    public void childrenTest() {
        assertTrue(shelter.parseRestrictions("only for children").contains(Restriction.CHILDREN));
        assertTrue(shelter.parseRestrictions("newborn infants").contains(Restriction.CHILDREN));
        assertFalse(shelter.parseRestrictions("for adults").contains(Restriction.CHILDREN));
    }

    @Test
    public void yaTest() {
        assertTrue(shelter.parseRestrictions("welcomes young adults").contains(Restriction.YOUNG_ADULTS));
        assertFalse(shelter.parseRestrictions("for adults and youth").contains(Restriction.FEMALE));
    }

    @Test
    public void anyoneTest() {
        assertTrue(shelter.parseRestrictions("we welcome anyone").contains(Restriction.FEMALE));
        assertTrue(shelter.parseRestrictions("we welcome anyone").contains(Restriction.MALE));
        assertTrue(shelter.parseRestrictions("we welcome anyone").contains(Restriction.FAMILIES));
        assertTrue(shelter.parseRestrictions("we welcome anyone").contains(Restriction.CHILDREN));
        assertTrue(shelter.parseRestrictions("we welcome anyone").contains(Restriction.YOUNG_ADULTS));
        assertTrue(shelter.parseRestrictions("we welcome anyone").contains(Restriction.NONBINARY));
    }

    @Test
    public void nbTest() {
        assertTrue(shelter.parseRestrictions("a nonbinary friendly shelter").contains(Restriction.NONBINARY));
        assertFalse(shelter.parseRestrictions("for adults and youth").contains(Restriction.NONBINARY));
    }

}
