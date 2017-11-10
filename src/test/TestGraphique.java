package test;

import java.awt.Container;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TestGraphique {
	private static String q;
	private static String r1;
	private static String r2;
	private static String r3;
	private static String r4;
	private static JTextField champTexte;
	
		
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		JButton bR1 = new JButton ();
		JButton bR2 = new JButton ();
		JButton bR3 = new JButton ();
		JButton bR4 = new JButton ();
		Class.forName("org.postgresql.Driver");
		  Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "ephec");
		  
		  Statement st = db.createStatement();
		  ResultSet rs = st.executeQuery("SELECT * FROM public.\"Question\" ");
		  while (rs.next()) {
		      q = rs.getString(1);
		      r1 = rs.getString(2);
		      r2 = rs.getString(3);
		      r3 = rs.getString(4);
		      r4 = rs.getString(5);
		      bR1.setText(rs.getString(2));
		      bR2.setText(rs.getString(3));
		      bR3.setText(rs.getString(4));
		      bR4.setText(rs.getString(5));
		      
		  }
		  
		  rs.close();
		  st.close();
		// Création et paramétrisation de la fenêtre
		JFrame f = new JFrame ("Ma première fenêtre");
		 
		f.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		f.setSize (400, 300);
		f.setLocationRelativeTo(null);
		// Création de la boite horizontale
		Box bottom = Box.createHorizontalBox();
		 
		bottom.add (bR1);
		bottom.add (bR2);
		bottom.add (bR3);
		bottom.add (bR4);
		 
		// Création de la zone de texte du haut
		JTextArea textarea = new JTextArea (q);
		textarea.setBounds(130, 50, 100, 20);
		textarea.setEditable (false);
		
		champTexte = new JTextField();
		champTexte.setBounds(200, 50, 100, 20);
		champTexte.setText(q);
		champTexte.setEditable (false);
		 
		// Création de la boite verticale
		Box main = Box.createVerticalBox();
		 
		main.add (champTexte);
		main.add (bottom);
		 
		// On met le tout sur la fenêtre et on l'affiche
		Container contentpane = f.getContentPane();
		contentpane.add (main);
		 
		f.setVisible (true);
	}
	
}
