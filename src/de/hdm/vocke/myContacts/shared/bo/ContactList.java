package de.hdm.vocke.myContacts.shared.bo;

public class ContactList {

	/**
	 * Name der Kontaktliste
	 */
	private String name = " ";

	/**
	 * Größe der Kontaktliste
	 */
	private float size = 100;
	

	private int contactListId = 0;
	
	
	public float getSize() {
		return size;
	}


	public void setSize(float size) {
		this.size = size;
	}
	
	public int getContactListId() {
		return contactListId;
	}


	public void setContactListId(int contactListId) {
		this.contactListId = contactListId;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}
	
	
}
