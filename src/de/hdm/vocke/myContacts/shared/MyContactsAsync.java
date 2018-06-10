package de.hdm.vocke.myContacts.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public interface MyContactsAsync {

	void init(AsyncCallback<Void> callback);
	
	/**
	 * ALLES ZU KONTAKT
	 */

	void createContact(String first, String last, int phonenumber, String street, int number, String city, Date birthdate, AsyncCallback<Contact> callback);

	void save(Contact c, AsyncCallback<Void> callback);
	
	void findContactById (int contactId, AsyncCallback<Contact> callback);
	
	void findAllContacts (AsyncCallback<Vector<Contact>> callback);
	
	void getContactByFirstname(String firstname, AsyncCallback<Vector<Contact>> callback);

	void getContactByLastname(String lastname, AsyncCallback<Vector<Contact>> callback);
		
	void findAllContactsByContactListId(int contactListId, AsyncCallback<Vector<Contact>> callback);
	
	void delete(Contact c, AsyncCallback<Void> callback);
	
	
	/**
	 * ALLES ZU KONTAKTLISTE
	 */
	
	void createContactList(String name, AsyncCallback<ContactList> callback);
	
	void getContactListByName(String name, AsyncCallback<Vector<ContactList>> callback);
	
	void findContactListById(int contactListId, AsyncCallback<ContactList> callback);

	void getAllContactLists(AsyncCallback<Vector<ContactList>> callback);
	
	void delete(ContactList cl, AsyncCallback<Void> callback);
	
	void save(ContactList cl, AsyncCallback<Void> callback);
	
	
	
	



	
	
	/**
	 * ALLES ZU KONTAKTLISTEKONTAKT
	 */
	

	void getContactsOfContactList(int contactListId, AsyncCallback<Vector<Contact>> asyncCallback);


	void addContact(ContactList cl, Contact c, AsyncCallback<ContactList> callback);
	
	
	
	
}
