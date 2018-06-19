package de.hdm.vocke.myContacts.shared.bo;

public class ContactList extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name der Kontaktliste
	 */
	private String name;

	/**
	 * Größe der Kontaktliste
	 */
	
	private int contactId;
	

		
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

	
}
