package com.telran.contact;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

// HomeWork

public class RemoveContactTest extends TestBase {
    // preconditions:
    @BeforeMethod
    public void ensurePreconditions() {
        // user should be loggedIn - check Sign Out button displayed
        if (!isSignOutTabPresent()) {
            // if we don't see 'Sign Out button' - need to log in with user data, who was already registered
            clickOnLoginTab();
            type(By.cssSelector("[placeholder='Email']"), "krooos@gm.com");
            type(By.cssSelector("[placeholder='Password']"), "Krooos12345~");
            click(By.xpath("//button[contains(., 'Login')]"));
        }
    }

    @Test
    public void addContactPositiveTest() {
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
        // click on the Save button - 3-th way
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
        // Assert - contact created

        // remove contact
        removeContactPositiveTest();
    }

    public void removeContactPositiveTest() {
        // 1. click on the CONTACTS tab
        // 2. click on the first User card
        // 3. Assert User card present
        // 4. click Remove button
        // 5. Assert User detailed card field is empty
        // or Assert User card field is empty

        // 1. click on the CONTACTS tab
//        click(By.xpath("//a[contains(., 'CONTACTS')]"));
        // 2. click on the first User card
        click(By.cssSelector(".contact-page_leftdiv__yhyke div:nth-child(1) > div:nth-child(1)"));
        pause(1000);
        // 3. Assert User card present
        // cssSelector: .contact-item-detailed_card__50dTS
        Assert.assertTrue(isContactItemDetailedCardPresent());
        // 4. click Remove button
        // xPath: //button[contains(., 'Remove')]
        click(By.xpath("//button[contains(., 'Remove')]"));
        // 5. Assert User detailed card field is empty
        // cssSelector: .contact-page_rightdiv__1NgZC
        Assert.assertTrue(isContactItemDetailedCardDoesntPresent());
        // or Assert User card field is empty
        // cssSelector: .contact-item_card__2SOIM  doesn't have tags h2, h3
    }

    public boolean isContactItemDetailedCardDoesntPresent() {
        return isElementPresent(By.cssSelector(".contact-page_rightdiv__1NgZC"));
    }

    public boolean isContactItemDetailedCardPresent() {
        return isElementPresent(By.cssSelector(".contact-item-detailed_card__50dTS"));
    }
}
