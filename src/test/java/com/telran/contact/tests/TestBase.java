package com.telran.contact.tests;

import com.telran.contact.fw.ApplicationManager;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

// parent class - always is "TestBase"
public class TestBase {

	protected static ApplicationManager app = new ApplicationManager(); // change 'final' on 'static'

	@BeforeSuite // run only 1 time. @BeforeSuite without 'static' wouldn't work
	public void setUp() {
		app.init();
	}

	@AfterSuite(enabled = true)
	public void tearDown() {
		app.stop();
	}
}
