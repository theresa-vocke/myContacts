package de.hdm.vocke.myContacts.client;

import com.google.gwt.core.client.GWT;

import de.hdm.vocke.myContacts.shared.CommonSettings;
import de.hdm.vocke.myContacts.shared.MyContactsAsync;

public class ClientsideSettings extends CommonSettings {

	/**
	   * Remote Service Proxy zur Verbindungsaufnahme mit dem Server-seitgen Dienst
	   * namens <code>MyContacts</code>.
	   */
	
	private static MyContactsAsync myContacts = null;
	
	/**
	   * <p>
	   * Anlegen und Auslesen der applikationsweit eindeutigen myContacts-Adiministration. Diese
	   * Methode erstellt myContacts, sofern sie noch nicht existiert. Bei
	   * wiederholtem Aufruf dieser Methode wird stets das bereits zuvor angelegte
	   * Objekt zurückgegeben.
	   * </p>
	   * 
	   * <p>
	   * Der Aufruf dieser Methode erfolgt im Client z.B. durch
	   * <code>myContactsAsync myContacts = ClientSideSettings.getMyContacts()</code>
	   * .
	   * </p>
	   */
	  public static MyContactsAsync getMyContacts() {
	    // Gab es bislang noch keine BankAdministration-Instanz, dann...
	    if (myContacts == null) {
	      // Zunächst instantiieren wir BankAdministration
	      myContacts = GWT.create(MyContactsEntryPoint.class);
	    }

	    // So, nun brauchen wir die BankAdministration nur noch zurückzugeben.
	    return myContacts;
	  }
	
}
