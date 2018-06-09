package de.hdm.vocke.myContacts.shared.bo;

import java.util.Vector;

public class ContactList extends BusinessObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Name der Kontaktliste
	 */
	private String name = " ";

	/**
	 * Größe der Kontaktliste
	 */
	private float size = 100;
	
	private int contactId;
	

	
	private Vector<Contact> contactsInList;
	
	
	public float getSize() {
		return size;
	}


	public void setSize(float size) {
		this.size = size;
	}
	
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


	public Vector<Contact> getContactsInList() {
		return contactsInList;
	}


	public void setContactsInList(Vector<Contact> contactsInList) {
		this.contactsInList = contactsInList;
	}
	
	
}
