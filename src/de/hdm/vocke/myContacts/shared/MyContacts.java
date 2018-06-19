package de.hdm.vocke.myContacts.shared;

import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;
import de.hdm.vocke.myContacts.shared.bo.ContactListContact;
/**
 * synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwatlung von Kontakten und Kontaktlisten
 * 
 * die Methoden werden hier umgesetzt, damit die Klassen nicht zu sehr aneinander gekoppelt werden
 * Das Wissen, wie die einzelnen Daten koexistieren wird also in der vorliegenden Klasse gekapselt 
 */

//----Wichtig----
@RemoteServiceRelativePath("kontaktverwaltung")


public interface MyContacts extends RemoteService {
	
	/** 
	 * initialisierung des Objektes 
	 * Diese Methode ist vor dem Hintergrund von GWT RPC zusätzlich zum No Argument Constructor der 
	 * implementierenden Klacrsse myContactsImpl notwendig. Diese Methode direkt nach der Instantiierung aufrufen
	 */
	public void init () throws IllegalArgumentException;
	
	/**
	 * ALLES ZU KONTAKT
	 */
	
	public Contact createContact(String firstname, String lastname);
		
	/**
	 * speichern eines Kontakt-Objektes in der Datenbank
	 */
	public void save (Contact c) throws IllegalArgumentException;
	
	public Contact findContactById(int contactId);
	
	public Vector<Contact> findAllContacts() throws IllegalArgumentException;
	
	/**
	 * Auslesen aller Kontakt-Objekte nach Nachname 
	 */
	public Vector<Contact> getContactByLastname (String lastname) throws IllegalArgumentException;
	
	/**
	 * auslesen aller Kontakt-Objekte nach Vorname
	 */
	public Vector<Contact> getContactByFirstname (String firstname) throws IllegalArgumentException;
	
	public Vector<Contact> findAllContactsByContactListId(int contactListId) throws IllegalArgumentException;
	
	/**
	 * löschen des übergebenen Kontakt-Objektes
	 */
	public void delete (Contact c) throws IllegalArgumentException;
	
	
	
	/**
	 * ALLES ZU KONTAKTLISTE 
	 */
	
	/**
	 * eine Kontaktliste erstellen
	 */
	public ContactList createContactList(String name) throws IllegalArgumentException;
	
	/**
	 * auslesen aller Kontaktlisten nach Name  einer Kontaktliste nach Name
	 */
	public Vector<ContactList> getContactListByName (String name) throws IllegalArgumentException;
	
	public ContactList findContactListById(int contactListId);
	
	/**
	 * sämtliche Kontaktlisten ausgeben
	 */
	public Vector<ContactList> getAllContactLists () throws IllegalArgumentException;
	
	/**
	 * löschen des übergebenen Kontaktlisten-Objektes
	 */
	public void delete (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * speichern eines Kontaktlisten-Objektes in der Datenbank 
	 */
	public void save (ContactList cl) throws IllegalArgumentException;


	

	




	/**
	 * ALLES ZU KONTAKTLISTEKONTAKT
	 */
	
	public void deleteContactListContact(ContactListContact clc) throws IllegalArgumentException;

	ContactListContact createContactToContactList(Contact contact, ContactList contactList);

	public void deleteContactListContactByContactId(int contactId) throws IllegalArgumentException;

	public void deleteContactListContactByContactListId(int contactListId) throws IllegalArgumentException;

	public Vector<ContactListContact> findContactListContactByContactListId(int contactListId) throws IllegalArgumentException;

	Vector<ContactListContact> findContactListContactByContactId(int contactId) throws IllegalArgumentException;



	
	
}
