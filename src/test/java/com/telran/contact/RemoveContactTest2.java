package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest2 extends TestBase {
    // preconditions:
    @BeforeMethod
    public void ensurePreconditions() {
        // user should be loggedIn - check Sign Out button displayed
        if (!isSignOutTabPresent()) {
            // if we don't see 'Sign Out button' - need to log in with user data, who was already registered
            clickOnLoginTab();
            login(new User()
                    .setEmail("krooos@gm.com")
                    .setPassword("Krooos12345~"));
        }
        // adding a contact
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600; // return current time, divide 3600
        addNewContact("Karl", "Adam", "12345" + i, "adam" + i + "@gm.com", "Koblenz", "torwart");
        // Assert - contact created
    }

    @Test
    public void removeContactPositiveTest() {
        // 1. create 2 variables - before removing and after removing to compare lists
        // compare of lists before and after
        int sizeBefore = sizeOfContacts();
        System.out.println(sizeBefore);
        pause(2000);
//        Thread.sleep(2000);
        removeContact();
        int sizeAfter = sizeOfContacts();
        System.out.println(sizeAfter);
        Assert.assertEquals(sizeBefore, sizeAfter);
    }
}
