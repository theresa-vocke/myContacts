package de.hdm.vocke.myContacts.server;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.vocke.myContacts.server.db.ContactListMapper;
import de.hdm.vocke.myContacts.server.db.ContactMapper;
import de.hdm.vocke.myContacts.shared.MyContacts;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

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
	
	/**
	 * Liste mit allen darin enthaltenen Kontakten auslesen
	 */
	public Vector<Contact> getContactsFor (ContactList cl) throws IllegalArgumentException{
		return this.cMapper.getContactsOf(cl);
		// da muss irgendwie die array liste wieder gegeben werden 
	}
	
	
	
	
	/*
	 * Methoden für Kontaktlisten-Objekte
	 */
	
	/**
	 * 	einen Kontakt zu einer Kontaktliste hinzufügen 
	 */
	@Override
	public ContactList addContact (ContactList cl, Contact c) throws IllegalArgumentException {
				
		// Objekt in der DB speichern
		// eigentlich dann hier die Methode mit contactlistcontactId die Kontakt und Kontaktliste zugeordnet wird 
		return this.clMapper.insertContact(c);
	}

	/**
	 * erstellen einer Kontaktliste
	 */
	@Override
	public ArrayList<ContactList> createContactList (String name) throws IllegalArgumentException {
		ContactList cl = new ContactList();
		cl.setName(name);
	 
		cl.setContactListId(1);
		
		return this.clMapper.insert(cl);
	}

	
	/**
	 * ausgeben der Kontaktlsiten nach übergebenem Name
	 */
	@Override
	public Vector<ContactList> getContactListByName(String name) throws IllegalArgumentException {
		return this.clMapper.findByName(name);
	}

	/**
	 * auslesen aller Kontaktlisten 
	 */
	@Override
	public Vector<ContactList> getAllContactLists() throws IllegalArgumentException {
		return this.clMapper.findAll();
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


	@Override
	public Vector<Contact> getContactsOfContactList(int contactListId) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ContactList findContactListById(int contactListId) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
