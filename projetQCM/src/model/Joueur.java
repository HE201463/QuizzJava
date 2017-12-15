package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import lombok.Getter;
import lombok.Setter;


/**
 * Cette classe implémente un joueur qui a un identifiant, un prénom, des points et un level
 * Groupe 12
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 */

@Getter
@Setter
public class Joueur {
	private String identifiant;
	private String prenom;
	private int point;
	private int nivMath;
	private int nivInfo;
	private int nivElec;
	
	/**
	 * Constructeur vide pour pouvoir instancier un Joueur et donc pouvoir accéder aux méthodes de la classe
	 */
	public Joueur() {
		
	}
	
	/**
	 * Cette méthode va permettre au joueur de se connecter si son identifiant est bien dans la BDD, il pourra donc commencer à jouer et à gagner des points
	 * @param identifiant unique qui permet de retrouver les autres informations du joueur
	 */
	public void connecter(String identifiant) {
		this.identifiant = identifiant;
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Joueur\" WHERE identifiant ='" + identifiant + "'");
			 while (rs.next()) {
				 this.prenom = rs.getString(2);
				 this.point = rs.getInt(3);
				 this.nivMath = rs.getInt(4);
				 this.nivInfo = rs.getInt(5);
				 this.nivElec = rs.getInt(6);
			 }
		} catch (SQLException | ClassNotFoundException e) {
			
		}
	}
	
	/**
	 * Cette méthode va permettre au joueur de s'inscrire dans la BDD et donc pouvoir commencer à gagner des points
	 * @param identifiant unique qui permettra au joueur de se connecter
	 * @param prenom qui permet la vérification de la combinaison identifiant/pseudo en BDD
	 */
	public void enregistrer(String identifiant, String prenom) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			st.executeQuery("INSERT INTO public.\"Joueur\"(\r\n" + 
											"	identifiant, prenom)\r\n" + 
											"	VALUES ('"+ identifiant +"', '"+ prenom +"');");
		} catch (SQLException | ClassNotFoundException e) {
			
		}
	}
	
	/**
	 * Cette méthode va vérifier si l'identifiant que le joueur veut utiliser pour s'inscrire n'existe pas déjà dans la BDD
	 * @param identifiant que le joueur veut utiliser
	 * @return true s'il existe déjà en BDD, false par défaut
	 */
	public boolean verifIdentifier(String identifiant) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Joueur\" ");
			while(rs.next()) {
				if(identifiant.equals(rs.getString(1))) {
					return true;
				}
			} 
		} 
		catch (ClassNotFoundException | SQLException e) {
			
		}
			return false;
	}
	
	/**
	 * Cette méthode va vérifier si la combinaison entre l'identifiant et le prenom est la bonne, si elle existe en BDD
	 * @param identifiant unique qui permet l'identification d'un joueur
	 * @param prenom qui permet la vérification dans la BDD
	 * @return true si la combinaison est bonne et qu'elle est en BDD, false par défaut
	 */
	public boolean verifConnecter(String identifiant, String prenom) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Joueur\" ");
			while(rs.next()) {
				if(identifiant.equals(rs.getString(1))&& prenom.equals(rs.getString(2))) {
					return true;
				}
			} 
		} 
		catch (ClassNotFoundException | SQLException e) {
			
		}
			return false;
	}
	
	/**
	 * Cette méthode va permettre au joueur de participer à l'amélioration du jeu en ayant la possibilité de proposer une question !
	 * Cette question sera ajoutée dans une autre table jusqu'à l'approbation des administrateurs (Vérifier si la question est pertinente).
	 * @param q Question que le joueur propose
	 * @param r1 La bonne réponse à la question 
	 * @param r2 Une autre réponse 
	 * @param r3 Une autre réponse
	 * @param r4 Une autre réponse
	 */
	public void proposerQuestion(String q, String r1, String r2, String r3, String r4) {
		try{
			String script = "INSERT INTO public.\"Proposition\" VALUES('" + q +"', '"+r1+"', '"+r2+"', '"+r3+"', '"+r4+"');";
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			st.executeQuery(script);
			 st.close();
			 db.close();
		}
		catch(ClassNotFoundException | SQLException e) {
			
		}
	}
	
	
	
	
	
	
	
	//Getter and Setter
	public String getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getNivMath() {
		return nivMath;
	}

	public void setNivMath(int nivMath) {
		this.nivMath = nivMath;
	}

	public int getNivInfo() {
		return nivInfo;
	}

	public void setNivInfo(int nivInfo) {
		this.nivInfo = nivInfo;
	}

	public int getNivElec() {
		return nivElec;
	}

	public void setNivElec(int nivElec) {
		this.nivElec = nivElec;
	}
	
	
	
	
}
