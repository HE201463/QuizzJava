package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Connexion {
	
	protected String question;
	protected String rep1;
	protected String rep2;
	protected String rep3;
	protected String rep4;
	
	public Connexion() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		  Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
		  
		  PreparedStatement st = db.prepareStatement("select question, rep1, rep2, rep3, rep4 FROM public.\"Questions\" where type = 'f' ORDER BY RANDOM() LIMIT 1");
		  ResultSet rs = st.executeQuery();
		  while (rs.next()) {
			  question = rs.getString(1);
			  rep1 = rs.getString(2);
			  rep2 = rs.getString(3);
			  rep3 = rs.getString(4);
			  rep4 = rs.getString(5);
		  }
		  rs.close();
		  st.close();
	}
	
}