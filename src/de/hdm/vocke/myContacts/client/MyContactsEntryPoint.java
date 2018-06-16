package de.hdm.vocke.myContacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.RootPanel;

import de.hdm.vocke.myContacts.client.gui.ContactForm;
import de.hdm.vocke.myContacts.client.gui.ContactListForm;
import de.hdm.vocke.myContacts.client.gui.ContactListTreeViewModel;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyContactsEntryPoint implements EntryPoint {
	
	static interface ContactTreeRecources extends CellTree.Resources {
	    @Override
		@Source("ContactCellTree.css")
	    CellTree.Style cellTreeStyle(); 
	}
		
	final MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	ClientsideSettings clientsideSettings = new ClientsideSettings();
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		ContactListForm clf = new ContactListForm();
		ContactForm cf = new ContactForm();
		
		ContactListTreeViewModel ctvm = new ContactListTreeViewModel();
		
		ctvm.setContactListForm(clf);
		clf.setCtvm(ctvm);
		
		ctvm.setContactForm(cf);
		cf.setCtvm(ctvm);
		
		RootPanel.get("Navigator").clear();
		RootPanel.get("Navigator").add(ctvm);
	    
	    
		
	}
}
