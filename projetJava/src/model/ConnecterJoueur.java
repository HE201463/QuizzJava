package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnecterJoueur extends Joueur{
		Joueur joueur;
	public ConnecterJoueur(String pseudo) {
		
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Joueur\" WHERE identifiant ='" + pseudo + "'");
			 while (rs.next()) {
				 joueur = new Joueur(pseudo, rs.getString(2), rs.getInt(3), rs.getInt(4),rs.getInt(5),rs.getInt(6));
				 System.out.println(joueur);
			 }
			 rs.close();
			 st.close();
		} catch (SQLException | ClassNotFoundException e) {
			
		}
	}
}
