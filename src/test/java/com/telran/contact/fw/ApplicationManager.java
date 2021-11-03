package com.telran.contact.fw;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class ApplicationManager {

	WebDriver driver; // type 'static' after typing @BeforeSuite

	UserHelper user; // created variable 'user'
	ContactHelper contact; // created variable 'contact'
	HomeHelper home; // created variable 'home'

	public UserHelper getUser() {
		return user;
	}

	public ContactHelper getContact() {
		return contact;
	}

	public HomeHelper getHome() {
		return home;
	}

	public void init() {
		driver = new ChromeDriver();
//        driver = new FirefoxDriver();
		driver.get("https://contacts-app.tobbymarshall815.vercel.app/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		user = new UserHelper(driver); // initialized UserHelper
		contact = new ContactHelper(driver); // initialized ContactHelper
		home = new HomeHelper(driver); // initialized HomeHelper
	}

	// isHomeComponentPresent, isElementPresent - checking preconditions.
	// For example:
	// 1. user logged
	// 2. logged user has a button LOGOUT in Header (checking an element LOGOUT)
	// 3. check in the methods locators

	public void stop() {
		driver.quit();
	}
}
