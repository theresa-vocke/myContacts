package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.vocke.myContacts.client.ClientsideSettings;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;
import de.hdm.vocke.myContacts.shared.bo.Contact;
//import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class ContactForm extends VerticalPanel {
	
	MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	Contact contactToDisplay = null;
	ContactListTreeViewModel ctvm = null;	
	/**
	 * Anlegen der GUI Elemente 
	 */
	
	//private VerticalPanel vpanel = new VerticalPanel();
	
	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	TextBox firstNameTextBox = new TextBox();
	TextBox lastNameTextBox = new TextBox();
	TextBox phoneNumberTextBox = new TextBox();
	TextBox streetTextBox = new TextBox();
	TextBox numberTextBox = new TextBox();
	TextBox cityTextBox = new TextBox();
	TextBox birthdayTextBox = new TextBox();
	Label idValueLabel = new Label("Kontakt: ");
	Button deleteButton = new Button("Löschen");
	Button saveButton = new Button("Speichern");
	

	/*
	 * Beim Anzeigen werden die anderen Widgets erzeugt. Alle werden in
	 * einem Raster angeordnet, dessen Größe sich aus dem Platzbedarf
	 * der enthaltenen Widgets bestimmt.
	 */
	
	public void onLoad() {
		super.onLoad();
		
		Grid contactGrid = new Grid(9, 2);
		this.add(contactGrid);

		Label firstNameLabel = new Label("Vorname");
		contactGrid.setWidget(1, 0, firstNameLabel);
		contactGrid.setWidget(1, 1, firstNameTextBox);
		
		Label lastNameLabel = new Label("Nachname");
		contactGrid.setWidget(2, 0, lastNameLabel);
		contactGrid.setWidget(2, 1, lastNameTextBox);

		Label phoneNumberLabel = new Label("Telefonnummer");
		contactGrid.setWidget(3, 0, phoneNumberLabel);
		contactGrid.setWidget(3, 1, phoneNumberTextBox);
		
		Label streetLabel = new Label("Straße");
		contactGrid.setWidget(4, 0, streetLabel);
		contactGrid.setWidget(4, 1, streetTextBox);
		
		Label numberLabel = new Label("Hausnummer");
		contactGrid.setWidget(5, 0, numberLabel);
		contactGrid.setWidget(5, 1, numberTextBox);
		
		Label cityLabel = new Label("Stadt");
		contactGrid.setWidget(6, 0, cityLabel);
		contactGrid.setWidget(6, 1, cityTextBox);
		
		Label birthdayLabel = new Label("Geburtstag");
		contactGrid.setWidget(7, 0, birthdayLabel);
		contactGrid.setWidget(7, 1, birthdayTextBox);
		
		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(false);
		contactGrid.setWidget(8, 0, deleteButton);
		
		saveButton.addClickHandler(new SaveClickHandler());
		saveButton.setEnabled(false);
		contactGrid.setWidget(8, 1, saveButton);	
		
		//this.vpanel.add(contactGrid);
	}
	
	/*
	 * Click-Handler
	 */
	
	
	
	private class DeleteClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
//			if (contactToDisplay == null) {
//				Window.alert("keinen Kontakt ausgewählt");
//			} else {
//				myContacts.findContactById(contactToDisplay.getId(),
//						new useContactListForContactDeletionCallback(contactToDisplay));
//			}			
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
//		@Override
//		public void onFailure(Throwable caught) {
//		}
//
//		public void onSuccess(ContactList contactList) {
//			if (contactList != null && contact != null) {
//				myContacts.delete(contact, new deleteContactCallback(
//						contact, contactList));
//			}			
//		}
//	}
	
	/*
	 * Da wir uns Kontaktliste und Kontakt merken müssen, um den Kontaktlisen- und Kontaktbaum 
	 * nach erfolgter Kontaktlöschung zu aktualisieren, hat diese Callback-Klasse
	 * private Attribute und einen Konstruktor, in dem diese Wert abgespeichert
	 * bzw. übergeben werden.
	 * 
	 * Nach erfolgter Löschung werden diese Werte verwendet.
	 */
//	private class deleteContactCallback implements AsyncCallback<Void> {
//
//		private ContactList contactList = null;
//		private Contact contact = null;
//
//		deleteContactCallback(Contact c, ContactList cl) {
//			contact = c;
//			contactList = cl;
//		}
//
//		@Override
//		public void onFailure(Throwable caught) {
//
//		}
//
//		@Override
//		public void onSuccess(Void result) {
//			setSelected(null);
//			if (contactList != null) {
//				ctvm.removeContactFromContactList(contact, contactList);
//			}
//		}
//	}
	
	
	private class SaveClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
			
//			ContactList selectedContactList = ctvm.getSelectedContactList();
//			Contact selectedContact = ctvm.getSelectedContact();
//			if (selectedContactList == null) {
//				Window.alert("keinen Kontakt ausgewählt");
//			} else {
//				myContacts.createContactToContactList(selectedContact, selectedContactList, 
//						new CreateContactCallback(selectedContactList));
//			}
		}
	}
	
	/*
	 * Hier muss der Kontakt-und Kontaktlistenbaum aktualisiert werden, wenn ein Kontakt erzeugt wurde 
	 * daher ein privates Attribu und der Konstruktor 
	 * 
	 * Wir benötigen hier nur einen Parameter für die Kontaktliste, da der Kontakt als
	 * Ergebnis des asynchronen Aufrufs geliefert wird.
	 */
	
//	private class CreateContactCallback implements AsyncCallback<Contact> {
//	
//		ContactList contactList = null;
//	
//		CreateContactCallback(ContactList cl) {
//			contactList = cl;
//		}
//	
//		@Override
//		public void onFailure(Throwable caught) {
//			// this.showcase.append("Fehler bei der Abfrage " +
//			// caught.getMessage());
//		}
//	
//		@Override
//		public void onSuccess(Contact contact) {
//			if (contact != null && contactList != null) {
//				ctvm.addContactToContactList(contact, contactList);
//			}
//		}
//	}	

	public void setCtvm(ContactListTreeViewModel ctvm) {
		this.ctvm = ctvm;
	}
	
	/*
	 * Wenn der anzuzeigende Kontakt gesetzt bzw. gelöscht wird, werden die
	 * zugehörenden Textfelder mit den Informationen aus dem Kontaktobjekt gefüllt
	 * bzw. gelöscht. 
	 */
	
	void setSelected(Contact c) {
		if (c != null) {
			contactToDisplay = c;
			deleteButton.setEnabled(true);
			saveButton.setEnabled(true);
			firstNameTextBox.setText(contactToDisplay.getFirstName());
			lastNameTextBox.setText(contactToDisplay.getLastName());
//			phoneNumberTextBox.setText(contactToDisplay.getPhonenumber());
//			streetTextBox.setText(contactToDisplay.getStreet());
//			numberTextBox.setText(contactToDisplay.getNumber());
//			cityTextBox.setText(contactToDisplay.getCity());
//			birthdayTextBox.setText(contactToDisplay.getBirthdate());
			idValueLabel.setText("Kontakt: " + Integer.toString(contactToDisplay.getId()));

		} else {
			contactToDisplay = null;
			deleteButton.setEnabled(false);
			saveButton.setEnabled(false);
			firstNameTextBox.setText("");
			lastNameTextBox.setText("");
			phoneNumberTextBox.setText("");
			streetTextBox.setText("");
			numberTextBox.setText("");
			cityTextBox.setText("");
			birthdayTextBox.setText("");
			this.idValueLabel.setText("Kontakt: ");
			
		}
	}


	
	
	
	
	
}
