package de.hdm.vocke.myContacts.client.gui;

import java.util.Map;

import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.TreeViewModel;

import de.hdm.thies.bankProjekt.shared.bo.Account;
import de.hdm.thies.bankProjekt.shared.bo.Customer;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class ContactListTreeViewModel implements TreeViewModel {

	
	// ListDataProvider gefüllt mit Kontaktlisten-Objekten
	private ListDataProvider<ContactList> contactListDataProvider = null;

	/*
	 * In dieser Map merken wir uns die ListDataProviders für die Kontaktlisten
	 * der im Kontakt- und Kontaktlistenbaum expandierten Kontaktknoten.
	 */
	private Map<ContactList, ListDataProvider<Contact>> ContactDataProviders = null;
	
	
	
	
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isLeaf(Object value) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
