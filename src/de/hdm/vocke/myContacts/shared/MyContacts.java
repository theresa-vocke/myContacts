package de.hdm.vocke.myContacts.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;

import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;
/**
 * synchrone Schnittstelle f�r eine RPC-f�hige Klasse zur Verwatlung von Kontakten und Kontaktlisten
 * 
 * die Methoden werden hier umgesetzt, damit die Klassen nicht zu sehr aneinander gekoppelt werden
 * Das Wissen, wie die einzelnen Daten koexistieren wird also in der vorliegenden Klasse gekapselt 
 */
public interface MyContacts extends RemoteService {
	
	/** 
	 * initialisierung des Objektes 
	 * Diese Methode ist vor dem Hintergrund von GWT RPC zus�tzlich zum No Argument Constructor der 
	 * implementierenden Klasse myContactsImpl notwendig. Diese Methode direkt nach der Instantiierung aufrufen
	 */
	public void init () throws IllegalArgumentException;
	
	/**
	 * einen Kontakt anlegen 
	 */
	public Contact createContact (String first, String last) throws IllegalArgumentException;
	
	/**
	 * einen Kontakt zu einer Kontaktliste hinzuf�gen 
	 */
	public ContactList addToContactList (Contact c) throws IllegalArgumentException;
	
	/**
	 * eine Kontaktliste erstellen
	 */
	public ContactList createContactList (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * auslesen aller Kontakte in einer speziellen Kontaktliste
	 */
	public ArrayList<Contact> getContactsOfContactList (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * suchen eines Kontakt-Objektes nach Nachname 
	 */
	public Contact getContactByLastname (String lastname) throws IllegalArgumentException;
	
	/**
	 * suchen eines Kontakt-Objektes nach Vorname
	 */
	public Contact getContactByFirstname (String firstname) throws IllegalArgumentException;
	
	/**
	 * suchen einer Kontaktliste nach Name
	 */
	public ContactList getContactListByName (String name) throws IllegalArgumentException;
	
	/**
	 * s�mtliche Kontaktlisten ausgeben
	 */
	public ContactList getAllContactLists (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * speichern eines Kontakt-Objektes in der Datenbank
	 */
	public void save (Contact c) throws IllegalArgumentException;
	
	/**
	 * speichern eines Kontaktlisten-Objektes in der Datenbank 
	 */
	public void save (ContactList cl) throws IllegalArgumentException;
	
	/**
	 * l�schen des �bergebenen Kontakt-Objektes
	 */
	public void delete (Contact c) throws IllegalArgumentException;
	
	/**
	 * l�schen des �bergebenen Kontaktlisten-Objektes
	 */
	public void delete (ContactList cl) throws IllegalArgumentException;
	
	
}