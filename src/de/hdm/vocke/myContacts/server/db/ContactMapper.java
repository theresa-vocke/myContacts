package de.hdm.vocke.myContacts.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import server.db.Contact;
import server.db.ContactMapper;
import server.db.Contacts;
import server.db.DBConnection;
import server.db.RestultSet;
import server.db.SQLExecption;
import server.db.SQLExeption;
import server.db.Vector;
import server.db.Vektor;
import server.db.contact;
import server.db.contactMapper;

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
	
	private static ContactMapper contactMapper(){
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
				stmt.executeUpdate("INSERT INTO contacts (id, firstname, lastname) " + "VALUES" 
				 c.getId() + "," + c.getLastname() + "," c.getFirstname ()+ ")" );
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
			
			stmt.executeUpdate("UPDATE contacts " + " SET lastname =\"" c.getLastnameID() + "\" "
					+ "WHERE id=" c.getID());
		}
		catch (SQLExecption e2){
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
			
			stmt.executeUpdate("DELETE FROM contacts " + "WHERE id=" c.getId());
		}
		
		catch (SQLExeption e2){
			e2.printStackTrace();			
		}
	}
	
	/**
	 * Auslesen aller Kontakte
	 * @return Vektor mit allen Contact-Objekten, die einen Kontakt repräsentieren 
	 */
	
	public Vektor<Contacts> findAll(){
		// Ergebnisvektor vorbereiten 
		Vektor<Contacts> result = new Vektor<Contacts>();
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			RestultSet rs = stmt.executeQuery("SELECT id, firstname, lastname " + "FROM contacts" + "ORDER BY lastname");
			
			// für jeden Eintrag im Suchergebnis wird nun ein Contact Objekt erstellt
			while (rs.next()){
				contact c = new Contact();
				c.setId(rs.getInt("id"));
				c.setLastname(rs.getString("lastname"));
				c.setFirstname(rs.getString("firstname"));
				
				// Hinzufügen eines neuen Objektes zum Ergebnisvektor
				result.addElement(c);
			}
		}
		
		catch (SQLExeption e2){
			e2.printStackTrace();
		}
		
		return result; 
	}
	
	/**
	 * Auslesen aller Kontakt-Objekten mit gegebenem Nachname
	 * @param name
	 * @return Vektor mit allen Contact-Objekten mit gesuchtem Nachname
	 */
	
	public Vector<Contacts> findByLastname(String name){
			Connection con = DBConnection.connection();
			Vector<Contacts> result = new Vector<Contacts>();
			
			try{
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT id, lastname, firstname " + "FROM contacts" 
				+ "WHERE lastname LIKE '" + name + "ORDER BY lastname");
				// für jeden Eintrag im Suchergebnis wird jetzt ein Contact-Objekt erzeugt
				while (rs.next()){
					Contact c = new Contact();
					c.setId(rs.getInt("id"));
					c.setFirstname(rs.getString("firstname"));
					c.setLastname(rs.getString("lastname"));
					
					//hinzufügen des neuen Objektes zum Ergebnisvektor
					result.addElement(c);
				}
			}
			catch (SQLExeption e2){
				e2.printStackTrace();
			}
			return result;

			
	}
	
	
}
