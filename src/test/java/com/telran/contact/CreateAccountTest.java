package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTest extends TestBase {
    // preconditions:
    // user should be logged out
    @BeforeMethod
    public void ensurePreconditions() {
        if (!isElementPresent(By.xpath("//a[contains(., 'LOGIN')]"))) {
            // login don't present
            // somename123@mail.comre
            // Somepassword123$

            driver.findElement(By.xpath("//button[contains(.,'Sign Out')]"));
            // //a[contains(., 'LOGIN')]
            // click on logout button

        }
    }

    @Test
    public void registrationPositiveTest () {
        // click on Login
        driver.findElement(By.xpath("//a[contains(., 'LOGIN')]")).click();
        // ...
        Assert.assertTrue(isElementPresent(By.cssSelector(".login_login__3EHKB")));
        // fill registration form
        // css: [placeholder='Email']
        // css: [placeholder='Password']
        // xpath: //button[contains(., 'Registration')]
        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("krooos@gm.com");

        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("Krooos12345~");
        // run test - check execute preconditions

        // click on Registration button
        driver.findElement(By.xpath("//button[contains(., 'Registration')]")).click();
        // check Logout button displayed
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }
}
