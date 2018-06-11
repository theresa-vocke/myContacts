package de.hdm.vocke.myContacts.client.gui;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.google.gwt.dev.util.collect.HashMap;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
 *
 */

public class ContactListTreeViewModel implements TreeViewModel {

	private ContactForm contactForm;
	private ContactListForm contactListForm;
	
	private Contact selectedContact = null;
	private ContactList selectedContactList = null;
	
	private MyContactsAsync myContacts = null;
	
		
	// ListDataProvider gefüllt mit Kontaktlisten-Objekten
	private ListDataProvider<ContactList> contactListDataProvider = null;

	/*
	 * In dieser Map merken wir uns die ListDataProviders für die Kontaktlisten
	 * der im Kontakt- und Kontaktlistenbaum expandierten Kontaktknoten.
	 */
	private Map<ContactList, ListDataProvider<Contact>> contactDataProvider = null;
	
	/**
	 * Bildet BusinessObjects auf eindeutige Zahlenobjekte ab, die als Schlüssel
	 * für Baumknoten dienen. Dadurch werden im Selektionsmodell alle Objekte
	 * mit derselben id selektiert, wenn eines davon selektiert wird. Der
	 * Schlüssel für Kontaktlistenobjekte ist eine positive, der für Kontaktobjekte eine
	 * negative Zahl, die sich jeweils aus der id des Objektes ergibt. Dadurch
	 * können Kontaktlisten- von Kontaktobjekten unterschieden werden, auch wenn sie
	 * dieselbe id haben.
	 */
	
	private class BusinessObjectKeyProvider implements ProvidesKey<BusinessObject> {
		
		@Override
		public Object getKey(BusinessObject bo) {
			if (bo == null) {
				return null;
			}
			if (bo instanceof ContactList) {
				return new Integer(bo.getId());
			} else {
				return new Integer(-bo.getId());
			}
		}

	};
	
	private BusinessObjectKeyProvider boKeyProvider = null;
	private SingleSelectionModel<BusinessObject> selectionModel = null;
	
	/**
	 * Nested Class für die Reaktion auf Selektionsereignisse. Als Folge einer
	 * Baumknotenauswahl wird je nach Typ des Business-Objekts die
	 * "selectedContactList" bzw. der "selectedContact" gesetzt.
	 */
	
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler{

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection  = selectionModel.getSelectedObject();
			
			if (selection instanceof ContactList){
				setSelectedContactList((ContactList) selection);
				//nochmal prüfen 
			} else if (selection instanceof Contact){
				setSelectedContact((Contact) selection);
				//nochmal prüfen
			}
					
		}
		
	}
	
	/*
	 * Im Konstruktor werden die für den Kontaktlisten- und Kontaktbaum wichtigen lokalen
	 * Variaben initialisiert.
	 */
	
	public ContactListTreeViewModel(){
		myContacts = ClientsideSettings.getMyContacts();
		boKeyProvider = new BusinessObjectKeyProvider();
		selectionModel = new SingleSelectionModel<BusinessObject>(boKeyProvider);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEventHandler());
		contactDataProvider = new HashMap<ContactList, ListDataProvider<Contact>>();
					
	}
	
	void setCustomerForm(ContactListForm clf) {
		contactListForm = clf;
	}

	void setAccountForm(ContactForm cf) {
		contactForm = cf;
	}

	ContactList getSelectedCustomer() {
		return selectedContactList;
	}

	void setSelectedContactList(ContactList cl) {
		selectedContactList = cl;
		contactListForm.setSelected(cl);
		selectedContact = null;
		contactForm.setSelected(null);
	}

	Contact getSelectedContact() {
		return selectedContact;
	}
	
	/*
	 * Wenn ein Kontakt ausgewählt wird, wird auch die ausgewählt Kontaktliste
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
	
	// Get the NodeInfo that provides the children of the specified value.
	
	@Override
	public <T> NodeInfo<?> getNodeInfo(T value) {
		
		if (value.equals("Root")) {

			contactListDataProvider = new ListDataProvider<ContactList>();
			myContacts.getAllContactLists(new AsyncCallback<Vector<ContactList>>() {

						@Override
						public void onFailure(Throwable caught) {
						}

						public void onSuccess(Vector<ContactList> result) {
							for (ContactList cl : result) {
								contactListDataProvider.getList().add(cl);
							}

						}

					});

			return new DefaultNodeInfo<ContactList>(contactListDataProvider, new ContactListCell(), selectionModel,
					null);
		}

		if (value instanceof ContactList) {

			final ListDataProvider<Contact> contactProvider = new ListDataProvider<Contact>();
			contactDataProvider.put((ContactList) value, contactProvider);
			int contactListId = ((ContactList) value).getId();
			myContacts.findAllContactsByContactListId(contactListId, new AsyncCallback<Vector<Contact>>(){

				@Override
				public void onFailure(Throwable caught) {

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
