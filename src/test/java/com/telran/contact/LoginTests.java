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

    @Test
    public void loginRegisteredUserPositiveTest() {
        // 1. click on the Login tab
        // 2. fill Login form
        // 3. submit Login
        // 4. Assert user loggedIn

        // click on the Login tab
        click(By.xpath("//a[contains(., 'LOGIN')]"));
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill Login form
        type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");
        type(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
        // submit Login - click on Login button
        // xpath: //button[contains(., 'Login')]
        click(By.xpath("//button[contains(., 'Login')]"));
        // Assert user loggedIn - check Sign Out button displayed - make sure that the Sign Out button is present
        Assert.assertTrue(isSignOutTabPresent());
    }

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
        Assert.assertTrue(isAlertPresent());
    }

}
