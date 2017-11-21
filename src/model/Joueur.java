package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Cette classe implémente un joueur qui a un pseudo, un prénom, des points et un level
 * Groupe 12
 * @author Jonathan Goossens 2TL2  --> auteur de cette classe !
 * @author Benoit de Mahieu 2TL2
 */
public class Joueur extends Observable {
	private String pseudo;
	private String prenom;
	private List <Integer> listePoint = new ArrayList<Integer>();
	
	/*private int point;
	private int niveauMath;
	private int niveauInfo;
	private int niveauElec;*/
	
	/**
	 * Constructeur vide pour pouvoir instancier un Joueur pour le modèle MVC
	 */
	public Joueur() {
		
	}
	
	/**
	 * Ce constructeur crée un joueur et l'ajoute dans la base de donnée
	 * @param pseudo unique
	 * @param prenom 
	 */
	public Joueur(String pseudo, String prenom) {
		this.pseudo = pseudo;
		this.prenom = prenom;
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
			Statement st = db.createStatement();
			if(verifConnecter(pseudo, prenom)) {
				ResultSet rs = st.executeQuery("SELECT pseudo, prenom, points, \"niveauMath\", \"niveauInfo\", \"niveauElec\" "
						+ "FROM public.\"Identifiant\""
					    + "WHERE pseudo = \'"+this.pseudo+"\';");
				rs.next();
				listePoint.add(rs.getInt(3));
				listePoint.add(rs.getInt(4));
				listePoint.add(rs.getInt(5));
				listePoint.add(rs.getInt(6));
			} else {
				st.executeQuery("INSERT INTO public.\"Identifiant\"(\r\n" + 
						"	pseudo, prenom)\r\n" + 
						"	VALUES ('"+ this.pseudo +"', '"+ this.prenom +"');");
				ResultSet rs = st.executeQuery("SELECT pseudo, prenom, points, \"niveauMath\", \"niveauInfo\", \"niveauElec\" "
						+ "FROM public.\"Identifiant\""
					    + "WHERE pseudo = \'"+this.pseudo+"\';");
				rs.next();
				listePoint.add(rs.getInt(3));
				listePoint.add(rs.getInt(4));
				listePoint.add(rs.getInt(5));
				listePoint.add(rs.getInt(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
			listePoint.add(0);
			listePoint.add(1);
			listePoint.add(1);
			listePoint.add(1);
		}
	}
	
	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * @param pseudo the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * A la connection, cette méthode va vérifier si le joueur est inscrit dans la base de données et si son pseudo correspond bien à son prénom !
	 * @param pseudo du joueur (UNIQUE en DB)
	 * @param prenom du joueur
	 * @return true si le joueur est dans la db, false si il n'y est pas ou si la combinaison pseudo prénom n'est pass bonne !
	 */
	public boolean verifConnecter(String pseudo, String prenom) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Identifiant\" ");
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
	
	/**
	 * Cette méthode va vérifier si le pseudo de la personne qui veut s'inscrire n'est pas déjà présent dans la base de donnée 
	 * @param pseudo que le joueur veut pour s'inscrire !  (UNIQUE en DB)
	 * @return true si le pseudo est déjà présent, false s'il ne s'y trouve pas !
	 */
	public boolean verifIdentifier(String pseudo) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Identifiant\" ");
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
	
	
	/*public void jouer() {
		
	}*/
	
	/**
	 * Cette méthode permet aux joueurs de proposer une question qui sera introduite dans notre base de donnée
	 * La proposition sera dans une table proposition en attente de l'approbation des administrateurs !
	 * @param q Question qui est proposée !
	 * @param r1 première propostion de réponse qui est la bonne réponse
	 * @param r2 autre proposition de réponse fausse pour le QCM
	 * @param r3 autre proposition de réponse fausse pour le QCM
	 * @param r4 autre proposition de réponse fausse pour le QCM
	 */
	public void proposerQ(String q, String r1, String r2, String r3, String r4) {
		try{
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
			Statement st = db.createStatement();
			st.executeQuery("INSERT INTO public.\"Proposition\"(q, r1, r2, r3, r4) VALUES('" + q +"', '"+r1+"', '"+r2+"', '"+r3+"', '"+r4+"');");
		}
		catch(ClassNotFoundException | SQLException e) {
			
		}
	}
	/**
	 * Représentation textuelle d'un joueur
	 */
	public String toString() {
		return("Pseudo: " + this.pseudo +
				"\nPrénom: " + this.prenom + 
				"\nPoint: " + this.listePoint.get(0) +
				"\nNiveau Math: " + this.listePoint.get(1) +
				"\nNiveau Info: " + this.listePoint.get(2) +
				"\nNiveau Elec: " + this.listePoint.get(3));
	}
}
