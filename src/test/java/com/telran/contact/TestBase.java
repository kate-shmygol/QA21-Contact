package com.telran.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

// parent class - always is "TestBase"
public class TestBase {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // concrete method 1
    public boolean isHomeComponentPresent() {
        // findElementS! not findElement
        return driver.findElements(By.cssSelector("div:nth-child(2) > div > div")).size() > 0;
    }

    // concrete method 2 - try ... catch - alternative one for concrete method 1
    public boolean isHomeComponentPresent2 () {
        try {
            // findElement! not findElementS
            // if you will find that element - true
            driver.findElement(By.cssSelector("div:nth-child(2) > div > div"));
            return true;
            // else - catch exception
        } catch (NoSuchElementException exception) { // choose NoSuchElementException org.openqa.selenium
            return false;
        }
    }

    // universal method 1 from concrete method 1 with locator - isHomeComponentPresent()
    public boolean isElementPresent (By locator) {
        return driver.findElements(locator).size() > 0;
    }

    // universal method 2 - try ... catch with locator
    public boolean isElementPresent2 (By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) { // choose NoSuchElementException org.openqa.selenium
            return false;
        }
    }

    // isHomeComponentPresent, isElementPresent - checking preconditions.
    // For example:
    // 1. user logged
    // 2. logged user has a button LOGOUT in Header (checking an element LOGOUT)
    // 3. check in the methods locators


    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }
}
