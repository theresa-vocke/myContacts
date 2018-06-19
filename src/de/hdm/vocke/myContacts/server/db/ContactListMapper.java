package de.hdm.vocke.myContacts.server.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import de.hdm.vocke.myContacts.shared.bo.ContactList;

public class ContactListMapper {

	/**
	 * durch static ist diese Variable nur einmal f�r s�mtliche eventuellen Instanzen dieser Klasse vorhanden
	 * diese Variable speichert die einzige Instanz dieser Klasse
	 */
	
	public static ContactListMapper contactListMapper = null; 
	
	/**
	 *  gesch�tzter Konstruktor, damit nur eine Instanz dieser Klasse erstellt werden kann
	 *  mit new k�nnen dann keine neuen Instanzen erstellt werden 
	 */
	
	protected ContactListMapper(){
		
	}
	
	/**
	   * Diese statische Methode kann aufgerufen werden durch ContactMapper.contactMapper
	   * stellt die Singleton- Eigenschaft sicher, indem sie daf�r sorgt, dass nur eine einzige Instanz
	   * von ContactMapper existiert
	   * 
	   * ContactMapper soltle nich mir new instantiiert werden sondern �ber den Aufruf dieser statischen Methode
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
	   * Einf�gen eines <code>Contact</code>-Objekts in die Datenbank
	   * Der PK des �bergebenen Objektes wird �berpr�ft und wenn notwendig berichtigt  
	   * 
	   * @param c das zu speichernde Objekt
	   * @return das bereits �bergebene Objekt, jedoch mit ggf. korrigierter
	   *         <code>id</code>.
	   */
	
	public ContactList insert (ContactList cl){
		// neue Verbindung mit DB aufnehmen 
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			/**
			 * �berpr�fen, was der momentan h�chste Prim�rschlussel - Wert ist 
			 */
			ResultSet rs = stmt.executeQuery("SELECT MAX(id) AS maxid " + "FROM contactlist ");
			
			// wenn wir etwas zur�ck erhalten, kann dies nur einzeilig sein 
			if (rs.next()){
				/**
				 * c erh�lt den bisher maximalen Prim�rschl�sselwert um 1 erh�ht 
				 */
				cl.setId(rs.getInt("maxid") +1);
				
				stmt = con.createStatement();
				
				/**
				 * jetzt erfolgt das tats�chliche Einf�gen des Contact-Objektes in die DB
				 */
				stmt.executeUpdate("INSERT INTO contactlist (id, titel) " + "VALUES" 
				 + cl.getId() + "," + cl.getName()+ ")" );
			}
		}
		
		catch (SQLException e2){
			e2.printStackTrace();
		}
		return cl;
	}
	
	/**
	   * Wiederholtes Schreiben eines Objekts in die Datenbank.
	   * 
	   * @param c das Objekt, das in die DB geschrieben werden soll
	   * @return das als Parameter �bergebene Objekt
	   */
	public ContactList update (ContactList cl){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("UPDATE contactlist " + " SET titel =\"" + cl.getName() + "\" "
					+ "WHERE ID=" + cl.getId());
		}
		catch (SQLException e2){
			e2.printStackTrace();
		}
		return cl; 
	}
	
	/**
	 * l�schen eines Contact-Objektes aus der DB
	 * @param c das aus der DB zu l�schende Objekt 
	 */
	
	public void delete (ContactList cl){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			stmt.executeUpdate("DELETE FROM contactlist " + "WHERE ID=" + cl.getId());
		}
		
		catch (SQLException e2){
			e2.printStackTrace();			
		}
	}
	
	/**
	 * Auslesen aller Kontakte
	 * @return Vektor mit allen Contact-Objekten, die einen Kontakt repr�sentieren 
	 */
	
	public Vector<ContactList> findAll(){
		// Ergebnisvektor vorbereiten 
		Vector<ContactList> result = new Vector<ContactList>();
		
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT ID, titel FROM contactlist ORDER BY titel");
			
			// f�r jeden Eintrag im Suchergebnis wird nun ein Contact Objekt erstellt
			while (rs.next()){
				ContactList cl = new ContactList();
				cl.setId(rs.getInt("ID"));
				cl.setName(rs.getString("titel"));
				
				// Hinzuf�gen eines neuen Objektes zum Ergebnisvektor
				result.addElement(cl);
			}
		}
		
		catch (SQLException e2){
			e2.printStackTrace();
		}
		
		return result; 
	}
	
	/**
	 * Auslesen aller Kontakt-Objekten mit gegebenem Nachtitel
	 * @param titel
	 * @return Vektor mit allen Contact-Objekten mit gesuchtem Nachtitel
	 */
	
	public Vector<ContactList> findByName(String titel){
			Connection con = DBConnection.connection();
			Vector<ContactList> result = new Vector<ContactList>();
			
			try{
				Statement stmt = con.createStatement();
				
				ResultSet rs = stmt.executeQuery("SELECT ID, titel " + "FROM contactlist" 
				+ "WHERE titel LIKE '" + titel + "ORDER BY titel");
				// f�r jeden Eintrag im Suchergebnis wird jetzt ein Contact-Objekt erzeugt
				while (rs.next()){
					ContactList cl = new ContactList();
					cl.setId(rs.getInt("ID"));
					cl.setName(rs.getString("titel"));
					
					//hinzuf�gen des neuen Objektes zum Ergebnisvektor
					result.addElement(cl);
				}
			}
			catch (SQLException e2){
				e2.printStackTrace();
			}
			return result;
		
	}
	

	public ContactList findContactListById(int id){
		Connection con = DBConnection.connection();
		
		try{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT ID, titel FROM contactlist "
          + "WHERE ID=" + id);
			
			if(rs.next()){
				ContactList cl = new ContactList();
				cl.setId(rs.getInt("ID"));
				cl.setName(rs.getString("titel"));
				return cl;
			}
		}
		catch(SQLException e2){
			e2.printStackTrace();
			return null;
		}
		return null;
		
	}
	
	
	
}
