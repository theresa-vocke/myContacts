package de.hdm.vocke.myContacts.client.gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;
import com.google.gwt.view.client.SelectionChangeEvent;

import de.hdm.vocke.myContacts.client.ClientsideSettings;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;
import de.hdm.vocke.myContacts.shared.bo.BusinessObject;
import de.hdm.vocke.myContacts.client.gui.ContactCell;
import de.hdm.vocke.myContacts.client.gui.ContactListCell;

/**
 * Die Klasse <code>ContactListTreeViewModel</code> dient zur Verwaltung
 * der Baumstruktur. Bei der Implementierung wurde sich an Professor Rathkes
 * Implementierung einer Baumstruktur aus dem Bankprojekt orientiert.
 */

public class ContactListTreeViewModel extends VerticalPanel implements TreeViewModel {

	private ContactForm contactForm;
	private ContactListForm contactListForm;
	
	private Contact selectedContact = null;
	private ContactList selectedContactList = null;
	
	private ContactListTreeViewModel contactTreeModel;
	private CellTree navigationCellTree;
	private ScrollPanel navigationTreePanel = new ScrollPanel();
	private VerticalPanel treeContainer = new VerticalPanel();
	private Label navigationHeadline = new Label("Kontaktlisten");
	
	private MyContactsAsync myContacts = null;
	
		
	// ListDataProvider gef�llt mit Kontaktlisten-Objekten
	private ListDataProvider<ContactList> contactListDataProvider = null;

	/*
	 * Map --> was genau passiert hier? 
	 */
	private Map<ContactList, ListDataProvider<Contact>> contactDataProvider = null;
	
	/**
	 * Durch die Klasse BusinessObjectKeyProvider k�nnen Kontaktlisten- von Kontaktobjekten 
	 * unterschieden werden, auch wenn sie dieselbe id haben.
	 */
	
	private class BusinessObjectKeyProvider implements ProvidesKey<BusinessObject> {
		
		//von der getKey-Mehtode wird ein integer wert als R�ckgabewert erwartet
		//aus den Anwendungsobjekten muss ein eindeutiger Schl�ssel erzeugt werden (durch ProvidesKey)
		@Override
		public Integer getKey(BusinessObject bo) {
			if (bo == null) {
				return null;
			}
			// pr�fen, ob bo ContactList ist, dann integer von id zur�ck geben 
			if (bo instanceof ContactList) {
				return new Integer(bo.getId());
			} else {
				// wenn bo keine ContactList, sondern dann Contact ist, negieren und negative id zur�ck geben
				return new Integer(-bo.getId());
			}
		}

	};
	
	private BusinessObjectKeyProvider boKeyProvider = null;
	private SingleSelectionModel<BusinessObject> selectionModel = null;
	
	protected void onLoad(){
		super.onLoad();
		
		contactTreeModel = new ContactListTreeViewModel();
		navigationCellTree = new CellTree(contactTreeModel, "Root");
		
		navigationCellTree.setAnimationEnabled(true);
		navigationTreePanel.add(navigationCellTree);
		
		treeContainer.add(navigationHeadline);
		navigationTreePanel.setStylePrimaryName("TreeContainerPanel");
		navigationHeadline.setStylePrimaryName("NavigationPanelHeadline");
		treeContainer.add(navigationTreePanel);
		navigationCellTree.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		RootPanel.get("Navigator").clear();
		RootPanel.get("Navigator").add(treeContainer);
		
	}
	
	
	

	
	/**
	 * Nested Class f�r die Reaktion auf Selektionsereignisse. Als Folge einer
	 * Baumknotenauswahl wird je nach Typ des Business-Objekts die
	 * "selectedContactList" bzw. der "selectedContact" gesetzt.
	 */
	
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler{

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection  = selectionModel.getSelectedObject();
			
			if (selection instanceof ContactList){
				setSelectedContactList((ContactList) selection);
				//nochmal pr�fen 
			} else if (selection instanceof Contact){
				setSelectedContact((Contact) selection);
				//nochmal pr�fen
			}
					
		}
		
	}
	
	/*
	 * Im Konstruktor werden die f�r den Kontaktlisten- und Kontaktbaum wichtigen lokalen
	 * Variaben initialisiert.
	 */
	
	public ContactListTreeViewModel(){
		myContacts = ClientsideSettings.getMyContacts();
		boKeyProvider = new BusinessObjectKeyProvider();
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		contactDataProvider = new HashMap<ContactList, ListDataProvider<Contact>>();
					
	}
	
	public void setContactListForm(ContactListForm clf) {
		contactListForm = clf;
	}

	public void setContactForm(ContactForm cf) {
		contactForm = cf;
	}

	public ContactList getSelectedContactList() {
		return selectedContactList;
	}

	void setSelectedContactList(ContactList cl) {
		selectedContactList = cl;
		contactListForm.setSelected(cl);
		selectedContact = null;
		contactForm.setSelected(null);
	}

	public Contact getSelectedContact() {
		return selectedContact;
	}
	
	/*
	 * Wenn ein Kontakt ausgew�hlt wird, wird auch die ausgew�hlt Kontaktliste
	 * angepasst.
	 */
	void setSelectedContact(Contact c) {
		selectedContact = c;
		contactForm.setSelected(c);

		if (c != null) {
			myContacts.findContactListById(c.getContactListId(), new AsyncCallback<ContactList>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onSuccess(ContactList result) {
					selectedContactList = result;
					contactListForm.setSelected(result);

				}

			});
		}
	}
	
	/*
	 * Wenn eine Kontaktliste neu erzeugt wurde, wird er selektiert.
	 */
	
	void addContatList(ContactList contactList) {
		contactListDataProvider.getList().add(contactList);
		selectionModel.setSelected(contactList, true);
	}
	
	void updateContactList(ContactList contactList){
		List<ContactList> contactListList = contactListDataProvider.getList();
		int i = 0;
		for(ContactList cl : contactListList){
			if(cl.getId() == cl.getId()){
				contactListList.set(i, contactList);
				break;
			}else {
				i++;
			}
		}
		contactListDataProvider.refresh();
	}
	
	
	void removeKontaktliste(ContactList contactList){
		contactListDataProvider.getList().remove(contactList);
		contactDataProvider.remove(contactList);
	}
	
	void addContactToContactList(Contact contact, ContactList contactList){
		if(!contactDataProvider.containsKey(contactList)){
			return;
		}
		ListDataProvider<Contact> contactProvider = contactDataProvider.get(contactList);
		if(!contactProvider.getList().contains(contact)){
			contactProvider.getList().add(contact);
		}
		selectionModel.setSelected(contact, true);
	}
	
	void removeContactFromContactList(Contact contact, ContactList contactList){
		if(!contactDataProvider.containsKey(contactList)){
			return;
		}
		contactDataProvider.get(contactList).getList().remove(contact);
		selectionModel.setSelected(contactList, true);
	}
	
	void updateContact(Contact c) {
		//myContacts.findContactListById(contactListId, callback);,
				//new UpdateContactCallback(c));
	}
	
//	private class UpdateContactCallback implements AsyncCallback<Contact> {
//
//		Contact contact = null;
//
//		UpdateContactCallback(Contact c) {
//			contact = c;
//		}
//
//		@Override
//		public void onFailure(Throwable t) {
//		}
//
//		@Override
//		public void onSuccess(Contact result) {
//			// TODO Auto-generated method stub
//			List<Contact> contactList1 = contactDataProvider.get(contact)
//					.getList();
//			for (int i=0; i<contactList1.size(); i++) {
//				if (contact.getId() == contactList1.get(i).getId()) {
//					contactList1.set(i, contact);
//					break;
//				}
//			}
//		}
//	}
	
	// Get the NodeInfo that provides the children of the specified value.
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		
		if (value.equals("Root")) {
			// Erzeugen eines ListDataProviders für die Kontaktdaten 
			contactListDataProvider = new ListDataProvider<ContactList>();
			myContacts.getAllContactLists(new AsyncCallback<Vector<ContactList>>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Fehler: " + caught.getMessage());
							
						}

						public void onSuccess(Vector<ContactList> result) {
							Window.alert("------");
							for (ContactList cl : result) {
								contactListDataProvider.getList().add(cl);
							}

						}

					});

			return new DefaultNodeInfo<ContactList>(contactListDataProvider, new ContactListCell(), selectionModel,
					null);
		}

		if (value instanceof ContactList) {
			//ListdataProvider erzeugen, in dem die Kontakt-Daten geladen werden 
			final ListDataProvider<Contact> contactProvider = new ListDataProvider<Contact>();
			contactDataProvider.put((ContactList) value, contactProvider);
			
			//myContacts.findAllContactsByContactListId(contactListId, new AsyncCallback<Vector<Contact>>(){
			myContacts.findAllContactsByContactListId((ContactList) value, new AsyncCallback<Vector<Contact>>(){
				
				@Override
				public void onFailure(Throwable caught) {
						Window.alert("Fehler beim Laden" + caught.getMessage());
				}

				@Override
				public void onSuccess(Vector<Contact> result) {
					for (Contact c : result) {
						contactProvider.getList().add(c);
					}

				}

			});

			return new DefaultNodeInfo<Contact>(contactProvider, new ContactCell(), selectionModel, null);
		}
		
		
		return null;
	}
	
	@Override
	public boolean isLeaf(Object value) {
		return (value instanceof Contact);
	}
	
	
}
