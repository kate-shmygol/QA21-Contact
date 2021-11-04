package com.telran.contact.fw;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperBase {

	WebDriver driver; // type 'static' after typing @BeforeSuite

	public HelperBase(WebDriver driver) {
		this.driver = driver;
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

	public boolean isAlertPresent() {
		// special library 'Alert' with class 'Alert'
		// initialize 'Alert' with Selenium class 'WebDriverWait', which is waiting for Alert
		Alert alert = new WebDriverWait(driver, 10).until(ExpectedConditions.alertIsPresent());
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

	public void pause(int millis) {
		new WebDriverWait(driver, millis);
	}
}
