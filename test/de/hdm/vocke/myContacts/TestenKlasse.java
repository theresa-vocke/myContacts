package de.hdm.vocke.myContacts;

import java.util.Vector;

import de.hdm.vocke.myContacts.server.MyContactsImpl;
import de.hdm.vocke.myContacts.shared.bo.Contact;

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
	
	verwaltung.init();
	Vector<ContactListContact> v = verwaltung.createContactToContactList(contact, contactList);
	
	for (ContactListContact clc : v){
		
	}
		
		
	}
}
