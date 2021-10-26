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
        if (!isElementPresent(By.xpath("//a[contains(., 'LOGIN')]"))) {
        // click on Sign Out button
            driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();
        }
    }

    @Test
    public void tabHomeTest () {
        // click on HOME
        driver.findElement(By.xpath("//a[contains(., 'HOME')]")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) > div > div")));
        System.out.println("Tab HOME. Home Component: " + isElementPresent(By.cssSelector("div:nth-child(2) > div > div")));
    }

    @Test
    public void tabAboutTest () {
        // click on ABOUT
        driver.findElement(By.xpath("//a[contains(., 'ABOUT')]")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) > div")));
        System.out.println("Tab ABOUT. Contacts Web Application: " + isElementPresent(By.cssSelector("div:nth-child(2) > div")));
    }

    public void userLogin () {
        // click on Login
        driver.findElement(By.xpath("//a[contains(., 'LOGIN')]")).click();
        // make sure that the Login form is present
        Assert.assertTrue(isElementPresent(By.cssSelector(".login_login__3EHKB")));
        // fill Login form
        // krooos@gm.com
        // Krooos12345~
        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("krooos@gm.com");

        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("Krooos12345~");
        // click on Login button
        // xpath: //button[contains(., 'Login')]
        driver.findElement(By.xpath("//button[contains(., 'Login')]")).click();
        // check Sign Out button displayed - make sure that the Sign Out button is present
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }
    // qwerty@qwe.com
    // qwerty123~
    @Test
    public void tabContactsTest () {
        userLogin();
         // click on CONTACTS
        driver.findElement(By.xpath("//a[contains(., 'CONTACTS')]")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("div:nth-child(2) > div")));
        System.out.println("Tab CONTACTS. No Contacts here: " + isElementPresent(By.cssSelector("div:nth-child(2) > div")));
    }

    @Test
    public void tabAddTest () {
        userLogin();
        // click on ADD
        driver.findElement(By.xpath("//a[contains(., 'ADD')]")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector("[placeholder='Name']")));
        System.out.println("Tab ADD. Placeholder 'Name:' " + isElementPresent(By.cssSelector("[placeholder='Name']")));
    }



}
