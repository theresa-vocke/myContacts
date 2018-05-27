package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.thies.bankProjekt.shared.bo.Customer;
import de.hdm.vocke.myContacts.shared.bo.Contact;

/**
 * Klasse zur Darstellung von Contact-Objekten.
 * Solche Erweiterungen von <code>AbstractCell<T></code> dienen zur Erzeugung von
 * HTML-Code für benutzerdefinierte Objekte. In diesem Fall werden die Werte der Attribute
 * <code>lastName</code> und <code>firstName</code> eines Kundenobjekts mit einem Komma
 * und einer Leerstelle in einem <code>div-</code>Element
 * erzeugt.
 * 
 * @author rathke
 *
 */
public class ContactCell extends AbstractCell<Contact> {

	@Override
	public void render(Context context, Contact value, SafeHtmlBuilder sb) {
		if (value == null) {
	        return;
	      }

	      sb.appendHtmlConstant("<div>");
	      sb.appendEscaped(value.getLastName());
	      sb.appendHtmlConstant(", ");
	      sb.appendEscaped(value.getFirstName());
	      sb.appendHtmlConstant(", ");
	      sb.appendEscaped(value.getPhonenumber());
	      sb.appendHtmlConstant(", ");
	      sb.appendEscaped(value.getStreet());
	      sb.appendHtmlConstant(", ");
	      sb.appendEscaped(value.getNumber());
	      sb.appendHtmlConstant(", ");
	      sb.appendEscaped(value.getCity());
	      sb.appendHtmlConstant(", ");
	      sb.appendEscaped(value.getZip());
	      sb.appendHtmlConstant(", ");
	      sb.appendEscaped(value.getBirthdate());
	      sb.appendHtmlConstant("</div>");
	}
	
}
