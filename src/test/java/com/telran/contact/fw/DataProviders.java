package com.telran.contact.fw;

import com.telran.contact.models.Contact;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

	@DataProvider
	public Iterator<Object[]> newContact() {
		List<Object[]> list = new ArrayList<>();
		list.add(new Object[]{"Oliver", "Kan", "918273641", "kan1@gm.co", "Dresden", "goalkeeper"});
		list.add(new Object[]{"Oliver", "Kan", "918273642", "kan2@gm.co", "Dresden", "goalkeeper"});
		list.add(new Object[]{"Oliver", "Kan", "918273643", "kan3@gm.co", "Dresden", "goalkeeper"});

		return list.iterator();
	}

	@DataProvider
	public Iterator<Object[]> newContactFromCSV() throws IOException {
		List<Object[]> list = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/Contacts.csv")));

		String line = reader.readLine();

		while (line != null) {
			String[] split = line.split(",");

			list.add(new Object[]{new Contact().setName(split[0]).setSurName(split[1]).setPhone(split[2])
					.setEmail(split[3]).setAddress(split[4]).setDescription(split[5])});
			line = reader.readLine();
		}
		return list.iterator();
	}

	// homework
	// @DataProvider
	// registrationNegativeTest()
	// newUserFromCSV
}
