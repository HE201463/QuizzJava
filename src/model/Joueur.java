package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Cette classe implémente un joueur qui a une pseudo, un prénom, des points et un level
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 */
public class Joueur {
	private String pseudo;
	private String prenom;
	private int point;
	private int level;
	/**
	 * 
	 */
	public Joueur() {
		pseudo = "";
		prenom = "";
		point = 0;
		level = 1;
	}
	/**
	 * 
	 * @param pseudo
	 * @param prenom
	 */
	public Joueur(String pseudo, String prenom) {
		this.pseudo = pseudo;
		this.prenom = prenom;
		point = 0;
		level = 1;
	}
	/**
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public boolean verifJoueur() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
		Statement st = db.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM public.\"Identifiant\" ");
		while(rs.next()) {
			if(this.pseudo == rs.getString(1) && this.prenom == rs.getString(2)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 */
	public void jouer() {
		
	}
	
	public void proposerQ(String q, String r1, String r2, String r3, String r4) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
		Statement st = db.createStatement();
		st.executeQuery("INSERT INTO public.\"Proposition\"(q, r1, r2, r3, r4) VALUES('" + q +"', '"+r1+"', '"+r2+"', '"+r3+"', '"+r4+"');");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Joueur ben = new Joueur("bendmh", "ben");
		ben.proposerQ("q1", "r1", "r2", "r3", "r4");
	}
}
