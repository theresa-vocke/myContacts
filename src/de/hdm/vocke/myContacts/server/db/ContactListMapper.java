package de.hdm.vocke.myContacts.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class ContactListMapper {

	/**
	 * durch static ist diese Variable nur einmal für sämtliche eventuellen Instanzen dieser Klasse vorhanden
	 * diese Variable speichert die einzige Instanz dieser Klasse
	 */
	
	public static ContactListMapper contactListMapper = null; 
	
	/**
	 *  geschützter Konstruktor, damit nur eine Instanz dieser Klasse erstellt werden kann
	 *  mit new können dann keine neuen Instanzen erstellt werden 
	 */
	
	protected ContactListMapper(){
		
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
	
	public static ContactListMapper contactListMapper(){
		if (contactListMapper == null){
			contactListMapper = new ContactListMapper();
		}
		return contactListMapper;
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
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM contactlist ");
			
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
				stmt.executeUpdate("INSERT INTO contactlist (id, name) " + "VALUES" 
				 c.getId() + "," + c.getName ()+ ")" );
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
	public ContactList update (ContactList cl){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE contactList " + " SET name =\"" c.getNameID() + "\" "
					+ "WHERE id=" c.getID());
		}
		catch (SQLExcecption e2){
			e2.printStackTrace();
		}
		return cl; 
	}
	
	/**
	 * löschen eines Contact-Objektes aus der DB
	 * @param c das aus der DB zu löschende Objekt 
	 */
	
	public void delete (ContactList cl){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM contactList " + "WHERE id=" c.getId());
		}
		
		catch (SQLException e2){
			e2.printStackTrace();			
		}
	}
	
	/**
	 * Auslesen aller Kontakte
	 * @return Vektor mit allen Contact-Objekten, die einen Kontakt repräsentieren 
	 */
	
	public Vector<ContactList> findAll(){
		// Ergebnisvektor vorbereiten 
		Vector<ContactList> result = new Vector<ContactList>();
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT id, name " + "FROM contactList" + "ORDER BY name");
			
			// für jeden Eintrag im Suchergebnis wird nun ein Contact Objekt erstellt
			while (rs.next()){
				ContactList cl = new ContactList();
				cl.setId(rs.getInt("id"));
				cl.setName(rs.getString("name"));
				
				// Hinzufügen eines neuen Objektes zum Ergebnisvektor
				result.addElement(cl);
			}
		}
		
		catch (SQLException e2){
			e2.printStackTrace();
		}
		
		return result; 
	}
	
	/**
	 * Auslesen aller Kontakt-Objekten mit gegebenem Nachname
	 * @param name
	 * @return Vektor mit allen Contact-Objekten mit gesuchtem Nachname
	 */
	
	public Vector<ContactList> findByName(String name){
			Connection con = DBConnection.connection();
			Vector<ContactList> result = new Vector<ContactList>();
			
			try{
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT id, name " + "FROM contactList" 
				+ "WHERE name LIKE '" + name + "ORDER BY name");
				// für jeden Eintrag im Suchergebnis wird jetzt ein Contact-Objekt erzeugt
				while (rs.next()){
					ContactList cl = new ContactList();
					cl.setId(rs.getInt("id"));
					cl.setName(rs.getString("name"));
					
					//hinzufügen des neuen Objektes zum Ergebnisvektor
					result.addElement(cl);
				}
			}
			catch (SQLExeption e2){
				e2.printStackTrace();
			}
			return result;

			
	}
	
}
