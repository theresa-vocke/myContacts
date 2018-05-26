package de.hdm.vocke.myContacts.server;

import java.util.ArrayList;
import java.util.Vector;

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
	
	/*
	 * Ende Initialisierung 
	 * Anfang Methoden für Contact-Objekte
	 */
	
	@Override
	public Contact createContact(String first, String last) throws IllegalArgumentException {
		Contact c = new Contact();
		c.setFirstName(first);
		c.setLastName(last);
		
		// setzen der vorläufigen contact-Nummer, insert-Aufruf liefert dann Objekt, dessen 
		// Nummer mit der DB konsistent ist 
		c.setContactId(1);
		
		// Objekt in der DB speichern
		return this.cMapper.insert(c);
	}

	
	/**
	 * Auslesen aller Kontakte, die den übergebenen Nachnamen besitzen 
	 */
	@Override
	public Vector<Contact> getContactByLastname(String lastname) throws IllegalArgumentException {
		return this.cMapper.findByLastname(lastname);
	}

	
	/**
	 * Auslesen aller Kontakte, die den übergebenen Vornamen besitzen 
	 */
	@Override
	public Vector<Contact> getContactByFirstname(String firstname) throws IllegalArgumentException {
		return this.cMapper.findByFirstname(firstname);
	}
	
	/**
	 * Auslesen aller Kontakte in einer Kontaktliste 
	 */
	@Override
	public ArrayList<Contact> getContactsOfContactList(ContactList cl) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}
	
	/** 
	 * löschen eines Kontakt-Objektes
	 */
	@Override
	public void delete(Contact c) throws IllegalArgumentException {
		cMapper.delete(c);
	}
	
	/**
	 * speichern eines Kontakt-Objektes 
	 */
	@Override
	public void save(Contact c) throws IllegalArgumentException {
		cMapper.update(c);
	}
	
	
	
	
	
	
	
	
	/*
	 * Methoden für Kontaktlisten-Objekte
	 */
	
	/**
	 * 	einen Kontakt zu einer Kontaktliste hinzufügen 
	 */
	@Override
	public ContactList addToContactList(Contact c) throws IllegalArgumentException {
				
		// Objekt in der DB speichern
		return this.clMapper.insert(c);
	}

	/**
	 * erstellen einer Kontaktliste
	 */
	@Override
	public ArrayList<ContactList> createContactList(ContactList cl) throws IllegalArgumentException {
		return null;
	}

	/**
	 * ausgeben der Kontaktlsiten nach übergebenem Name
	 */
	@Override
	public ContactList getContactListByName(String name) throws IllegalArgumentException {
		return null;
	}

	/**
	 * auslesen aller Kontaktlisten 
	 */
	@Override
	public ContactList getAllContactLists(ContactList cl) throws IllegalArgumentException {
		return null;
	}

	/**
	 * speichern eines Kontaktlisten-Objektes
	 */
	@Override
	public void save(ContactList cl) throws IllegalArgumentException {
		clMapper.update(cl);
	}

	/**
	 * löschen eines Kontaktlisten-Objektes 
	 */
	@Override
	public void delete(ContactList cl) throws IllegalArgumentException {
		clMapper.delete(cl);
	}

	

}
