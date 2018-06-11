package de.hdm.vocke.myContacts;

	import java.util.Date;
	import java.util.List;
	import java.util.Vector;

import org.junit.Test;

import com.google.gwt.junit.client.GWTTestCase;


	import de.hdm.vocke.myContacts.server.db.ContactListContactMapper;
	import de.hdm.vocke.myContacts.server.db.ContactListMapper;
	import de.hdm.vocke.myContacts.server.db.ContactMapper;
	import de.hdm.vocke.myContacts.shared.bo.Contact;
	import de.hdm.vocke.myContacts.shared.bo.ContactList;
	import de.hdm.vocke.myContacts.shared.bo.ContactListContact;

	public class Methodentest extends GWTTestCase {


		public String getModuleName() {
			return "de.hdm.vocke.myContacts.server.MyContactsImpl";
		}
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
		 * @return 
		 */
		public void MyContactsImpl () throws IllegalArgumentException{
			//keine ahnung
		}
		
		/**
		 * Initialisierungsmethode
		 */
		public void init () throws IllegalArgumentException{
			this.cMapper = ContactMapper.contactMapper();
			this.clMapper = ContactListMapper.contactListMapper();
			this.clcMapper = ContactListContactMapper.contactListContactmapper();
		}
		
		/*
		 * Ende Initialisierung 
		 * Anfang Methoden für Contact-Objekte
		 */
		
		@Test
		public Contact createContact(String firstname, String lastname, int phonenumber, String street, int number, String city, Date birthdate) 
				throws IllegalArgumentException {
			Contact c = new Contact();
			c.setFirstName(firstname);
			c.setLastName(lastname);
			c.setPhonenumber(phonenumber);
			c.setStreet(street);
			c.setNumber(number);
			c.setCity(city);
			c.setBirthdate(birthdate);
			
			// setzen der vorläufigen contact-Nummer, insert-Aufruf liefert dann Objekt, dessen 
			// Nummer mit der DB konsistent ist 
			c.setId(1);
			
			 init();
			// Objekt in der DB speichern
			return this.cMapper.insert(c);
		}

		
		/**
		 * speichern eines Kontaktlisten-Objektes
		 */


		public void save(Contact c) throws IllegalArgumentException {
			cMapper.update(c);
		}
		
		public Contact findContactById(int contactId) throws IllegalArgumentException {
			return this.cMapper.findById(contactId);
		}
		
		public Vector<Contact> findAllContacts() throws IllegalArgumentException {
			return this.cMapper.findAllContacts();
		}
		
		/**
		 * Auslesen aller Kontakte, die den übergebenen Nachnamen besitzen 
		 */

		public Vector<Contact> getContactByLastname(String lastname) throws IllegalArgumentException {
			return this.cMapper.findByLastname(lastname);
		}

		
		/**
		 * Auslesen aller Kontakte, die den übergebenen Vornamen besitzen 
		 */

		public Vector<Contact> getContactByFirstname(String firstname) throws IllegalArgumentException {
			return this.cMapper.findByFirstname(firstname);
		}
		
		
		
		public Vector<Contact> findAllContactsByContactListId(int contactListId) throws IllegalArgumentException {
			return this.cMapper.findAllContactsByContactListId(contactListId);
		}
		
		/** 
		 * löschen eines Kontakt-Objektes
		 */
		public void delete(Contact c) throws IllegalArgumentException {
			
			List<ContactListContact> contactListContact = findContactListContactByContactId(c.getId());
//					ContactListContactMapper.findContactListContactByContactId(c.getId());
			
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

		public ContactList createContactList (String name) throws IllegalArgumentException {
			ContactList cl = new ContactList();
			cl.setName(name);
		 
			cl.setId(1);
			
			return this.clMapper.insert(cl);
		}

		public void save(ContactList cl) throws IllegalArgumentException {
			clMapper.update(cl);
		}
		
		/**
		 * ausgeben der Kontaktlsiten nach übergebenem Name
		 */
		public Vector<ContactList> getContactListByName(String name) throws IllegalArgumentException {
			return this.clMapper.findByName(name);
		}

		
		
		public ContactList findContactListById(int contactListId) throws IllegalArgumentException {
			return this.clMapper.findContactListById(contactListId);
		}
		
		
		/**
		 * auslesen aller Kontaktlisten 
		 */

		public Vector<ContactList> getAllContactLists() throws IllegalArgumentException {
			return this.clMapper.findAll();
		}
		
		/**
		 * löschen eines Kontaktlisten-Objektes 
		 */

		public void delete(ContactList cl) throws IllegalArgumentException {
			clMapper.delete(cl);
		}

		
		
		
		
		
		
		
		/*
		 * Methoden für ContactListContact-Objekte
		 */
		
		public void deleteContactListContact(ContactListContact clc) throws IllegalArgumentException {
			this.clcMapper.deleteContactListContact(clc);
		}
		
		
		public ContactListContact createContactToContactList(int contactId, int contactListId) throws IllegalArgumentException {
			ContactListContact clc = new ContactListContact();

			clc.setContactId(contactId);
			clc.setContactListId(contactListId);
			
			return this.clcMapper.addContactToContactList(clc);
		}
		
		
		
		public void deleteContactListContactByContactId(int contactId) throws IllegalArgumentException {
			this.clcMapper.deleteContactListContactByContactId(contactId);
		}
		
		
		public void deleteContactListContactByContactListId(int contactListId) throws IllegalArgumentException {
			this.clcMapper.deleteContactListContactByContactListId(contactListId);
		}
		
		
		public Vector<ContactListContact> findContactListContactByContactListId (int contactListId) throws IllegalArgumentException {
			return this.clcMapper.findContactListContactByContactListId(contactListId);
		}
		
		
		public Vector<ContactListContact> findContactListContactByContactId (int contactId) throws IllegalArgumentException {
			return this.clcMapper.findContactListContactByContactId(contactId);
		}

		

		
		
		

		

	}

