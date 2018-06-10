package de.hdm.vocke.myContacts.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.vocke.myContacts.shared.bo.ContactListContact;

public class ContactListContactMapper {

private static ContactListContactMapper contactListContactMapper = null;
	
	protected ContactListContactMapper(){
		
	};
	
	public static ContactListContactMapper contactListContactmapper(){
		if(contactListContactMapper == null){
			contactListContactMapper = new ContactListContactMapper();
		}
		return contactListContactMapper;
	}
	
	
	public ContactListContact addContactToContactList (ContactListContact contactListContact){
		
		Connection con = DBConnection.connection();
		
		try {
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT MAX(ID) AS maxID " + " FROM contactlistcontacts ");
			
			if(rs.next()){
				
				contactListContact.setId(rs.getInt("maxID") +1);
				
				stmt.executeUpdate("INSERT INTO `contactlistcontacts`(`ID`, `contactId`,"
        		+ " `contactListId`) "
        		+ "VALUES ('"+contactListContact.getId()+"', '"+contactListContact.getContactId()+"', '"
        		+ ""+contactListContact.getContactListId()+"')");
			}
		}
		catch(SQLException e2) {
			e2.printStackTrace();
		}
		finally {	
			if (con!=null) 
				try {
					con.close();
				}
				catch(SQLException e) {
					e.printStackTrace();
				}
			}
		return contactListContact;
		
	}
	
	public void deleteContactListContactByContactId (int contactId){
		
		Connection con = DBConnection.connection();
		
		try{
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM contactlistcontacts " + "WHERE contactId = " + contactId);
			//prüfen, ob contactId als übergabeparameter passt 
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		
	}
	
	public void deleteContactListContactByContactListId (int contactListId){
		
		Connection con = DBConnection.connection();
		
		try{
			
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM contactlistcontacts " + "WHERE contactListId = " + contactListId); 
			
		}catch(SQLException e){
			e.printStackTrace();
			}
		}
	
	// auslesen aller Kontakte einer Kontaktliste 
	
	public Vector<ContactListContact> findContactListContactByContactListId (int contactListId){
		
		Vector <ContactListContact> result = new Vector<ContactListContact>();
		Connection con = DBConnection.connection();
		
		try{
			
		Statement stmt = con.createStatement();
			
		ResultSet rs =	stmt.executeQuery("SELECT * FROM contactlistcontacts WHERE contactListId =" + contactListId);
			
		while (rs.next()){
			ContactListContact clc = new ContactListContact();
			clc.setId(rs.getInt("ID"));
			clc.setContactId(rs.getInt("contactId"));
			clc.setContactListId(rs.getInt("contactListId"));
			
			result.addElement(clc);
		}
			
		}catch (SQLException e){
			e.printStackTrace();
		}
		return result;
		
	}
	
}
