package com.example.michelleliu.homelessapp;

import org.junit.Before;
import org.junit.Test;

import model.UserManager;
import model.UserInfo;


import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

/**
 * @author Emily Wang
 */

public class UserManagerCheckRegisterTest {
    private UserManager manager;

    @Before
    public void setUp() {
        manager = new UserManager();
    }

    @Test
    public void verifyRightInfo() {
        String message = manager.checkRegisterInfo("a@gmail.com", "12345678");
        assertEquals("", message);
    }

    @Test
    public void wrongEmailFormat() {
        String message = manager.checkRegisterInfo("abc", "thyjulksa");
        assertEquals("Email must contain @", message);
    }

    @Test
    public void emptyEmail() {
        String message = manager.checkRegisterInfo("", "abcdefghij");
        assertEquals("Email cannot be empty", message);
    }

    @Test
    public void shortPassword() {
        String message = manager.checkRegisterInfo("ui@gmail.com", "abc");
        assertEquals("Password must have at least 8 characters", message);
    }

    public void emptyPassword() {
        String message = manager.checkRegisterInfo("test@gmail.com", "");
        assertEquals("Password cannot be empty", message);
    }
}
