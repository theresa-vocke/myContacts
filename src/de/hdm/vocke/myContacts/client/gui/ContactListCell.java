package de.hdm.vocke.myContacts.client.gui;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

import de.hdm.vocke.myContacts.shared.bo.ContactList;

/**
* Klasse zur Darstellung von Konto-Objekten.
*
*/

public class ContactListCell extends AbstractCell<ContactList> {

	@Override
	//Methode verstehen -> was passiert hier? 
    public void render(Context context, ContactList value, SafeHtmlBuilder sb) {
      // Value can be null, so do a null check..
      if (value == null) {
        return;
      }

      sb.appendHtmlConstant("<div>");
      sb.appendEscaped(value.getName());
      sb.appendHtmlConstant("</div>");
    }
	
}
