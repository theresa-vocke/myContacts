package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.vocke.myContacts.client.ClientsideSettings;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;
import de.hdm.vocke.myContacts.shared.bo.Contact;

public class ContactForm extends VerticalPanel {
	
	MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	Contact contactToDisplay = null;
	ContactListTreeViewModel cltvm = null;	
	/**
	 * Anlegen der GUI Elemente 
	 */
	
	private VerticalPanel vpanel = new VerticalPanel();
	
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
		
		deleteButton.addClickHandler(new CancelClickHandler());
		deleteButton.setEnabled(false);
		contactGrid.setWidget(8, 0, deleteButton);
		
		saveButton.addClickHandler(new SaveClickHandler());
		saveButton.setEnabled(false);
		contactGrid.setWidget(8, 1, saveButton);	
		
		this.vpanel.add(contactGrid);
	}
	
	
	private class CancelClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
						
		}
	}

	private class SaveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			
		}
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
			phoneNumberTextBox.setText(contactToDisplay.getPhonenumber());
			streetTextBox.setText(contactToDisplay.getStreet());
			numberTextBox.setText(contactToDisplay.getNumber());
			cityTextBox.setText(contactToDisplay.getCity());
			birthdayTextBox.setText(contactToDisplay.getBirthdate());
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
