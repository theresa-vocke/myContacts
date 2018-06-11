package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.hdm.vocke.myContacts.client.ClientsideSettings;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;
import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class ContactListForm extends VerticalPanel {

	MyContactsAsync myContacts = ClientsideSettings.getMyContacts();
	ContactList contactListToDisplay = null;
	ContactListTreeViewModel cltvm = null;	
	/**
	 * Anlegen der GUI Elemente 
	 */
	
	private VerticalPanel vpanel = new VerticalPanel();
	
	/*
	 * Widgets, deren Inhalte variable sind, werden als Attribute angelegt.
	 */
	TextBox TitleTextBox = new TextBox();
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

		Label TitleLabel = new Label("Bezeichnung");
		contactListGrid.setWidget(1, 0, TitleLabel);
		contactListGrid.setWidget(1, 1, TitleTextBox);
					
		deleteButton.addClickHandler(new CancelClickHandler());
		deleteButton.setEnabled(false);
		contactListGrid.setWidget(2, 1, deleteButton);
		
		saveButton.addClickHandler(new SaveClickHandler());
		saveButton.setEnabled(false);
		contactListGrid.setWidget(2, 0, saveButton);	
		
		this.vpanel.add(contactListGrid);
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
	 * Wenn die anzuzeigende Kontaktliste gesetzt bzw. gelöscht wird, werden die
	 * zugehörenden Textfelder mit den Informationen aus dem KontaktlistenObjekt
	 * gefüllt bzw. gelöscht.
	 */
	void setSelected(ContactList cl) {
		if (cl != null) {
			contactListToDisplay = cl;
			deleteButton.setEnabled(true);
			saveButton.setEnabled(true);
			TitleTextBox.setText(contactListToDisplay.getName());
			idValueLabel.setText("Kontaktliste: " + Integer.toString(contactListToDisplay.getId()));
		} else {
			TitleTextBox.setText("");
			idValueLabel.setText("Kontaktliste: ");
			deleteButton.setEnabled(false);
			saveButton.setEnabled(false);
		}
	}
	
}
