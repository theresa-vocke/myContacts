package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView {
	
	private VerticalPanel vPanel = new VerticalPanel ();
	private VerticalPanel contentPanel;
	
	public MainView(){
		initWidget(this.vPanel);
		
		this.contentPanel = new Vertical Panel ();
		this.vPanel.add(contentPanel);
		
		
	}

}
