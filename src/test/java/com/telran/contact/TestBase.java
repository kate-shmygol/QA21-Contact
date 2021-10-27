package com.telran.contact;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

    @AfterMethod(enabled = false)
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
        //        click(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");
        //        click(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
        // do variable locator = By.cssSelector("[placeholder='Email']")
        // highlight "cssSelector" or "By.cssSelector("[placeholder='Email']")" -> Refactor -> Introduce parameter... or Ctr + Alt + P
        click(locator); // = driver.findElement(locator).click();
        driver.findElement(locator).clear(); // necessarily clear field Email! we don't know what a field is empty
        // highlight ' "krooos@gm.com" ' -> Refactor -> Introduce parameter... or Ctr + Alt + P
        // change variable 's' on the 'text'
        driver.findElement(locator).sendKeys(text);
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
        new WebDriverWait(driver, Duration.ofMillis(millis));
    }
}
