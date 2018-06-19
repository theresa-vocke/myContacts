package de.hdm.vocke.myContacts.server.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.vocke.myContacts.server.db.DBConnection;
import de.hdm.vocke.myContacts.shared.bo.Contact;

public class ContactMapper {

	/**
	 * durch static ist diese Variable nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden
	 * diese Variable speichert die einzige Instanz dieser Klasse
	 */
	
	public static ContactMapper contactMapper = null; 
	
	/**
	 *  geschützter Konstruktor, damit nur eine Instanz dieser Klasse erstellt werden kann
	 *  mit new können dann keine neuen Instanzen erstellt werden 
	 */
	
	protected ContactMapper(){
		
	}
	
	/**
	   * Diese statische Methode kann aufgerufen werden durch ContactMapper.contactMapper
	   * stellt die Singleton- Eigenschaft sicher, indem sie dafür sorgt, dass nur eine einzige Instanz
	   * von ContactMapper existiert
	   * 
	   * ContactMapper soltle nich mir new instantiiert werden sondern über den Aufruf dieser statischen Methode
	   * 
	   * @return DAS <code>ContactMapper</code>-Objekt.
	   * @see contactMapper
	   */
	
	public static ContactMapper contactMapper(){
		if (contactMapper == null){
			contactMapper = new ContactMapper();
		}
		return contactMapper;
	}

	/**
	   * Einfügen eines <code>Contact</code>-Objekts in die Datenbank
	   * Der PK des übergebenen Objektes wird überprüft und wenn notwendig berichtigt  
	   * 
	   * @param c das zu speichernde Objekt
	   * @return das bereits übergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	
	public Contact insert (Contact c){
		// neue Verbindung mit DB aufnehmen 
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			/**
			 * überprüfen, was der momentan höchste Primärschlussel - Wert ist 
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM contacts ");
			
			// wenn wir etwas zurück erhalten, kann dies nur einzeilig sein 
			if (rs.next()){
				/**
				 * c erhält den bisher maximalen Primärschlüsselwert um 1 erhöht 
				 */
				c.setId(rs.getInt("maxid") +1);
				
				stmt = con.createStatement();
				
				/**
				 * jetzt erfolgt das tatsächliche Einfügen des Contact-Objektes in die DB
				 */
				stmt.executeUpdate("INSERT INTO contacts (ID, firstname, lastname)" 
				 + "VALUES (" 
				 + c.getId() 
				 + "," 
				 + c.getLastName() 
				 + "," 
				 + c.getFirstName()
				 +")" );
			}
		}
		
		catch (SQLException e2){
			e2.printStackTrace();
		}
		return c;
	}
	
	/**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param c das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter übergebene Objekt
	   */
	public Contact update (Contact c){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
						
			stmt.executeUpdate("UPDATE contact " + " SET firstname =\"" 
			+ c.getFirstName() +"\", "
			+ "lastname=\"" 
			+ c.getLastName() + "\", " 
			+ "WHERE ID="
			+ c.getId());
			
		}
		catch (SQLException e2){
			e2.printStackTrace();
		}
		return c; 
	}
	
	/**
	 * löschen eines Contact-Objektes aus der DB
	 * @param c das aus der DB zu löschende Objekt 
	 */
	
	public void delete (Contact c){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM contact " + "WHERE ID=" + c.getId());
		}
		
		catch (SQLException e2){
			e2.printStackTrace();			
		}
	}
	
	/**
	 * Auslesen aller Kontakte
	 * @return Vektor mit allen Contact-Objekten, die einen Kontakt repräsentieren 
	 */
	
//	public Vector<Contact> findAll(){
//		// Ergebnisvektor vorbereiten 
//		Vector<Contact> result = new Vector<Contact>();
//		
//		Connection con = DBConnection.connection();
//		
//		try{
//			Statement stmt = con.createStatement();
//			
//			ResultSet rs = stmt.executeQuery("SELECT id, firstname, lastname, phonenumber, street, number, city, birthdate " 
//			+ "FROM contacts" + "ORDER BY lastname");
//			
//			// für jeden Eintrag im Suchergebnis wird nun ein Contact Objekt erstellt
//			while (rs.next()){
//				Contact c = new Contact();
//				c.setId(rs.getInt("id"));
//				c.setFirstName(rs.getString("firstname"));
//				c.setLastName(rs.getString("lastname"));
//				c.setPhonenumber(rs.getString("phonenumber"));
//				c.setStreet(rs.getString("street"));
//				c.setNumber(rs.getInt("number"));
//				c.setCity(rs.getString("city"));
//				c.setBirthdate(rs.getDate("birthdate"));
//								
//				// Hinzufügen eines neuen Objektes zum Ergebnisvektor
//				result.addElement(c);
//			}
//		}
//		
//		catch (SQLException e2){
//			e2.printStackTrace();
//		}
//		
//		return result; 
//	}
	
	/**
	 * Auslesen aller Kontakt-Objekten mit gegebenem Nachname
	 * @param lastname
	 * @return Vektor mit allen Contact-Objekten mit gesuchtem Nachname
	 */
	
	public Vector<Contact> findByLastname(String lastName){
			Connection con = DBConnection.connection();
			Vector<Contact> result = new Vector<Contact>();
			
			try{
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT ID, firstname, lastname " 
				+ "FROM contact" 
				+ "WHERE lastname LIKE '" + lastName + "ORDER BY lastname");
				// für jeden Eintrag im Suchergebnis wird jetzt ein Contact-Objekt erzeugt
				while (rs.next()){
					Contact c = new Contact();
					c.setId(rs.getInt("ID"));
					c.setFirstName(rs.getString("firstname"));
					c.setLastName(rs.getString("lastname"));
					
					//hinzufügen des neuen Objektes zum Ergebnisvektor
					result.addElement(c);
				}
			}
			catch (SQLException e2){
				e2.printStackTrace();
			}
			return result;

			
	}
	
	/**
	 * Auslesen aller Kontakt-Objekten mit gegebenem Vornamen
	 * @param firstname
	 * @return Vektor mit allen Contact-Objekten mit gesuchtem Vornamen
	 */
	
	public Vector<Contact> findByFirstname(String firstname){
			Connection con = DBConnection.connection();
			Vector<Contact> result = new Vector<Contact>();
			
			try{
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT ID, firstname, lastname " 
				+ "FROM contact" 
				+ "WHERE firstname LIKE '" + firstname + "ORDER BY firstname");
				// für jeden Eintrag im Suchergebnis wird jetzt ein Contact-Objekt erzeugt
				while (rs.next()){
					Contact c = new Contact();
					c.setId(rs.getInt("ID"));
					c.setFirstName(rs.getString("firstname"));
					c.setFirstName(rs.getString("lastname"));
					
					//hinzufügen des neuen Objektes zum Ergebnisvektor
					result.addElement(c);
				}
			}
			catch (SQLException e2){
				e2.printStackTrace();
			}
			return result;
		
	}
	
	public Contact findById (int contactId){
		
		/**
		 * Verbindung zur DB Connection
		 */
		Connection con = DBConnection.connection();

		try {

			PreparedStatement stmt = con.prepareStatement("SELECT ID, fistname, lastname FROM contact WHERE `ID` = ?");

			stmt.setInt(1, contactId);
			ResultSet rs = stmt.executeQuery();

			/**
			 * Für jeden Eintrag im Suchergebnis wird nun ein Kontakt-Objekt
			 * erstellt.
			 */
			if (rs.next()) {
				Contact c = new Contact();
				c.setId(rs.getInt("ID"));
				c.setFirstName(rs.getString("firstname"));
				c.setFirstName(rs.getString("lastname"));
				
				return c;
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	
		public Vector<Contact> findAllContacts(){
			
			/**
			 * Verbindung zur DB Connection
			 */		
			
			Vector<Contact> result = new Vector<Contact>();
			
			Connection con = DBConnection.connection();
			
			try {
				
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT ID, firstname, lastname FROM contact ");
				
				/**
				 * Für jeden Eintrag im Suchergebnis wird nun ein Kontakt-Objekt erstellt.
				 */			
				while(rs.next()) {
					Contact c = new Contact();
					c.setId(rs.getInt("ID"));
					c.setFirstName(rs.getString("firstname"));
					c.setLastName(rs.getString("lastname"));
				
					/**
					 * Hinzufügen des neuen Objektes zum Ergebnisvektor
					 */				
					result.addElement(c);				
				}
			}
			catch(SQLException e2) {
				e2.printStackTrace();
			} 
			
			/**
			 * Ergebnisvektor zurückgeben
			 */		
			return result;
			
		}
		
		
		public Vector<Contact> findAllContactsByContactListId(int contactListId) {
			
			/**
			 * Verbindung zur DB Connection
			 */
			Connection con = DBConnection.connection();
			
			Vector<Contact> result = new Vector<Contact>();

			try {
				PreparedStatement stmt = con.prepareStatement("SELECT `contact`.`ID`, `contact`.`firstname`, `contact`.`lastname`, `contactList`.`ID` "
						+ "FROM `contactlist` INNER JOIN `contactlistcontacts` "
						+ "ON `contactlistcontacts`.`contactListId` = `contactlist`.`id` INNER JOIN `contact` "
						+ "ON `contactlistcontacts`.`contactId` = `contact`.`ID` WHERE `contactList`.`ID` = ?");
				
				stmt.setInt(1, contactListId);
				ResultSet rs = stmt.executeQuery();
				
				/**
				 * Für jeden Eintrag Kontakt ein Kontakt-Objekt erstellt.
				 */
				while(rs.next()) {
					Contact c = new Contact();
					c.setId(rs.getInt("ID"));
					c.setFirstName(rs.getString("firstname"));
					c.setFirstName(rs.getString("lastname"));
					
					/**
					 * Hinzufügen des neuen Objekts zum Ergebnisvektor
					 */
					result.addElement(c);
				}
			}
			catch(SQLException e2) {
				e2.printStackTrace();
			}
			
			/**
			 * Ergebnisvektor zurückgeben
			 */
			return result;
		}
		
		
}
