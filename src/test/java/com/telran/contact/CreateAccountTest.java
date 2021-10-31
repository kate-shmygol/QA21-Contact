package com.telran.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// changes on GitHub - https://github.com/kate-shmygol/QA21-Contact/commit/4a1a9920837c1930767a945cb93d5e0c62313cb6

public class CreateAccountTest extends TestBase {
    // preconditions:
    //    ensurePreconditions() method:
    // 1. user should be logged out
    //   1.1. button Login doesn't present
    //   1.2. click on Sign Out button

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isLoginTabPresent()) { // //a[contains(., 'LOGIN')]
            // click on Sign Out button
            clickOnSignOutButton();
        }
    }

    //    registrationPositiveTest () method:
    // 1. click on Login
    // 2. fill registration form
    // 3. click on Registration button
    // 4. check Sign Out button displayed

    @Test
    public void registrationPositiveTest() {
        // click on Login
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill registration form
        createNewAccount(new User()
                .setEmail("krooos@gm.com")
                .setPassword("Krooos12345~"));
        Assert.assertTrue(isSignOutTabPresent());
    }

    @Test
    public void registrationNegativeWithoutPasswordTest() {
        // click on Login
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill registration form
        createNewAccount(new User()
                .setEmail("krooos@gm.com"));
        // check Logout button displayed
        Assert.assertTrue(isAlertPresent());
    }

    // transferring methods to the parent class TestBase:
    // anywhere in the empty editor field (not in the methods or tests)
    // click RMB (right mouse button) -> Refactor -> Pull members Up...
    // never choose members with 2 locks - these are the tests
    // select isLoginTabPresent(), isSignOutTabPresent(), isLoginRegistrationFormPresent()
    // -> Refactor
}
