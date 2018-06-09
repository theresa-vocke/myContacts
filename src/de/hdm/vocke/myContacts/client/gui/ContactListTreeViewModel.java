package de.hdm.vocke.myContacts.client.gui;

import java.util.Map;
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
import de.hdm.vocke.myContacts.shared.MyContacts;

/**
 * Die Klasse <code>ContactListTreeViewModel</code> dient zur Verwaltung
 * der Baumstruktur. Bei der Implementierung wurde sich an Professor Rathkes
 * Implementierung einer Baumstruktur aus dem Bankprojekt orientiert.
 *
 */

public class ContactListTreeViewModel implements TreeViewModel {

	private ContactForm contactForm;
	private ContactListForm contactListform;
	
	private Contact selectedContact = null;
	private ContactList selectedContactList = null;
	
	private MyContactsAsync meineKontakte = null;
	
		
	// ListDataProvider gef�llt mit Kontaktlisten-Objekten
	private ListDataProvider<ContactList> contactListDataProvider = null;

	/*
	 * In dieser Map merken wir uns die ListDataProviders f�r die Kontaktlisten
	 * der im Kontakt- und Kontaktlistenbaum expandierten Kontaktknoten.
	 */
	private Map<ContactList, ListDataProvider<Contact>> contactDataProvider = null;
	
	/**
	 * Bildet BusinessObjects auf eindeutige Zahlenobjekte ab, die als Schl�ssel
	 * f�r Baumknoten dienen. Dadurch werden im Selektionsmodell alle Objekte
	 * mit derselben id selektiert, wenn eines davon selektiert wird. Der
	 * Schl�ssel f�r Kontaktlistenobjekte ist eine positive, der f�r Kontaktobjekte eine
	 * negative Zahl, die sich jeweils aus der id des Objektes ergibt. Dadurch
	 * k�nnen Kontaktlisten- von Kontaktobjekten unterschieden werden, auch wenn sie
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
	 * Nested Class f�r die Reaktion auf Selektionsereignisse. Als Folge einer
	 * Baumknotenauswahl wird je nach Typ des Business-Objekts die
	 * "selectedContactList" bzw. der "selectedContact" gesetzt.
	 */
	
	private class SelectionChangeEventHandler implements SelectionChangeEvent.Handler{

		@Override
		public void onSelectionChange(SelectionChangeEvent event) {
			BusinessObject selection  = selectionModel.getSelectedObject();
			
			if (selection instanceof ContactList){
				setSelectedContactList((contactList) selection);
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
		MyContacts = ClientsideSettings.getMyContacts();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
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
