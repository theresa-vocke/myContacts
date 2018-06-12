package de.hdm.vocke.myContacts;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import de.hdm.vocke.myContacts.server.MyContactsImpl;
import de.hdm.vocke.myContacts.shared.bo.Contact;

public class MapperTest extends GWTTestCase {

	public String getModuleName() {
		return null;
	}
	
	
	@Test
	public void insert() {
		MyContactsImpl myContacts = new MyContactsImpl();
	Contact c = new Contact();
	c.setId(1);
	c.setFirstName("Theresa");
	c.setLastName("Vocke");
	c.setStreet("StuttgarterStraﬂe");
	c.setCity("Stuttgart");

	}
	
}
