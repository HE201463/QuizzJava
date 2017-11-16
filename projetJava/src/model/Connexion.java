package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Connexion {
	
	protected String question;
	protected List<String> questions = new ArrayList<String>();
	protected List<String> rep = new ArrayList<String>();
	
	public Connexion() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.postgresql.Driver");
		  Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
		  
		  PreparedStatement st = db.prepareStatement("select question, rep1, rep2, rep3, rep4 FROM public.\"Questions\" where type = 'f' and niveau = 1 order by random() fetch first 3 rows only");
		  //PreparedStatement st = db.prepareStatement("select question, rep1, rep2, rep3, rep4 FROM public.\"Questions\" where type = 'f' ORDER BY RANDOM() LIMIT 1");
		  ResultSet rs = st.executeQuery();
		  while (rs.next()) {
			  questions.add(rs.getString(1));
			  rep.add(rs.getString(2));
			  rep.add(rs.getString(3));
			  rep.add(rs.getString(4));
			  rep.add(rs.getString(5));
			  //System.out.println(reponses);
			  //System.out.println(questions);
		  }
		  rs.close();
		  st.close();
	}
	
}