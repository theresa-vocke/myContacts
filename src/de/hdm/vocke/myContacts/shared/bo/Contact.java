package de.hdm.vocke.myContacts.shared.bo;

import java.util.Date;

public class Contact extends BusinessObject {
	
	private static final long serialVersionUID = 1L;
	
	private int contactId = 0;
	
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	/**
	 * Vorname des Kontakts
	 */
	private String firstName = " ";
	
	/**
	 * Nachname des Kontaktes 
	 */
	private String lastName = " ";
	
	/**
	 * Geburtstag des Kontaktes
	 */
	private Date birthdate = 0;

	/**
	 * Stadt des Kontaktes
	 */
	private String city = " ";
	/**
	 * Straﬂe des Kontaktes 
	 */
	private String street = " ";
	/**
	 * Hausnummer des Kontaktes 
	 */
	private int number = 0; 
	/**
	 * PLZ des Kontaktes 
	 */
	private int zip = 0;
	
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
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}

}
