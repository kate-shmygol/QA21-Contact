package com.telran.contact;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;
import java.util.List;

// parent class - always is "TestBase"
public class TestBase {
    static WebDriver driver; // type 'static' after typing @BeforeSuite

    @BeforeSuite // run only 1 time. @BeforeSuite without 'static' wouldn't work
    public void setUp() {
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app/home");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // isHomeComponentPresent, isElementPresent - checking preconditions.
    // For example:
    // 1. user logged
    // 2. logged user has a button LOGOUT in Header (checking an element LOGOUT)
    // 3. check in the methods locators

    // concrete method 1
    public boolean isHomeComponentPresent() {
        // findElementS! not findElement
        return driver.findElements(By.cssSelector("div:nth-child(2) > div > div")).size() > 0;
    }

    // concrete method 2 - try ... catch - alternative one for concrete method 1
    public boolean isHomeComponentPresent2() {
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
    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    // universal method 2 - try ... catch with locator
    public boolean isElementPresent2(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) { // choose NoSuchElementException org.openqa.selenium
            return false;
        }
    }

    @AfterSuite(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    // cleaning code - refactoring
    public void click(By locator) { // class 'By' responsible for the 'locator'
        // click on the Login tab
//        driver.findElement(By.xpath("//a[contains(., 'LOGIN')]")).click();
        // do variable locator = By.xpath("//a[contains(., 'LOGIN')]")
        // highlight "xpath" or "By.xpath("//a[contains(., 'LOGIN')]")" -> Refactor -> Introduce parameter... or Ctr + Alt + P
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public boolean isLoginTabPresent() {
        return isElementPresent(By.xpath("//a[contains(., 'LOGIN')]"));
    }

    public boolean isSignOutTabPresent() {
        return isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public boolean isLoginRegistrationFormPresent() {
        return isElementPresent(By.cssSelector(".login_login__3EHKB"));
    }

    public boolean isAlertPresent() {
        // special library 'Alert' with class 'Alert'
        // initialize 'Alert' with Selenium class 'WebDriverWait', which is waiting for Alert
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert(); // switchTo() - method of the class Alert, which redirects on the pop-up window Alert
            alert.accept(); // click on the 'ok' on the pop-up window Alert
            return true;
        }
    }

    // click on the Save button - 3-th way
    // invoke the class Actions and initialise it
    public void clickWithAction(By save) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElement(save);
        action.moveToElement(element).build().perform();
        element.click();
    }

    // click on the Save button - 2-nd way (if Selenium doesn't see 'Save' button)
    public void jump() {
        driver.findElement(By.cssSelector(".add_form__2rsm2 button")).sendKeys(Keys.CONTROL, Keys.END);
    }

    public void pause(int millis) {
        new WebDriverWait(driver, Duration.ofSeconds(millis));
//        new WebDriverWait(driver, Duration.ofMillis(millis));
    }

    public void clickOnSignOutButton() {
        click(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public void clickOnLoginTab() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public void createNewAccount(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
        // click on Registration button
        click(By.xpath("//button[contains(.,'Registration')]"));
    }

    public void addNewContact(String name, String surName, String phone, String email, String address, String description) {
        click(By.cssSelector("a:nth-child(5)"));
        type(By.cssSelector("[placeholder='Name']"), name); // name - [placeholder='Name'] = input:nth-child(1)
        type(By.cssSelector("input:nth-child(2)"), surName); // lastname
        type(By.cssSelector("input:nth-child(3)"), phone); // phone
        type(By.cssSelector("input:nth-child(4)"), email); // email
        type(By.cssSelector("input:nth-child(5"), address); // address
        type(By.cssSelector("input:nth-child(6)"), description); // description
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }

    public boolean isContactCreated(String text) {
        // check list of contacts
        List<WebElement> contacts = driver.findElements(By.xpath("//h2"));
//        List<WebElement> contacts = driver.findElements(By.xpath("//h3"));
        // going over the contacts (перебираем)
        for (WebElement el : contacts) {
            if (el.getText().contains(text))
                return true;
        }
        return false;
    }

    public void removeContact() {
        if (!isContactListEmpty()) {
            driver.findElement(By.cssSelector(".contact-item_card__2SOIM")).click();
            driver.findElement(By.xpath("//button[text()='Remove']")).click();
//            click(By.cssSelector(".contact-item_card__2SOIM"));
//            click(By.xpath("//button[text()='Remove']"));
        }
    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }

    public int sizeOfContacts() {
        // if amount more than 0, return list
        if (driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        } else {
            return sizeOfContacts();
//            return -1;
        }
    }

    public void login(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
        // submit Login - click on Login button
        // xpath: //button[contains(., 'Login')]
        click(By.xpath("//button[contains(., 'Login')]"));
    }
}
