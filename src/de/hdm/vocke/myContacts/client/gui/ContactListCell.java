package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.vocke.myContacts.shared.bo.ContactList;

/**
* Klasse zur Darstellung von Konto-Objekten.
* Solche Erweiterungen von <code>AbstractCell<T></code> dienen zur Erzeugung von
* HTML-Code für benutzerdefinierte Objekte. In diesem Fall wird die <code>id</code>
* eines Kontoobjekts mit einem vorangestellten "Kontonnr. " in einem <code>div-</code>Element
* erzeugt.
* 
* @author rathke
*
*/

public class ContactListCell extends AbstractCell<ContactList> {

	@Override
    public void render(Context context, ContactList value, SafeHtmlBuilder sb) {
      // Value can be null, so do a null check..
      if (value == null) {
        return;
      }

      sb.appendHtmlConstant("<div>Kontaktliste ");
      sb.append(value.getId());
      sb.appendHtmlConstant("</div>");
    }
	
}
