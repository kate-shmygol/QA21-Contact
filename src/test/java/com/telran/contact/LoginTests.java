package com.telran.contact;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        // preconditions:
        // login didn't present
        if (!isLoginTabPresent()) {
            // click on Sign Out button
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        }
    }

    // positive test
    @Test
    public void loginRegisteredUserPositiveTest() {
        // 1. click on the Login tab
        // 2. fill Login form
        // 3. submit Login
        // 4. Assert user loggedIn - make sure that the Sign Out button is present

        // click on the Login tab - copy from CreateAccountTest
        click(By.xpath("//a[contains(., 'LOGIN')]"));
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill Login form - copy from CreateAccountTest
        type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");
        type(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
        // submit Login - click on Login button
        // xpath: //button[contains(., 'Login')]
        click(By.xpath("//button[contains(., 'Login')]"));
        // Assert user loggedIn - check Sign Out button displayed
        Assert.assertTrue(isSignOutTabPresent());
    }

    // negative test - the same 'loginRegisteredUserPositiveTest()'
    // only checking instead 'Assert.assertTrue(isSignOutTabPresent())' - 'isAlertPresent()'

    @Test
    public void loginRegisteredUserNegativeWithWrongPasswordTest() {
        // click on the Login tab
        click(By.xpath("//a[contains(., 'LOGIN')]"));
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill Login form
        type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");
        type(By.cssSelector("[placeholder='Password']"), "Krooos12345");
        // submit Login
        click(By.xpath("//button[contains(., 'Login')]"));
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
