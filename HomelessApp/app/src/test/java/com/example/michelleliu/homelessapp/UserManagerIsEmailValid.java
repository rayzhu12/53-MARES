package com.example.michelleliu.homelessapp;

import org.junit.Before;
import org.junit.Test;

import model.UserManager;


import static org.junit.Assert.*;

/**
 * @author rayzhu12
 */
public class UserManagerIsEmailValid {
    private UserManager manager;

    @Before
    public void setUp() {
        manager = new UserManager();
    }

    @Test
    public void noAtSign() {
        assertFalse(manager.isEmailValid("insertemailhere"));
    }
    @Test
    public void containsExclamationMark() {
        assertFalse(manager.isEmailValid("insertemailhere@!"));
    }
    @Test
    public void containsQuestionMark() {
        assertFalse(manager.isEmailValid("insertemailhere@?"));
    }
    @Test
    public void correctEmail() {
        assertTrue(manager.isEmailValid("insertemailhere@"));
    }

}