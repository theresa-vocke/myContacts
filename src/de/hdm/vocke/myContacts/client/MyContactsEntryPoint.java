package de.hdm.vocke.myContacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.CellTree.Style;

import de.hdm.vocke.myContacts.client.gui.ContactForm;
import de.hdm.vocke.myContacts.client.gui.ContactListForm;
import de.hdm.vocke.myContacts.client.gui.ContactListTreeViewModel;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyContactsEntryPoint implements EntryPoint {
	
	static interface ContactTreeRecources extends CellTree.Resources {
	    @Source("ContactCellTree.css")
	    public Style contactCellTreeStyle();
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
		
		/*
		 * Die Panels und der CellTree werden erzeugt und angeordnet und in das RootPanel eingefügt.
		 */
		VerticalPanel detailsPanel = new VerticalPanel();
		detailsPanel.add(cf);
		detailsPanel.add(clf);

		CellTree.Resources bankTreeResource = GWT.create(ContactTreeRecources.class);
		CellTree cellTree = new CellTree(ctvm, "Root", bankTreeResource);
		cellTree.setAnimationEnabled(true);
		
		RootPanel.get("Navigator").clear();
		RootPanel.get("Navigator").add(ctvm);
	    
	    
		
	}
}
