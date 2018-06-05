package de.hdm.vocke.myContacts.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.hdm.vocke.myContacts.client.gui.MainView;



/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MyContacts implements EntryPoint {
	
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		//Instanz von MainView 
		MainView mainView = new MainView();
		RootPanel.get().add(mainView);
	    
	    
	    
		
	}
}
