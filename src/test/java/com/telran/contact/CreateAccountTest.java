package com.telran.contact;

import org.openqa.selenium.By;
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
        // if button Login doesn't present - click Sign out button
        // 1. button Login doesn't present
        // 2. click on Sign Out button

        // button Login doesn't present

        // refactoring method 'isElementPresent(By.xpath("//a[contains(., 'LOGIN')]"))' on 'isLoginTabPresent()' method:
        // highlight 'isElementPresent(By.xpath("//a[contains(., 'LOGIN')]"))' -> Alt + Enter -> Extracted method
        // change name 'isElementPresent' (already exists) on the 'isLoginTabPresent()'
        // Keep original signature
        // always change 'private' on 'public' - because we'll transfer methods in TestBase class
        // if it stays the same ('private'), it will not reach this method
        if (!isLoginTabPresent()) { // //a[contains(., 'LOGIN')]
            // click on Sign Out button
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        }
    }

    //    registrationPositiveTest () method:
    // 1. click on Login
    // 2. fill registration form
    // 3. click on Registration button
    // 4. check Sign Out button displayed

    @Test
    public void registrationPositiveTest() {
        // 1. click on Login
        click(By.xpath("//a[contains(., 'LOGIN')]"));
        // make sure (ensure) that the element LOGIN is present on this page
        // take universal method 'isLoginRegistrationFormPresent()', which finds a locator (.login_login__3EHKB) of whole form with email, password fields
        // we need to check and use Assert - confirmation that we've taken the right actions
        // make sure that the registration form is present

        // refactoring method 'isElementPresent(By.cssSelector(".login_login__3EHKB"))' on 'isLoginRegistrationFormPresent()' method:
        // highlight 'isElementPresent(By.cssSelector(".login_login__3EHKB"))' -> Alt + Enter -> Extracted method
        // change name on the 'isLoginRegistrationFormPresent()'
        // Keep original signature
        // change 'private' on 'public'
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // 2. fill registration form
        // Email: krooos@gm.com
        // Password: Krooos12345~
        // css: [placeholder='Email']
        // css: [placeholder='Password']
        // xpath: //button[contains(., 'Registration')]

        // refactoring into type() method:
        // highlight 3 rows -> Alt + Enter -> Extracted method
        // change name 'extracted' on the 'type'
        // Keep original signature
        // change 'private' on 'public'
        type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com"); // method type(By locator, String text) - where to? 'locator', was? 'text'
        type(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
        // CHECK! - in TestBase - @AfterMethod(enabled = false)
        // run test - check executed preconditions

        // 3. submit Registration - click on Registration button
        click(By.xpath("//button[contains(., 'Registration')]"));
        // 4. check Sign Out button displayed - make sure that the Sign Out button is present

        // refactoring method 'isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"))' on 'isSignOutTabPresent()' method:
        // highlight 'isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"))' -> Alt + Enter -> Extracted method
        // change name on the 'isSignOutTabPresent()'
        // Keep original signature
        // change 'private' on 'public'
        Assert.assertTrue(isSignOutTabPresent());
    }

    // transferring methods to the parent class TestBase:
    // anywhere in the empty editor field (not in the methods or tests)
    // click RMB (right mouse button) -> Refactor -> Pull members Up...
    // never choose members with 2 locks - these are the tests
    // select isLoginTabPresent(), isSignOutTabPresent(), isLoginRegistrationFormPresent()
    // -> Refactor
}
