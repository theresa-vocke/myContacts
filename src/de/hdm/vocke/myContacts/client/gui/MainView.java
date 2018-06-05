package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView {
	
	private VerticalPanel vPanel = new VerticalPanel ();
	private VerticalPanel contentPanel;
	
	public MainView(){
		initWidget(this.vPanel);
		
		ContactForm Kontaktformular = new ContactForm();
		this.vPanel.add(Kontaktformular);
		
		this.contentPanel = new VerticalPanel();
		this.vPanel.add(contentPanel);
		
		
	}

	private void initWidget(VerticalPanel vPanel2) {
		// TODO Auto-generated method stub
		
	}

}
