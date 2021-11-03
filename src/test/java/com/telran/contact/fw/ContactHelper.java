package com.telran.contact.fw;

import com.telran.contact.models.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase {

	public ContactHelper(WebDriver driver) {
		super(driver);
	}

	public void addNewContact(Contact contact) {

		click(By.cssSelector("a:nth-child(5)"));
		type(By.cssSelector("[placeholder='Name']"), contact.getName()); // name - [placeholder='Name'] = input:nth-child(1)
		type(By.cssSelector("input:nth-child(2)"), contact.getSurName()); // lastname
		type(By.cssSelector("input:nth-child(3)"), contact.getPhone()); // phone
		type(By.cssSelector("input:nth-child(4)"), contact.getEmail()); // email
		type(By.cssSelector("input:nth-child(5"), contact.getAddress()); // address
		type(By.cssSelector("input:nth-child(6)"), contact.getDescription()); // description
		clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
	}

	public boolean isContactCreated(String text) {
		// check list of contacts
		List<WebElement> contacts = driver.findElements(By.xpath("//h2"));
//        List<WebElement> contacts = driver.findElements(By.xpath("//h3"));
		// going over the contacts (перебираем)
		for (WebElement el : contacts) {
			if (el.getText().contains(text)) {
				return true;
			}
		}
		return false;
	}

	public void removeContact() {
		if (!isContactListEmpty()) {
			driver.findElement(By.cssSelector(".contact-item_card__2SOIM")).click();
			driver.findElement(By.xpath("//button[text()='Remove']")).click();
//            click(By.cssSelector(".contact-item_card__2SOIM"));
//            click(By.xpath("//button[text()='Remove']"));
		}
	}

	public boolean isContactListEmpty() {
		return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
	}

	public int sizeOfContacts() {
		// if amount more than 0, return list
		if (driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0) {
			return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
		} else {
//            return sizeOfContacts();
			return 0;
		}
	}
}
