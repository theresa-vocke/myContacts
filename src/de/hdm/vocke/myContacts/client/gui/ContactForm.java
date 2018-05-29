package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

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
	
	  TextBox firstNameBox;
	  TextBox lastNameBox;  
	  TextBox phoneNumberBox;
	  TextBox streetBox;
	  TextBox numberBox;
	  TextBox cityBox;
	  DateBox birthdayBox;
	  
	  Button createButton;
	  Button updateButton;
	  Button addButton; 
	  
	  VerticalPanel navPanel = new VerticalPanel();

	@Override
	public void render(Context context, Contact value, SafeHtmlBuilder sb) {
		// TODO Auto-generated method stub
		
	}
	  
}
