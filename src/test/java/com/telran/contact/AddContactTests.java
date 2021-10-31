package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {
    // preconditions:
    @BeforeMethod
    public void ensurePreconditions() {
        if (!isSignOutTabPresent()) { // user should be loggedIn - check Sign Out button displayed
            // if we don't see 'Sign Out button' - need to log in with user data, who was already registered
            clickOnLoginTab();
            login(new User()
                    .setEmail("krooos@gm.com")
                    .setPassword("Krooos12345~"));
        }
    }

    @Test
    public void addContactPositiveTest() {
        // create variable 'i' - different values for numbers
        // currentTimeMillis() - return current time
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600; // return current time, divide 3600
        addNewContact("Karl", "Adam", "12345" + i, "adam" + i + "@gm.com", "Koblenz", "torwart");
        // Assert - contact created
        Assert.assertTrue(isContactCreated("Karl"));
//        Assert.assertTrue(isContactCreated("12345678"));
    }
}

