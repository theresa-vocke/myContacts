package de.hdm.vocke.myContacts.shared.bo;

public class ContactListContact extends BusinessObject{

private static final long serialVersionUID = 1L;
	
	/**
	 * Fremdschlüsselbeziehung zum Kontakt
	 */
	
	private int contactId;
	
	/**
	 * Fremdschlüsselbeziehung zur Kontaktliste
	 */
	
	private int contactListId;
	

	/**Auslesen des Fremdschlüssels des Kontaktes
	 * @return contactId
	 */
	public int getContactId() {
		return contactId;
	}

	/** Setzen der Fremdschlüsselbeziehung zum Kontakt
	 * @param contactId
	 */
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	/** Auslesen des Fremdschlüssel zur Kontaktliste
	 * @return contactListId
	 */
	public int getContactListId() {
		return contactListId;
	}

	/**Setzen der Fremdschlsselbeziehung zur Kontaktliste
	 * @param contactListId
	 */
	public void setContactListId(int contactListId) {
		this.contactListId = contactListId;
	}
	
}
