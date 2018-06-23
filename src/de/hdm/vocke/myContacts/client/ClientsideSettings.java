package de.hdm.vocke.myContacts.client;

import com.google.gwt.core.client.GWT;

import de.hdm.vocke.myContacts.shared.CommonSettings;
import de.hdm.vocke.myContacts.shared.MyContacts;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;

public class ClientsideSettings extends CommonSettings {

	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>MyContacts</code>.
	   */
	// Instanz von MyContactsAsync anlegen 
	private static MyContactsAsync myContacts = null;
	
	/**
	   * <p>
	   * Anlegen und Auslesen der applikationsweit eindeutigen myContacts-Adiministration. Diese
	   * Methode erstellt myContacts, sofern sie noch nicht existiert. Bei
	   * wiederholtem Aufruf dieser Methode wird stets das bereits zuvor angelegte
	   * Objekt zur�ckgegeben.
	   * </p>
	   * 
	   * <p>
	   * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	   * <code>myContactsAsync myContacts = ClientSideSettings.getMyContacts()</code>
	   * .
	   * </p>
	   */
	// Async ist auf der Clientseite
	
	  public static MyContactsAsync getMyContacts() {
	    // Gab es bislang noch keine myContact-Instanz, dann...
	    if (myContacts == null) {
	      // Zunächst instantiieren wir myContacts 
	    	// GWT.create(MyContacts.class) --> Proxy-Objket wird erstellt 
	    	//myContacts dient dann dazu, dass die Verbingund zwischen Client und Server hergestellt wrid. 
	      myContacts = GWT.create(MyContacts.class);
	    }

	    // So, nun brauchen wir myContacts nur noch zur�ckzugeben.
	    return myContacts;
	  }
	
}
