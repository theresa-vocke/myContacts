package de.hdm.vocke.myContacts.shared;

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
	public ContactList createContactList (ContactList cl) throws IllegalArgumentException;
	
	
	
	
}
