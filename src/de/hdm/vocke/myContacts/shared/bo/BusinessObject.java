package de.hdm.vocke.myContacts.shared.bo;

import java.io.Serializable;

public abstract class BusinessObject implements Serializable {
	
	private static final long serialVersionUID = 1L; 
	
	/**
	 * eindeutige Identifikationsnummer einer Instanz dieser Klasse
	 */
	private int id = 0;

	/**
	 * auslesen der id 
	 */
	
	public int getId() {
		return id;
	}

	/**
	 * setzen der id 
	 */
	
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * erzeugen einer einfachen textuellen Darstellung der jeweiligen Instanz
	 * Dies kann in den Subklassen �berschrieben werden
	 */
	public String toString(){
		/**
		 * zur�ckgegeben wird der Klassenname gefolgt von der ID
		 */
		return this.getClass().getName() + " #" + this.id;
	}
	
	/**
	 * <p>
	 * Feststellen der <em>inhaltlichen</em> Gleichheit zweier
	 * <code>BusinessObject</code>-Objekte. Die Gleichheit wird in diesem Beispiel auf eine
	 * identische ID beschr�nkt.
   	 * </p>
     * <p>
   	 *
   	 * Die Methode <code>equals(...)</code> ist f�r jeden Referenzdatentyp
   	 * definiert, da sie bereits in der Klasse <code>Object</code> in einfachster
   	 * Form realisiert ist. Dort ist sie allerdings auf die simple Bestimmung der
   	 * Gleicheit der Java-internen Objekt-ID der verglichenen Objekte beschr�nkt.
   	 * In unseren eigenen Klassen k�nnen wir diese Methode �berschreiben und ihr
   	 * mehr Intelligenz verleihen.
   	 * </p>
   	 */
	public boolean equals(Object o){
		/**
		 * Abfragen, ob ein Objekt ungleich NULL ist und gecastet werden kann
		 */
		if (o != null && o instanceof BusinessObject){
			BusinessObject bo = (BusinessObject) o;
			
			try {
				if (bo.getId() == this.id)
					return true;
			}
			catch (IllegalArgumentException e){
				// wenn was schief geht wird zur Sicherheit false zur�ckgegeben
				return false;
			}
		}
		// wenn bislang keine Gleichheit festgestellt wurde wird false zur�ckgegeben
		return false;
	}

	/**
	 * erzeugen einer ganzen Zahl, die f�r das BusinessObjekt charakteristisch ist 
	 * Zusammen mit <code>equals</code> sollte diese Methode immer definiert werden. 
	 * Manche Java-Klassen
	 * verwendenden <code>hashCode</code>, um initial ein Objekt (z.B. in einer Hashtable) 
	 * zu identifizieren. Erst danach
	 * w�rde mit <code>equals</code> festgestellt, ob es sich tats�chlich um das 
	 * gesuchte Objekt handelt.
	 	*/
	public int hashCode (){
		return this.id;
	}
	
}
