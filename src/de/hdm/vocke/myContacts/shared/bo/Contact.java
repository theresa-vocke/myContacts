package de.hdm.vocke.myContacts.shared.bo;

public class Contact extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int contactListId = 0;
	
	/**
	 * Vorname des Kontakts
	 */
	private String firstName = "";
	
	/**
	 * Nachname des Kontaktes 
	 */
	private String lastName = "";

	
	private String adresse = "";
	

	private String telefonnummer = ""; 
	
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getContactListId() {
		return contactListId;
	}

	public void setContactListId(int contactListId) {
		this.contactListId = contactListId;
	}
	
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public String getTelefonnummer() {
		return telefonnummer;
	}
	public void setTelefonnummer(String telefonnummer) {
		this.telefonnummer = telefonnummer;
	}
}
