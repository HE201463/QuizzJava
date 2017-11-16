package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Cette classe implémente un joueur qui a un pseudo, un prénom, des points et un level
 * Groupe 12
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 */
public class Joueur {
	private String pseudo;
	private String prenom;
	private int point;
	private int level;
	private int niveauMath;
	private int niveauInfo;
	private int niveauElec;
	
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
		niveauInfo = 1;
		niveauMath = 1;
		niveauElec = 1;
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
			Statement st = db.createStatement();
			st.executeQuery("INSERT INTO public.\"Identifiant\"(\r\n" + 
											"	pseudo, prenom)\r\n" + 
											"	VALUES ('"+ pseudo +"', '"+ prenom +"');");
		} catch (SQLException | ClassNotFoundException e) {
			
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
	 * @return the point
	 */
	public int getPoint() {
		return point;
	}
	
	/**
	 * @param point the point to set
	 */
	public void setPoint(int point) {
		this.point = point;
	}
	
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * @return the niveauMath
	 */
	public int getNiveauMath() {
		return niveauMath;
	}

	/**
	 * @param niveauMath the niveauMath to set
	 */
	public void setNiveauMath(int niveauMath) {
		this.niveauMath = niveauMath;
	}

	/**
	 * @return the niveauInfo
	 */
	public int getNiveauInfo() {
		return niveauInfo;
	}

	/**
	 * @param niveauInfo the niveauInfo to set
	 */
	public void setNiveauInfo(int niveauInfo) {
		this.niveauInfo = niveauInfo;
	}

	/**
	 * @return the niveauElec
	 */
	public int getNiveauElec() {
		return niveauElec;
	}

	/**
	 * @param niveauElec the niveauElec to set
	 */
	public void setNiveauElec(int niveauElec) {
		this.niveauElec = niveauElec;
	}

	/**
	 * A la connection, cette méthode va vérifier si le joueur est inscrit dans la base de données
	 * @return 
	 */
	@SuppressWarnings("finally")
	public boolean verifJoueur() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Identifiant\" ");
			while(rs.next()) {
				if(this.pseudo == rs.getString(1) && this.prenom == rs.getString(2)) {
					return true;
				}
			} 
		} 
		catch (ClassNotFoundException | SQLException e) {
			
		}
		finally {
			return false;
		}
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
}