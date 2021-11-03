package com.telran.contact.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OpenHomePageTest extends TestBase {

	@Test
	public void homePageTest() throws InterruptedException {
		System.out.println("Site opened!");
		Thread.sleep(2000);
//        System.out.println("HomeComponent: " + isHomeComponentPresent());
		System.out.println("HomeComponent: " + app.getHome().isElementPresent(By.cssSelector("div:nth-child(2) > div > div")));
	}
	// transferring methods to the parent class TestBase:
	// anywhere in the empty editor field
	// click RMB (right mouse button) -> Refactor -> Pull members Up...
	// select everything except @Test homePageTest() -> Refactor
}
