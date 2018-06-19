package de.hdm.vocke.myContacts;

import java.util.Vector;

import de.hdm.vocke.myContacts.server.MyContactsImpl;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class TestenKlasse {
	
	public static void main(String[] args) {
	
	//final MyContactsImpl impl = null;
	
//	MyContactsImpl verwaltung = new MyContactsImpl();
//	
//	verwaltung.init();
//	Vector<Contact> v = verwaltung.findAllContacts();
//	
//	for(Contact c : v){
//	System.out.println(c.getLastName());
//	}
		
	MyContactsImpl verwaltung = new MyContactsImpl();
	
	ContactList contactListId = contactListId.getId();
	
	verwaltung.init();
	Vector<ContactList> v = verwaltung.findAllContactsByContactListId(contactListId);
	
	for(Contact c : v){
		System.out.println(c.getLastName());
		}
		
	}
}
