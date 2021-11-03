package com.telran.contact.tests;

import com.telran.contact.models.Contact;
import com.telran.contact.models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {
	// preconditions:
	@BeforeMethod
	public void ensurePreconditions() {
		if (!app.getUser().isSignOutTabPresent()) { // user should be loggedIn - check Sign Out button displayed
			// if we don't see 'Sign Out button' - need to log in with user data, who was already registered
			app.getUser().clickOnLoginTab();
			app.getUser().login(new User()
					.setEmail("krooos@gm.com")
					.setPassword("Krooos12345~"));
		}
	}

	@Test
	public void addContactPositiveTest() {
		// create variable 'i' - different values for numbers
		// currentTimeMillis() - return current time
		int i = (int) ((System.currentTimeMillis()) / 1000) % 3600; // return current time, divide 3600
		app.getContact().addNewContact(new Contact().setName("Karl").setSurName("Adam")
				.setPhone("12345" + i).setEmail("adam" + i + "@gm.com")
				.setAddress("Koblenz").setDescription("torwart"));
		// Assert - contact created
		Assert.assertTrue(app.getContact().isContactCreated("Karl"));
//        Assert.assertTrue(isContactCreated("12345678"));
	}
}

