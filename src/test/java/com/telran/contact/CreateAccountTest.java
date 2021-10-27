package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {
    // preconditions:
    //    ensurePreconditions() method:
    // 1. user should be logged out -
    //   1.1. button Login don't present
    //   1.2. click on Sign Out button

    //    registrationPositiveTest () method:
    // 2. click on Login
    // 3. fill registration form
    // 4. click on Registration button
    // 5. check Sign Out button displayed

    @BeforeMethod
    public void ensurePreconditions() {
        // if button Login don't present - click Sign out button
        // 1. button Login don't present
        // 2. click on Sign Out button

        // button Login don't present
        if (!isLoginTabPresent()) { // //a[contains(., 'LOGIN')]
            // click on Sign Out button
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        }
    }

    @Test
    public void registrationPositiveTest() {
//        // click on Login
        click(By.xpath("//a[contains(., 'LOGIN')]"));

//        driver.findElement(By.xpath("//a[contains(., 'LOGIN')]")).click();
        // make sure (ensure) that the element LOGIN is present on this page
        // take universal method isElementPresent()
        // find a locator of whole form with email, password fields
        // we need check and use Assert - confirmation that we have taken the right actions
        // make sure that the registration form is present
//        isElementPresent(By.cssSelector(".login_login__3EHKB")
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill registration form
        // krooos@gm.com
        // Krooos12345~
        // css: [placeholder='Email']
        // css: [placeholder='Password']
        // xpath: //button[contains(., 'Registration')]
        type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");
        type(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
        // CHECK! - in TestBase - @AfterMethod(enabled = false)
        // run test - check execute preconditions

        // click on Registration button
        click(By.xpath("//button[contains(., 'Registration')]"));
        // check Sign Out button displayed - make sure that the Sign Out button is present
        Assert.assertTrue(isSignOutTabPresent());
    }
}
