package de.hdm.vocke.myContacts;



import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;

import de.hdm.vocke.myContacts.server.MyContactsImpl;

	import de.hdm.vocke.myContacts.shared.bo.Contact;


	public class Methodentest extends GWTTestCase {


		public String getModuleName() {
			return "de.hdm.vocke.myContacts.server.MyContactsImpl";
		}
			

		
		@Test
		public void createContactList() {
			
			Contact c = new Contact();
			c.setId(3);
			
			MyContactsImpl myContact = new MyContactsImpl();
			
			myContact.createContactList("Kollegen");
		}
		
		
		
		
		

		

	}

