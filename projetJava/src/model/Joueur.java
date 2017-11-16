package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe implémente un joueur qui a un pseudo, un prénom, des points et un level
 * Groupe 12
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 */

@Getter
@Setter
public class Joueur {
	private String pseudo;
	private String prenom;
	private int point;
	private int level;
	private int nivMath;
	private int nivInfo;
	private int nivElec;
	
	/**
	 * Ce constructeur crée un joueur et l'ajoute dans la base de donnée
	 * @param pseudo unique
	 * @param prenom 
	 */
	public Joueur(String pseudo, String prenom) {
		this.pseudo = pseudo;
		this.prenom = prenom;
		point = 0;
		level = 1;
		nivInfo = 1;
		nivMath = 1;
		nivElec = 1;
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			st.executeQuery("INSERT INTO public.\"Joueur\"(\r\n" + 
											"	identifiant, prenom)\r\n" + 
											"	VALUES ('"+ this.pseudo +"', '"+ this.prenom +"');");
		} catch (SQLException | ClassNotFoundException e) {
			
		}
}
}
