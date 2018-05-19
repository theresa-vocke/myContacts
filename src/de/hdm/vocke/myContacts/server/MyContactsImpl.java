package de.hdm.vocke.myContacts.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.vocke.myContacts.server.db.ContactListMapper;
import de.hdm.vocke.myContacts.server.db.ContactMapper;
import de.hdm.vocke.myContacts.shared.MyContacts;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;

public class MyContactsImpl extends RemoteServiceServlet implements MyContacts{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Referenz auf Datenbank-Mapper der Kontakt-Objekte mit der Datenbank abgleicht
	 */
	private ContactMapper cMapper = null;
	
	/**
	 * Referenz auf Datenbank-Mapper der Kontaktlisten-Objekte mit der Datenbank ableicht
	 */
	private ContactListMapper clMapper = null; 
	
	
	/*
	 * Initialisierung 
	 */
	
	/**
	 * no-Argument Konstruktor muss vorhanden sein und keine weitere Funkton haben
	 */
	public MyContactsImpl () throws IllegalArgumentException{
		
	}
	
	/**
	 * Initialisierungsmethode
	 */
	public void init () throws IllegalArgumentException{
		this.cMapper = ContactMapper.contactMapper();
		this.clMapper = ContactListMapper.contactListMapper();
	}
	
	
	
	@Override
	public Contact createContact(String first, String last) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ContactList addToContactList(Contact c) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ContactList createContactList(ContactList cl) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ArrayList<Contact> getContactsOfContactList(ContactList cl) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contact getContactByLastname(String lastname) throws IllegalArgumentException {
		return null;
	}

	@Override
	public Contact getContactByFirstname(String firstname) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ContactList getContactListByName(String name) throws IllegalArgumentException {
		return null;
	}

	@Override
	public ContactList getAllContactLists(ContactList cl) throws IllegalArgumentException {
		return null;
	}

	@Override
	public void save(Contact c) throws IllegalArgumentException {
		
	}

	@Override
	public void save(ContactList cl) throws IllegalArgumentException {
		
	}

	@Override
	public void delete(Contact c) throws IllegalArgumentException {
	
	}

	@Override
	public void delete(ContactList cl) throws IllegalArgumentException {
		
	}

	

}
