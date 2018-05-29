package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.hdm.thies.bankProjekt.client.gui.CustomerForm.ChangeClickHandler;
import de.hdm.thies.bankProjekt.client.gui.CustomerForm.DeleteClickHandler;
import de.hdm.thies.bankProjekt.client.gui.CustomerForm.NewClickHandler;
import de.hdm.vocke.myContacts.shared.bo.Contact;

public class ContactForm extends VerticalPanel {
	
	private Navigation navigation=null;
	
	/**
	 * Konstruktor, dem ein Projekt und eine Instanz der navigation übergeben wird 
	 * @param navigation
	 */
	public ContactForm (Navigation navigation) {
		this.navigation=navigation;
	}
	/**
	 * Anlegen der GUI Elemente 
	 */
	
	private VerticalPanel vpanel = new VerticalPanel();
	
	public void onLoad() {
		super.onLoad();
		
		Grid contactGrid = new Grid(9, 2);
		this.add(cotactGrid);


		deleteButton.addClickHandler(new DeleteClickHandler());
		deleteButton.setEnabled(false);
		customerGrid.setWidget(0, 1, deleteButton);
		
		newButton.addClickHandler(new NewClickHandler());
		newButton.setEnabled(false);
		customerGrid.setWidget(1, 1, newButton);

		Label firstNameLabel = new Label("Vorname");
		customerGrid.setWidget(2, 0, firstNameLabel);
		customerGrid.setWidget(2, 1, firstNameTextBox);

		Label lastNameLabel = new Label("Nachname");
		customerGrid.setWidget(3, 0, lastNameLabel);
		customerGrid.setWidget(3, 1, lastNameTextBox);

		HorizontalPanel customerButtonsPanel = new HorizontalPanel();
		customerGrid.setWidget(4, 1, customerButtonsPanel);

		Button changeButton = new Button("Name ändern");
		changeButton.addClickHandler(new ChangeClickHandler());
		customerButtonsPanel.add(changeButton);
	  
}
