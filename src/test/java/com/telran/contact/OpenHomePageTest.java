package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class OpenHomePageTest extends TestBase {

    @Test
    public void homePageTest() {
        System.out.println("Site opened!");
//        System.out.println("HomeComponent: " + isHomeComponentPresent());
        System.out.println("HomeComponent: " + isElementPresent(By.cssSelector("div:nth-child(2) > div > div")));
    }
    // transferring methods to the parent class TestBase:
    // anywhere in the empty editor field
    // click RMB (right mouse button) -> Refactor -> Pull members Up...
    // select everything except @Test homePageTest() -> Refactor
}
