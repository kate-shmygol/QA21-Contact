package com.telran.contact.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class HomeHelper extends HelperBase {
	public HomeHelper(WebDriver driver) {
		super(driver);
	}

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
}
