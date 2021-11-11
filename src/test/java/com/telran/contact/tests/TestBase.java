package com.telran.contact.tests;

import com.telran.contact.fw.ApplicationManager;
<<<<<<< Updated upstream
=======
import org.openqa.selenium.remote.BrowserType;
>>>>>>> Stashed changes
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.Arrays;

// parent class - always is "TestBase"
public class TestBase {

<<<<<<< Updated upstream
	protected static ApplicationManager app = new ApplicationManager(); // change 'final' on 'static'
=======
	protected static ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME)); // change 'final' on 'static'
>>>>>>> Stashed changes

	Logger logger = LoggerFactory.getLogger(TestBase.class);

	@BeforeSuite // run only 1 time. @BeforeSuite without 'static' wouldn't work
	public void setUp() {
		app.init();
	}

	@AfterSuite(enabled = true)
	public void tearDown() {
		app.stop();
	}

	@BeforeMethod
	public void startTest(Method m, Object[] p) {
		logger.info("Start test " + m.getName() + " with data: " + Arrays.asList(p));
	}

	@AfterMethod
	public void stopTest(ITestResult result) {
		if (result.isSuccess()) {
			logger.info("PASSED: test method " + result.getMethod().getMethodName());
		} else {
<<<<<<< Updated upstream
			logger.error("FAILED: test method " + result.getMethod().getMethodName());
=======
			logger.error("FAILED: test method " + result.getMethod().getMethodName()
			+ "Screenshot: " + app.getContact().takeScreenshot());
>>>>>>> Stashed changes
		}
		logger.info("============================================");
	}
}
