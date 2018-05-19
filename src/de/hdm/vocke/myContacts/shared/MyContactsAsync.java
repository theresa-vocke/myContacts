package de.hdm.vocke.myContacts.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public interface MyContactsAsync {

	void init(AsyncCallback<Void> callback);

	void createContact(String first, String last, AsyncCallback<Contact> callback);

	void addToContactList(Contact c, AsyncCallback<ContactList> callback);

}
