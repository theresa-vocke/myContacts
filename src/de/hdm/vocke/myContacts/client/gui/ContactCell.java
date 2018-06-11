package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.vocke.myContacts.shared.bo.Contact;

/**
* Klasse zur Darstellung von Konto-Objekten.
* Solche Erweiterungen von <code>AbstractCell<T></code> dienen zur Erzeugung von
* HTML-Code f�r benutzerdefinierte Objekte. In diesem Fall wird die <code>id</code>
* eines Kontoobjekts mit einem vorangestellten "Kontonnr. " in einem <code>div-</code>Element
* erzeugt.
* 
* @author rathke
*
*/

public class ContactCell extends AbstractCell<Contact>{

	@Override
    public void render(Context context, Contact value, SafeHtmlBuilder sb) {
      // Value can be null, so do a null check..
      if (value == null) {
        return;
      }

      sb.appendHtmlConstant("<div>");
      sb.appendEscaped(value.getLastName());
      sb.appendHtmlConstant(", ");
      sb.appendEscaped(value.getFirstName());
      sb.appendHtmlConstant("</div>");
      sb.appendHtmlConstant(", ");
      //sb.appendEscaped(value.getPhonenumber());
      sb.appendHtmlConstant("</div>");
      sb.appendHtmlConstant(", ");
      sb.appendEscaped(value.getStreet());
      sb.appendHtmlConstant("</div>");
      sb.appendHtmlConstant(", ");
      //sb.appendEscaped(value.getNumber());
      sb.appendHtmlConstant("</div>");
      sb.appendHtmlConstant(", ");
      sb.appendEscaped(value.getCity());
      sb.appendHtmlConstant("</div>");
      sb.appendHtmlConstant(", ");
      //sb.appendEscaped(value.getBirthdate());
      sb.appendHtmlConstant("</div>");
      sb.appendHtmlConstant(", ");
    }
	
}
