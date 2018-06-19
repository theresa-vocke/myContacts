package de.hdm.vocke.myContacts.server;

import java.util.List;
import java.util.Vector;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.hdm.vocke.myContacts.server.db.ContactListContactMapper;
import de.hdm.vocke.myContacts.server.db.ContactListMapper;
import de.hdm.vocke.myContacts.server.db.ContactMapper;
import de.hdm.vocke.myContacts.shared.MyContacts;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;
import de.hdm.vocke.myContacts.shared.bo.ContactListContact;

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
	
	private ContactListContactMapper clcMapper = null;
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
	@Override
	public void init () throws IllegalArgumentException{
		this.cMapper = ContactMapper.contactMapper();
		this.clMapper = ContactListMapper.contactListMapper();
		this.clcMapper = ContactListContactMapper.contactListContactmapper();
	}
	
	/*
	 * Ende Initialisierung 
	 * Anfang Methoden für Contact-Objekte
	 */
	@Override
	public Contact createContact(String firstname, String lastname) 
			throws IllegalArgumentException {
		Contact c = new Contact();
		c.setFirstName(firstname);
		c.setLastName(lastname);
		
		// setzen der vorläufigen contact-Nummer, insert-Aufruf liefert dann Objekt, dessen 
		// Nummer mit der DB konsistent ist 
		c.setId(1);
		
		// Objekt in der DB speichern
		return this.cMapper.insert(c);
	}

	
	/**
	 * speichern eines Kontaktlisten-Objektes
	 */

	@Override
	public void save(Contact c) throws IllegalArgumentException {
		cMapper.update(c);
	}
	
	@Override
	public Contact findContactById(int contactId) throws IllegalArgumentException {
		return this.cMapper.findById(contactId);
	}
	
	@Override
	public Vector<Contact> findAllContacts() throws IllegalArgumentException {
		return this.cMapper.findAllContacts();
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
	
	
	@Override
	public Vector<Contact> findAllContactsByContactListId(int contactListId) throws IllegalArgumentException {
		return this.cMapper.findAllContactsByContactListId(contactListId);
	}
	
	/** 
	 * löschen eines Kontakt-Objektes
	 */
	@Override
	public void delete(Contact c) throws IllegalArgumentException {
		
		List<ContactListContact> contactListContact = findContactListContactByContactId(c.getId());
//				ContactListContactMapper.findContactListContactByContactId(c.getId());
		
		if (contactListContact != null){ 
			for (ContactListContact clcListe : contactListContact){
				deleteContactListContactByContactId(clcListe.getContactId());
				//ContactListContactMapper.deleteContactListContactByContactId(clcListe.getContactId());
			}
		}
		
		this.cMapper.delete(c);
	}
	
	
	
	
	
		
	
	

	
	
	/*
	 * Methoden für Kontaktlisten-Objekte
	 */

	/**
	 * erstellen einer Kontaktliste
	 */
	@Override
	public ContactList createContactList (String name) throws IllegalArgumentException {
		ContactList cl = new ContactList();
		cl.setName(name);
	 
		cl.setId(1);
		
		return this.clMapper.insert(cl);
	}

	@Override
	public void save(ContactList cl) throws IllegalArgumentException {
		clMapper.update(cl);
	}
	
	/**
	 * ausgeben der Kontaktlsiten nach übergebenem Name
	 */
	@Override
	public Vector<ContactList> getContactListByName(String name) throws IllegalArgumentException {
		return this.clMapper.findByName(name);
	}

	
	@Override
	public ContactList findContactListById(int contactListId) throws IllegalArgumentException {
		return this.clMapper.findContactListById(contactListId);
	}
	
	
	/**
	 * auslesen aller Kontaktlisten 
	 */
	@Override
	public Vector<ContactList> getAllContactLists() throws IllegalArgumentException {
		return this.clMapper.findAll();
	}
	
	/**
	 * löschen eines Kontaktlisten-Objektes 
	 */
	@Override
	public void delete(ContactList cl) throws IllegalArgumentException {
		clMapper.delete(cl);
	}

	
	
	
	
	
	
	
	/*
	 * Methoden für ContactListContact-Objekte
	 */
	@Override
	public void deleteContactListContact(ContactListContact clc) throws IllegalArgumentException {
		this.clcMapper.deleteContactListContact(clc);
	}
	
	@Override
	public ContactListContact createContactToContactList(Contact contact, ContactList contactList) throws IllegalArgumentException {
		ContactListContact clc = new ContactListContact();

		clc.setContactId(contact.getId());
		clc.setContactListId(contactList.getId());
		
		return this.clcMapper.addContactToContactList(clc);
	}
	
	
	@Override
	public void deleteContactListContactByContactId(int contactId) throws IllegalArgumentException {
		this.clcMapper.deleteContactListContactByContactId(contactId);
	}
	
	@Override
	public void deleteContactListContactByContactListId(int contactListId) throws IllegalArgumentException {
		this.clcMapper.deleteContactListContactByContactListId(contactListId);
	}
	
	@Override
	public Vector<ContactListContact> findContactListContactByContactListId (int contactListId) throws IllegalArgumentException {
		return this.clcMapper.findContactListContactByContactListId(contactListId);
	}
	
	@Override
	public Vector<ContactListContact> findContactListContactByContactId (int contactId) throws IllegalArgumentException {
		return this.clcMapper.findContactListContactByContactId(contactId);
	}

	
	
	

	

}
