package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

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
public class Joueur extends Observable{
	private String pseudo;
	private String prenom;
	private int point;
	private int nivMath;
	private int nivInfo;
	private int nivElec;
	private Joueur joueur;
	
	/**
	 * Ce constructeur crée un joueur et l'ajoute dans la base de donnée
	 * @param pseudo unique
	 * @param prenom 
	 */
	
	public Joueur() {
		
	}
	
	public Joueur(String pseudo, String prenom, int point, int nivMath, int nivInfo, int nivElec) {
		this.pseudo = pseudo;
		this.prenom = prenom;
		this.point = point;
		this.nivMath = nivMath;
		this.nivInfo = nivInfo;
		this.nivElec = nivElec;
	}
	
	
	public Joueur connecter(String pseudo) {
		this.pseudo = pseudo;
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Joueur\" WHERE identifiant ='" + this.pseudo + "'");
			 while (rs.next()) {
				 joueur = new Joueur(pseudo, rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getInt(5),rs.getInt(6));
				 this.prenom = rs.getString(2);
				 this.point = rs.getInt(3);
				 this.nivMath = rs.getInt(4);
				 this.nivInfo = rs.getInt(5);
				 this.nivElec = rs.getInt(6);
			 }
		} catch (SQLException | ClassNotFoundException e) {
			
		}
		return joueur;
	}
	/*public Joueur(String pseudo, String prenom) {
		this.pseudo = pseudo;
		this.prenom = prenom;
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			st.executeQuery("INSERT INTO public.\"Joueur\"(\r\n" + 
											"	identifiant, prenom)\r\n" + 
											"	VALUES ('"+ this.pseudo +"', '"+ this.prenom +"');");
		} catch (SQLException | ClassNotFoundException e) {
			
		}
	}*/
	
	public void enregistrer(String pseudo, String prenom) {
		this.pseudo = pseudo;
		this.prenom = prenom;
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
	
	
	public boolean verifIdentifier(String pseudo) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Joueur\" ");
			while(rs.next()) {
				if(pseudo.equals(rs.getString(1))) {
					return true;
				}
			} 
		} 
		catch (ClassNotFoundException | SQLException e) {
			
		}
			return false;
	}
	
	
	public boolean verifConnecter(String pseudo, String prenom) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Joueur\" ");
			while(rs.next()) {
				if(pseudo.equals(rs.getString(1))&& prenom.equals(rs.getString(2))) {
					return true;
				}
			} 
		} 
		catch (ClassNotFoundException | SQLException e) {
			
		}
			return false;
	}
	
	public String toString() {
		return ("identifiant: " + this.pseudo + " Prénom : " + this.prenom + 
				" Niveau Math : " + this.nivMath + " Niveau Info : " + this.nivInfo
				+ " Niveau elec : " + this.nivElec + 
				" Points : " + this.point);
	}
}
