package com.telran.contact.fw;

import com.telran.contact.tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

//	WebDriver driver; // type 'static' after typing @BeforeSuite

	String browser;

//	WebDriver driver; // type 'static' after typing @BeforeSuite

	EventFiringWebDriver driver;

	UserHelper user; // created variable 'user'
	ContactHelper contact; // created variable 'contact'
	HomeHelper home; // created variable 'home'

	public ApplicationManager(String browser) {
		this.browser = browser;
	}

	public static class MyListener extends AbstractWebDriverEventListener {

		Logger logger = LoggerFactory.getLogger(TestBase.class);

		@Override
		public void beforeFindBy(By by, WebElement element, WebDriver driver) {
			logger.info("Start search " + by);
		}

		@Override
		public void afterFindBy(By by, WebElement element, WebDriver driver) {
			logger.info(by + " found.");
		}

		@Override
		public void onException(Throwable throwable, WebDriver driver) {
			logger.error(throwable.toString());
		}
	}

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

//		driver = new ChromeDriver();
//        driver = new FirefoxDriver();

//		driver = new ChromeDriver();
//        driver = new FirefoxDriver();
		if (browser.equals(BrowserType.CHROME)) {
			driver = new EventFiringWebDriver(new ChromeDriver());
		} else  if (browser.equals(BrowserType.FIREFOX)) {
			driver = new EventFiringWebDriver(new FirefoxDriver());
		}

		driver.get("https://contacts-app.tobbymarshall815.vercel.app/home");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		user = new UserHelper(driver); // initialized UserHelper
		contact = new ContactHelper(driver); // initialized ContactHelper
		home = new HomeHelper(driver); // initialized HomeHelper

		driver.register(new MyListener());
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
