package de.hdm.vocke.myContacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.vocke.myContacts.shared.MyContactsAsync;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyContactsEntryPoint implements EntryPoint {
	
	private static MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	ClientsideSettings clientsideSettings = new ClientsideSettings();
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		VerticalPanel mainPanel = new VerticalPanel();
		Button addContactButton = new Button("Add");
		
	    mainPanel.add(addContactButton);
	    
	    
		
	}
}
