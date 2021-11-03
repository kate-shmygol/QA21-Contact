package com.telran.contact.tests;

import com.telran.contact.models.Contact;
import com.telran.contact.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTest2 extends TestBase {
	// preconditions:
	@BeforeMethod
	public void ensurePreconditions() throws InterruptedException {
		// user should be loggedIn - check Sign Out button displayed
		if (!app.getUser().isSignOutTabPresent()) {
			// if we don't see 'Sign Out button' - need to log in with user data, who was already registered
			app.getUser().clickOnLoginTab();
			app.getUser().login(new User()
					.setEmail("krooos@gm.com")
					.setPassword("Krooos12345~"));
		}
		// adding a contact
		int i = (int) ((System.currentTimeMillis()) / 1000) % 3600; // return current time, divide 3600
		Thread.sleep(1000);
		app.getContact().addNewContact(new Contact().setName("Karl").setSurName("Adam")
				.setPhone("12345" + i).setEmail("adam" + i + "@gm.com")
				.setAddress("Koblenz").setDescription("torwart"));
		// Assert - contact created
	}

	@Test
	public void removeContactPositiveTest() throws InterruptedException {
		// 1. create 2 variables - before removing and after removing to compare lists
		// compare of lists before and after
		int sizeBefore = app.getContact().sizeOfContacts();
		System.out.println(sizeBefore);
//        pause(2000);
		app.getContact().removeContact();
		Thread.sleep(2000);
		int sizeAfter = app.getContact().sizeOfContacts();
		System.out.println(sizeAfter);
		Assert.assertEquals(sizeBefore - 1, sizeAfter);
	}
}
