package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.vocke.myContacts.shared.bo.Contact;

/**
*
* Darstellung von Kontakt-Objekten 
* für die Objekte wird hier ein HTML Code erzeugt, wir holen uns den Nachnamen und den Vornamen und trennen beide 
* Objekte durch ein Komma voneinadner
* --> das ist die Darstellung im Baum
*
*/

public class ContactCell extends AbstractCell<Contact>{

	@Override
    public void render(Context context, Contact value, SafeHtmlBuilder sb) {
      // Value can be null, so do a null check..
      if (value == null) {
        return;
      }
      // durch das div Element wird ein Bereich erzeugt, in dem ich etwas anzeigen kann 
      sb.appendHtmlConstant("<div>");
      // hier appendEscaped, da für den Datentyp String. nur append für int zb
      sb.appendEscaped(value.getLastName());
      sb.appendHtmlConstant(", ");
      sb.appendEscaped(value.getFirstName());
      sb.appendHtmlConstant("</div>");
    }
	
}
