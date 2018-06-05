package de.hdm.vocke.myContacts.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;
/**
 * synchrone Schnittstelle für eine RPC-fähige Klasse zur Verwatlung von Kontakten und Kontaktlisten
 * 
 * die Methoden werden hier umgesetzt, damit die Klassen nicht zu sehr aneinander gekoppelt werden
 * Das Wissen, wie die einzelnen Daten koexistieren wird also in der vorliegenden Klasse gekapselt 
 */
public interface MyContacts extends RemoteService {
	
	/** 
	 * initialisierung des Objektes 
	 * Diese Methode ist vor dem Hintergrund von GWT RPC zusätzlich zum No Argument Constructor der 
	 * implementierenden Klasse myContactsImpl notwendig. Diese Methode direkt nach der Instantiierung aufrufen
	 */
	public void init () throws IllegalArgumentException;
	
	/**
	 * einen Kontakt anlegen 
	 */
	public Contact createContact (String first, String last) throws IllegalArgumentException;
	
	/**
	 * einen Kontakt zu einer Kontaktliste hinzufügen 
	 */
	public ContactList addToContactList (Contact c) throws IllegalArgumentException;
	
	/**
	 * eine Kontaktliste erstellen
	 */
	public ArrayList<ContactList> createContactList (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * auslesen aller Kontakte in einer speziellen Kontaktliste
	 */
	public ArrayList<Contact> getContactsOfContactList (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * Auslesen aller Kontakt-Objekte nach Nachname 
	 */
	public Vector<Contact> getContactByLastname (String lastname) throws IllegalArgumentException;
	
	/**
	 * auslesen aller Kontakt-Objekte nach Vorname
	 */
	public Vector<Contact> getContactByFirstname (String firstname) throws IllegalArgumentException;
	
	/**
	 * auslesen aller Kontaktlisten nach Name  einer Kontaktliste nach Name
	 */
	public Vector<ContactList> getContactListByName (String name) throws IllegalArgumentException;
	
	/**
	 * sämtliche Kontaktlisten ausgeben
	 */
	public Vector<ContactList> getAllContactLists (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * speichern eines Kontakt-Objektes in der Datenbank
	 */
	public void save (Contact c) throws IllegalArgumentException;
	
	/**
	 * speichern eines Kontaktlisten-Objektes in der Datenbank 
	 */
	public void save (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * löschen des übergebenen Kontakt-Objektes
	 */
	public void delete (Contact c) throws IllegalArgumentException;
	
	/**
	 * löschen des übergebenen Kontaktlisten-Objektes
	 */
	public void delete (ContactList cl) throws IllegalArgumentException;

	public ArrayList<ContactList> createContactList (String name) throws IllegalArgumentException;

	public Contact addToContactList(ContactList contactlist, int contactId) throws IllegalArgumentException;


	
	
}
