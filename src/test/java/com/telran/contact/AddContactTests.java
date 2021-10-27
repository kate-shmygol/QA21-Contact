package com.telran.contact;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {
    // preconditions:
    @BeforeMethod
    public void ensurePreconditions() {
        if (!isSignOutTabPresent()) {
            click(By.xpath("//a[contains(., 'LOGIN')]"));
            type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");
            type(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
            click(By.xpath("//button[contains(., 'Login')]"));
        }
    }

    @Test
    public void addContactPositiveTest () {
        int i = (int) ((System.currentTimeMillis()) / 1000) % 3600; // return current time, divide 3600
        // click on the tab Add
        click(By.cssSelector("a:nth-child(5)"));
        pause(1000);
        // fill all fields
        type(By.cssSelector("[placeholder='Name']"), "Karl"); // name - [placeholder='Name'] = input:nth-child(1)
        type(By.cssSelector("input:nth-child(2)"), "Adam"); // lastname
        type(By.cssSelector("input:nth-child(3)"), "12345" + i); // phone
        type(By.cssSelector("input:nth-child(4)"), "adam" + i + "@gm.com"); // email
        type(By.cssSelector("input:nth-child(5"), "Koblenz"); // address
        type(By.cssSelector("input:nth-child(6)"), "torwart"); // description
        // click on the Save button - 1-st way
//        click(By.cssSelector(".add_form__2rsm2 button"));
        // click on the Save button - 2-nd way
//        jump();
        // click on the Save button - 3-th way
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
        // Assert - contact created
    }
}
