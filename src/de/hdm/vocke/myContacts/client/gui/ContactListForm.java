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
import de.hdm.vocke.myContacts.shared.bo.ContactList;

//Formular für die Darstellung von selektierten Kontaktlisten 

public class ContactListForm extends VerticalPanel {

	MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	ContactList contactListToDisplay = null;
	ContactListTreeViewModel ctvm= null;
	
	/**
	 * Anlegen der GUI Elemente 
	 */
	
	private VerticalPanel vpanel = new VerticalPanel();
	
	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	TextBox titleTextBox = new TextBox();
	Label idValueLabel = new Label("Kontaktliste: ");
	Button deleteButton = new Button("Löschen");
	Button saveButton = new Button("Speichern");

	

	/*
	 * Beim Anzeigen werden die anderen Widgets erzeugt. Alle werden in
	 * einem Raster angeordnet, dessen Größe sich aus dem Platzbedarf
	 * der enthaltenen Widgets bestimmt.
	 */
	
	public void onLoad() {
		super.onLoad();
		
		Grid contactListGrid = new Grid(3, 2);
		this.add(contactListGrid);

		Label titleLabel = new Label("Bezeichnung");
		contactListGrid.setWidget(1, 0, titleLabel);
		contactListGrid.setWidget(1, 1, titleTextBox);
					
		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(false);
		contactListGrid.setWidget(2, 1, deleteButton);
		
		saveButton.addClickHandler(new SaveClickHandler());
		saveButton.setEnabled(false);
		contactListGrid.setWidget(2, 0, saveButton);	
		
		this.vpanel.add(contactListGrid);
	}
	
	//Click Handler
	
	
	
	private class DeleteClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			if (contactListToDisplay == null) {
				Window.alert("keine Kontaktliste ausgewählt");
			} else {
				myContacts.delete(contactListToDisplay,
						new deleteContactListCallback(contactListToDisplay));
			}
		}
	}

	class deleteContactListCallback implements AsyncCallback<Void> {

		ContactList contactList = null;

		deleteContactListCallback(ContactList cl) {
			contactList = cl;
		}

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Das Löschen des Kunden ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			if (contactList != null) {
				setSelected(null);
				ctvm.removeKontaktliste(contactList);
			}
		}
	}

	/*
	 * Die Änderung einer Kontaktliste bezieht sich auf den Titel 
	 * Die Änderung erfolgt direkt im Textfeld
	 * erfolgt durch die Service-Methode save
	 */
	
	private class SaveClickHandler implements ClickHandler{
		@Override
		public void onClick(ClickEvent event) {
			if (contactListToDisplay != null) {
				contactListToDisplay.setName(titleTextBox.getText());
				myContacts.save(contactListToDisplay, new SaveCallback());
			} else {
				Window.alert("keinen Kontakt ausgewählt");
			}
		}
	}

	private class SaveCallback implements AsyncCallback<Void> {
		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Die Namensänderung ist fehlgeschlagen!");
		}

		@Override
		public void onSuccess(Void result) {
			// Die Änderung wird zum Kunden- und Kontenbaum propagiert.
			ctvm.updateContactList(contactListToDisplay);
		}
	}
	
	public void setCtvm(ContactListTreeViewModel ctvm) {
		this.ctvm = ctvm;
	}
	
	/*
	 * Wenn die anzuzeigende Kontaktliste gesetzt bzw. gelöscht wird, werden die
	 * zugehörenden Textfelder mit den Informationen aus dem KontaktlistenObjekt
	 * gefüllt bzw. gelöscht.
	 */
	void setSelected(ContactList cl) {
		if (cl != null) {
			contactListToDisplay = cl;
			deleteButton.setEnabled(true);
			saveButton.setEnabled(true);
			titleTextBox.setText(contactListToDisplay.getName());
			idValueLabel.setText("Kontaktliste: " + Integer.toString(contactListToDisplay.getId()));
		} else {
			titleTextBox.setText("");
			idValueLabel.setText("Kontaktliste: ");
			deleteButton.setEnabled(false);
			saveButton.setEnabled(false);
		}
	}
	
}
