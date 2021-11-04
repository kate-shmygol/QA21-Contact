package com.telran.contact.tests;

import com.telran.contact.fw.DataProviders;
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

	@Test(dataProvider = "newContact", dataProviderClass = DataProviders.class)
	public void addContactPositiveFromDataProviderTest(String name, String sName, String ph, String email, String addr, String descr) throws InterruptedException {

		app.getContact().addNewContact(new Contact().setName(name).setSurName(sName).setPhone(ph)
				.setEmail(email).setAddress(addr).setDescription(descr));
		app.getContact().removeContact();
		Thread.sleep(2000);
	}

	@Test(dataProvider = "newContactFromCSV", dataProviderClass = DataProviders.class)
	public void addContactPositiveFromCSVTest(Contact contact) throws InterruptedException {

		app.getContact().addNewContact(contact);
		app.getContact().removeContact();
		Thread.sleep(2000);
	}
}

