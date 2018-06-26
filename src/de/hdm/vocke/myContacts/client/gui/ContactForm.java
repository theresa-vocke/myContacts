package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.vocke.myContacts.client.ClientsideSettings;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;
import de.hdm.vocke.myContacts.shared.bo.Contact;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class ContactForm extends VerticalPanel {
	
	// myContacts wird immer genutzt, wenn ich eine Methode in der Impl aufrufe 
	MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	// Instanz von Contact anlegen 
	Contact contactToDisplay = null;
	// Instanz von TeeViewModel anlegen 
	ContactListTreeViewModel ctvm = null;	
	
	/**
	 * Anlegen der GUI Elemente 
	 */

	
	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	TextBox firstNameTextBox = new TextBox();
	TextBox lastNameTextBox = new TextBox();
	TextBox telefonnummerTextBox = new TextBox();
	TextBox adresseTextBox = new TextBox();
	Label idValueLabel = new Label("Kontakt: ");
	Button addButton = new Button ("Kontakt zu Kontaktliste hinzufügen");
	Button newButton = new Button ("Als neuen Kontakt speichern");
	Button deleteButton = new Button("Löschen");
	Button saveButton = new Button("Speichern");
	

	/*
	 * Beim Anzeigen werden die anderen Widgets erzeugt. Alle werden in
	 * einem Raster angeordnet, dessen Gr��e sich aus dem Platzbedarf
	 * der enthaltenen Widgets bestimmt.
	 */
	
	public void onLoad() {
		super.onLoad();
		
		Grid contactGrid = new Grid(9, 2);
		this.add(contactGrid);
		
		contactGrid.setWidget(0, 0, idValueLabel);
		
		Label firstNameLabel = new Label("Vorname");
		contactGrid.setWidget(1, 0, firstNameLabel);
		contactGrid.setWidget(1, 1, firstNameTextBox);
		
		Label lastNameLabel = new Label("Nachname");
		contactGrid.setWidget(2, 0, lastNameLabel);
		contactGrid.setWidget(2, 1, lastNameTextBox);

		Label telefonnummerLabel = new Label("Telefonnummer");
		contactGrid.setWidget(3, 0, telefonnummerLabel);
		contactGrid.setWidget(3, 1, telefonnummerTextBox);
		
		Label adresseLabel = new Label("Adresse");
		contactGrid.setWidget(4, 0, adresseLabel);
		contactGrid.setWidget(4, 1, adresseTextBox);
		
		addButton.addClickHandler(new AddClickHandler());
		addButton.setEnabled(false);
		contactGrid.setWidget(7, 0, addButton);
		
		newButton.addClickHandler(new NewClickHandler());
		newButton.setEnabled(false);
		contactGrid.setWidget(7, 1, newButton);
		
		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(false);
		contactGrid.setWidget(8, 0, deleteButton);
		
		saveButton.addClickHandler(new SaveClickHandler());
		saveButton.setEnabled(false);
		contactGrid.setWidget(8, 1, saveButton);	
		
	}
	
	/*
	 * Click-Handler
	 */
	// privat, weil sie nur in ContactForm aufgerufen werden 
	// hier soll ein Kontakt zu einer Kontaktliste hinzugefügt werden 
	private class AddClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event){
			if (contactToDisplay == null) {
				Window.alert("keinen Kontakt ausgewählt");
			} else {
				// hier muss die Dialogbox aufgerufen werden 
			}
		}
	}
	
	/*
	 * Ein vorhandenes Kontakt Objekt wird ausgewählt und daran werden Änderungen vorgenommen
	 * Das Objekt wird nicht geupdatet sondern hieraus wird ein neues Kontakt-Objekt erzeugt
	 */
	
	private class NewClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event){
			if (contactToDisplay == null) {
				Window.alert("keinen Kontakt ausgewählt");
			} else {
//
				//myContacts.createContact(firstNameTextBox.getText(), lastNameTextBox.getText(), telefonnummerTextBox.getText(),
				//adresseTextBox.getText(), new AsyncCallback<Contact>);
			}
		}
	}

	
	
	/*
	 * Ein ausgewähltes Konakt-Objekt wird gelöscht
	 */
	
	private class DeleteClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			if (contactToDisplay == null) {
				Window.alert("keinen Kontakt ausgewählt");
			} else {
//				myContacts.findContactById(contactToDisplay.getId(),
//						new useContactListForContactDeletionCallback(contactToDisplay));
			}			
		}
	}
	
//	private class useContactListForContactDeletionCallback implements
//	AsyncCallback<Contact> {
//		
//		Contact contact = null;
//
//		useContactListForContactDeletionCallback(Contact c) {
//			contact = c;
//		}
//
//		
//		public void onFailure(Throwable caught) {
//		}
//		
//		@Override
//		public void onSuccess(ContactList contactList) {
//			if (contactList != null && contact != null) {
//				myContacts.delete(contact, new deleteContactCallback(
//						contact, contactList));
//			}
//		}
//	}
	
	/*
	 * Kontaktliste und Kontakte müssen gemerkt werden, um den baum nach Kontaktlöschung zu aktualisiern 
	 * Deshalb hat die Callback-Klasse private Attribute und einen Konstruktor, in dem diese Werte abgespeichert 
	 * und somit übergeben werden können.
	 * 
	 * Wenn die Lösschung erfolgt ist, werden diese Werte verwendet
	 */
	private class deleteContactCallback implements AsyncCallback<Void> {

		private ContactList contactList = null;
		private Contact contact = null;

		deleteContactCallback(Contact c, ContactList cl) {
			contact = c;
			contactList = cl;
		}

		@Override
		public void onFailure(Throwable caught) {

		}

		@Override
		public void onSuccess(Void result) {
			setSelected(null);
			if (contactList != null) {
				ctvm.removeContactFromContactList(contact, contactList);
			}
		}
	}
	
	
	private class SaveClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			
			ContactList selectedContactList = ctvm.getSelectedContactList();
			Contact selectedContact = ctvm.getSelectedContact();
			if (selectedContactList == null) {
				Window.alert("keinen Kontakt ausgew�hlt");
			} else {
//				myContacts.createContactToContactList(selectedContact, selectedContactList, 
//						new SaveContactCallback(selectedContactList));
			}
		}
	}
	
	/*
	 * Hier muss der Kontakt-und Kontaktlistenbaum aktualisiert werden, wenn ein Kontakt erzeugt wurde 
	 * daher ein privates Attribu und der Konstruktor 
	 * 
	 * Wir ben�tigen hier nur einen Parameter f�r die Kontaktliste, da der Kontakt als
	 * Ergebnis des asynchronen Aufrufs geliefert wird.
	 */
	
	private class SaveContactCallback implements AsyncCallback<Contact> {
	
		ContactList contactList = null;
	
		SaveContactCallback(ContactList cl) {
			contactList = cl;
		}
	
		@Override
		public void onFailure(Throwable caught) {
		}
	
		@Override
		public void onSuccess(Contact contact) {
			if (contact != null && contactList != null) {
				ctvm.updateContact(contact);
			}
		}
	}	

	public void setCtvm(ContactListTreeViewModel ctvm) {
		this.ctvm = ctvm;
	}
	
	/*
	 * Wenn der anzuzeigende Kontakt gesetzt bzw. gel�scht wird, werden die
	 * zugeh�renden Textfelder mit den Informationen aus dem Kontaktobjekt gef�llt
	 * bzw. gel�scht. 
	 */
	
	void setSelected(Contact c) {
		if (c != null) {
			contactToDisplay = c;
			deleteButton.setEnabled(true);
			saveButton.setEnabled(true);
			firstNameTextBox.setText(contactToDisplay.getFirstName());
			lastNameTextBox.setText(contactToDisplay.getLastName());
			telefonnummerTextBox.setText(contactToDisplay.getTelefonnummer());
			adresseTextBox.setText(contactToDisplay.getAdresse());
			idValueLabel.setText("Kontakt: " + Integer.toString(contactToDisplay.getId()));

		} else {
			contactToDisplay = null;
			deleteButton.setEnabled(false);
			saveButton.setEnabled(false);
			firstNameTextBox.setText("");
			lastNameTextBox.setText("");
			telefonnummerTextBox.setText("");
			adresseTextBox.setText("");
//			this.idValueLabel.setText("Kontakt: ");
			
		}
	}

	}
