package com.telran.contact;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // preconditions:
        // login didn't present
        if (!isLoginTabPresent()) {
            // click on Sign Out button
            clickOnSignOutButton();
        }
    }

    // positive test
    @Test(priority = 2)
    public void loginRegisteredUserPositiveTest() {
        // 1. click on the Login tab
        // 2. fill Login form
        // 3. submit Login
        // 4. Assert user loggedIn - make sure that the Sign Out button is present

        // click on the Login tab - copy from CreateAccountTest
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill Login form - copy from CreateAccountTest
        login(new User()
                .setEmail("krooos@gm.com")
                .setPassword("Krooos12345~"));
        // Assert user loggedIn - check Sign Out button displayed
        Assert.assertTrue(isSignOutTabPresent());
    }

    // negative test - the same 'loginRegisteredUserPositiveTest()'
    // only checking instead 'Assert.assertTrue(isSignOutTabPresent())' - 'isAlertPresent()'

    @Test(priority = 1)
    public void loginRegisteredUserNegativeWithWrongPasswordTest() {
        // click on the Login tab
        clickOnLoginTab();
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill Login form
        login(new User()
                .setEmail("krooos@gm.com")
                .setPassword("Krooos12345"));
        // Assert user loggedIn
        // isAlertPresent() - catch a window with alert
        Assert.assertTrue(isAlertPresent());
    }
    // transferring method 'isAlertPresent' to the parent class TestBase:
    // anywhere in the empty editor field (not in the methods or tests)
    // click RMB (right mouse button) -> Refactor -> Pull members Up...
    // never choose members with 2 locks - these are the tests
    // select isAlertPresent()
    // -> Refactor
}
