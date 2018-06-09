package de.hdm.vocke.myContacts.shared;

import java.util.ArrayList;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public interface MyContactsAsync {

	void init(AsyncCallback<Void> callback);

	void createContact(String first, String last, AsyncCallback<Contact> callback);

	void createContactList(String name, AsyncCallback<ArrayList<ContactList>> callback);

	void getContactsOfContactList(int contactListId, AsyncCallback<ArrayList<Contact>> asyncCallback);

	void getContactByLastname(String lastname, AsyncCallback<Vector<Contact>> callback);

	void getContactByFirstname(String firstname, AsyncCallback<Vector<Contact>> callback);

	void getContactListByName(String name, AsyncCallback<Vector<ContactList>> callback);

	void getAllContactLists(AsyncCallback<Vector<ContactList>> callback);

	void save(Contact c, AsyncCallback<Void> callback);

	void save(ContactList cl, AsyncCallback<Void> callback);

	void delete(Contact c, AsyncCallback<Void> callback);

	void delete(ContactList cl, AsyncCallback<Void> callback);

	void addContact(ContactList cl, Contact c, AsyncCallback<ContactList> callback);

	void findContactListById(Object getContactListId, AsyncCallback<ContactList> asyncCallback);



}
