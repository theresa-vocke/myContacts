package de.hdm.vocke.myContacts.client.gui;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.vocke.myContacts.client.ClientsideSettings;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class DialogBoxContactToContactList extends DialogBox {
	
	MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	ContactListTreeViewModel ctvm = null;	
	
	private VerticalPanel vPanel = new VerticalPanel();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private Label frage= new HTML("In welche Kontaktlisten möchten Sie die Auswahl hinzufügen?<br>");
	private Button speichern = new Button("Speichern");
	private Button abbrechen = new Button("Abbrechen");
	private ListBox kontaktListenBox = new ListBox();
	private ArrayList<ContactList> alleKontaktListen = new ArrayList<>();
	
	public DialogBoxContactToContactList(){
		
		ContactList alleKontaktListen = myContacts.getAllContactLists(new AllContactListCallback());
		// füge solange ein Item hinzu, bis die ArrayList leer ist 
		kontaktListenBox.addItem();

		speichern.addClickHandler(new AddClickHandler());
		abbrechen.addClickHandler(new AbortClickHandler());
		//myContacts.findAllKontaktlisteByNutzerID(nutzer.getId(), new AllKontaktlisteByNutzerCallback());
	}
	
	public class AllContactListCallback implements AsyncCallback<ArrayList<ContactList>> {

		@Override
		public void onFailure(Throwable caught) {
			
		}

		@Override
		public void onSuccess(ArrayList<ContactList> result) {
			alleKontaktListen.clear();
			alleKontaktListen.addAll(result);
		}	
	}
	
	public class AddClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			for (Contact  contact : contacts) {
				for (ContactList contactList : selectionModel.getSelectedSet()) {
					myContacts.createKontaktKontaktliste(contact.getId(), contactList.getId(), 
							new CreateKontaktKontaktlisteCallback());

				}
			}
		}

	}
	
	public class AbortClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
			hide();
		}

	}
	
	
	
}
