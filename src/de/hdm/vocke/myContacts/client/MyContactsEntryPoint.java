package de.hdm.vocke.myContacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.vocke.myContacts.client.gui.ContactListTreeViewModel;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyContactsEntryPoint implements EntryPoint {
	
	final MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	ClientsideSettings clientsideSettings = new ClientsideSettings();
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		ContactListTreeViewModel ctvm = new ContactListTreeViewModel();
		RootPanel.get("Navigator").clear();
		RootPanel.get("Navigator").add(ctvm);
	    
	    
		
	}
}
