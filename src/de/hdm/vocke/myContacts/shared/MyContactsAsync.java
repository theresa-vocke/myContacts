package de.hdm.vocke.myContacts.shared;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public interface MyContactsAsync {

	void init(AsyncCallback<Void> callback);

	void createContact(String first, String last, AsyncCallback<Contact> callback);

	void addToContactList(Contact c, AsyncCallback<ContactList> callback);

	void createContactList(ContactList cl, AsyncCallback<ContactList> callback);

	void getContactsOfContactList(ContactList cl, AsyncCallback<ArrayList<Contact>> callback);

	void getContactByLastname(String lastname, AsyncCallback<Contact> callback);

	void getContactByFirstname(String firstname, AsyncCallback<Contact> callback);

	void getContactListByName(String name, AsyncCallback<ContactList> callback);

	void getAllContactLists(ContactList cl, AsyncCallback<ContactList> callback);

	void save(Contact c, AsyncCallback<Void> callback);

	void save(ContactList cl, AsyncCallback<Void> callback);

	void delete(Contact c, AsyncCallback<Void> callback);

	void delete(ContactList cl, AsyncCallback<Void> callback);


}
