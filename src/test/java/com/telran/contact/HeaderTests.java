package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// hometask - Create HeaderTests class(click on each tab for logged and unlogged user)
// 1. click on each tab (HOME, ABOUT) for logged user - check that we are on the needed page
// 2. click on each tab for unlogged user (CONTACT) - check that we are on the needed page
public class HeaderTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        // preconditions:
        // user should be logged out
        if (!isLoginTabPresent()) {
        // click on Sign Out button
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        }
    }

    @Test
    public void tabHomeTest () {
        // click on HOME
        click(By.xpath("//a[contains(., 'HOME')]"));
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) > div > div")));
        System.out.println("Tab HOME. Home Component: " + isElementPresent(By.cssSelector("div:nth-child(2) > div > div")));
    }

    @Test
    public void tabAboutTest () {
        // click on ABOUT
        click(By.xpath("//a[contains(., 'ABOUT')]"));
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) > div")));
        System.out.println("Tab ABOUT. Contacts Web Application: " + isElementPresent(By.cssSelector("div:nth-child(2) > div")));
    }

    public void userLogin () {
        // click on Login
        click(By.xpath("//a[contains(., 'LOGIN')]"));
        // make sure that the Login form is present
        Assert.assertTrue(isLoginRegistrationFormPresent());
        // fill Login form
        // krooos@gm.com
        // Krooos12345~
        type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");

        type(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
        // click on Login button
        // xpath: //button[contains(., 'Login')]
        click(By.xpath("//button[contains(., 'Login')]"));
        // check Sign Out button displayed - make sure that the Sign Out button is present
        Assert.assertTrue(isSignOutTabPresent());
    }
    // qwerty@qwe.com
    // qwerty123~
    @Test
    public void tabContactsTest () {
        userLogin();
         // click on CONTACTS
        click(By.xpath("//a[contains(., 'CONTACTS')]"));
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) > div")));
        System.out.println("Tab CONTACTS. No Contacts here: " + isElementPresent(By.cssSelector("div:nth-child(2) > div")));
    }

    @Test
    public void tabAddTest () {
        userLogin();
        // click on ADD
        click(By.xpath("//a[contains(., 'ADD')]"));
        Assert.assertTrue(isElementPresent(By.cssSelector("[placeholder='Name']")));
        System.out.println("Tab ADD. Placeholder 'Name:' " + isElementPresent(By.cssSelector("[placeholder='Name']")));
    }
}
