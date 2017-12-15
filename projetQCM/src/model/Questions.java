package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
/**
 * Cette classe va permettre d'obtenir les questions du QCM. Les questions vont être afficher dans la console et dans la partie GUI également.
 * Groupe 12
 * @author Benoit de Mahieu
 * @author Jonathan Gossens
 *Classe: 2TL2
 *J'utilise aussi le Jar Lombok qui permet de générer les getter et setter sans les écrire
 */
@Getter
@Setter
public class Questions{
	private String question;
	private String bonneReponse;
	private String rep1;
	private String rep2;
	private String rep3;
	private String rep4;
	
	
	private List<String> questions = new ArrayList<String>(); // liste avec les questions
	private List<String> rep = new ArrayList<String>();// liste avec les 12 réponses
	private List<String> reponses = new ArrayList<String>(); // liste avec les 4 réponses qui vont etre mélangées
	
	/**
	 * Ce constructeur se connecte à la DB et créé un tableau de questions(3 pour le moment), un tableau avec toutes les réponses 
	 * et pour finir un tableau permettant de mélanger les réponses. 
	 * @param j Ce paramètre va permettre de choisir entre les 3 questions sélectionnées dans la DB
	 * @throws ClassNotFoundException Cette exception arrive quand il n'y a pas de résultat retourné de la DB.
	 * @throws SQLException Cette exception permet de signaler lorsque la connexion a la DB échoue.
	 */
	public Questions() throws ClassNotFoundException, SQLException {
		
	}
		
	/**
	 * Cette méthode me permet de comparer la réponse choisie par le joueur avec la bonne réponse.
	 * @param rep rep correspond à la réponse renvoyé par le joueur(rep1, rep2, rep3 ou rep4)
	 * @return Retourne vrai si c'est la bonne réponse, faux dans le cas contraire
	 * @throws ClassNotFoundException exception pour la connexion avec la DB
	 * @throws SQLException exception au cas ou la requete ne fonctionne pas
	 */
	
	public void choixQuestion (String sujet, int niveau) throws ClassNotFoundException, SQLException {
		questions.clear();
		rep.clear();
		Class.forName("org.postgresql.Driver");
		Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
		  
		PreparedStatement st = db.prepareStatement("select question, rep1, rep2, rep3, rep4 FROM public.\"Questions\" where type = 'f' and niveau <=" + niveau + "and sujet='" + sujet + "'order by random() fetch first 5 rows only");
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
		  questions.add(rs.getString(1));
		  rep.add(rs.getString(2));
		  rep.add(rs.getString(3));
		  rep.add(rs.getString(4));
		  rep.add(rs.getString(5));
		}
		  rs.close();
		  st.close();
	}
	/**
	 * Cette m�thode va comparer la r�ponse choisie par le joueur avec la bonne r�ponse
	 * @param rep r�ponse choisie par le joueur
	 * @return true si c'est la bonne r�ponse
	 */
	public boolean comparaison(String rep) {
		if(rep.equals(bonneReponse)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Création de la question choisis et du tableau avec les réponses correspondantes.
	 * On a la bonne réponse qui est choisis à cet endroit.
	 * @param j j est le choix de la question allant de 0->4 au final
	 */
	public void questionSuivante(int j) {
		question = questions.get(j);
		reponses.clear();
		reponses.add(rep.get(4*j));
		reponses.add(rep.get(4*j+1));
		reponses.add(rep.get(4*j+2));
		reponses.add(rep.get(4*j+3));
		bonneReponse = rep.get(4*j);
		
		Collections.shuffle(reponses);
		
		rep1 = reponses.get(0);
		rep2 = reponses.get(1);
		rep3 = reponses.get(2);
		rep4 = reponses.get(3);
		
		if (rep1.equals(bonneReponse)) {
			bonneReponse = "rep1";
		}
		else if (rep2.equals(bonneReponse)) {
			bonneReponse = "rep2";
		}
		else if (rep3.equals(bonneReponse)) {
			bonneReponse = "rep3";
		}
		else {
			bonneReponse = "rep4";
		}
	}
	/**
	 * Changer les points du joueur lors de la r�ponse � une question
	 * @param identifiant du joueur sur lequel il faut changer les points
	 * @param points � changer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void changerPoints(String identifiant, int points) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
		String insertTableSQL = "UPDATE public.\"Joueur\" SET points = ? where identifiant = ?";
		PreparedStatement preparedStatement = db.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, points);
		preparedStatement.setString(2, identifiant);
		//execute insert SQL stetement
		preparedStatement.executeUpdate();
	}
	/**
	 * Cette m�thode va permettre de changer le niveau dans un sujet pour le joueur
	 * @param identifiant du joueur qui va changer de niveau
	 * @param sujet dans lequel il faut changer de niveau
	 * @param niveau � mettre
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public void changerNiv(String identifiant, String sujet, int niveau) throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
		String texte = "niv" + sujet;
		//System.out.println("niv"+ sujet);
		String insertTableSQL = "UPDATE public.\"Joueur\" SET " + texte + "= ? where identifiant = ?";
		PreparedStatement preparedStatement = db.prepareStatement(insertTableSQL);
		preparedStatement.setInt(1, niveau);
		preparedStatement.setString(2, identifiant);
		//execute insert SQL stetement
		preparedStatement.executeUpdate();
	}
	/**
	 * Cette m�thode met la premi�re question propos�e dans une lise de String
	 * @return la question propos�e avec ses r�ponse
	 */
	public List<String> showProposition() {
		List<String> test = new ArrayList<String>();
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM public.\"Proposition\" ");
			if(rs.next()) {
				test.add(rs.getString(1));
				test.add(rs.getString(2));
				test.add(rs.getString(3));
				test.add(rs.getString(4));
				test.add(rs.getString(5));
				rs.next();
				rs.close();
				st.close();
				db.close();
				return test;
			}
		} catch(Exception e) {
			
		}
		return test;
	}
	/**
	 * Supprime la question propos�e dans la BDD
	 * @param q a supprim�e
	 * @param r a supprim�e
	 */
	public void deleteProposition(String q, String r) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			st.executeQuery("Delete FROM public.\"Proposition\" WHERE question =\'" + q+"\' AND r1 =\'"+r+"\';");
			st.close();
			db.close();
		} catch(Exception e) {
			
		}
	}
	/**
	 * Ajoute la question propos�e dans la BDD, dans la table des questions
	 * @param q � ajouter
	 * @param r1 Bonne r�ponse
	 * @param r2 Autre R�ponse
	 * @param r3 Autre R�ponse
	 * @param r4 Autre R�ponse
	 * @param sujet de la question
	 * @param niveau de la question dans le sujet
	 */
	public void addProposition(String q, String r1, String r2, String r3, String r4, String sujet, int niveau) {
		try {
			Class.forName("org.postgresql.Driver");
			Connection db = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testDB", "postgres", "postgres");
			Statement st = db.createStatement();
			st.executeQuery("INSERT INTO public.\"Questions\" (question, rep1, rep2, rep3, rep4, sujet, niveau) VALUES(\'"+ q + "\', \'"+ r1 + "\', \'"+ r2 + "\', \'"+ r3 + "\', \'"+ r4 + "\', \'"+ sujet + "\', "+ niveau +");");
			st.close();
			db.close();
		} catch(Exception e) {
			
		}
	}
	
	
	
	
	
	
	//Getter and Setter
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getBonneReponse() {
		return bonneReponse;
	}

	public void setBonneReponse(String bonneReponse) {
		this.bonneReponse = bonneReponse;
	}

	public String getRep1() {
		return rep1;
	}

	public void setRep1(String rep1) {
		this.rep1 = rep1;
	}

	public String getRep2() {
		return rep2;
	}

	public void setRep2(String rep2) {
		this.rep2 = rep2;
	}

	public String getRep3() {
		return rep3;
	}

	public void setRep3(String rep3) {
		this.rep3 = rep3;
	}

	public String getRep4() {
		return rep4;
	}

	public void setRep4(String rep4) {
		this.rep4 = rep4;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public List<String> getRep() {
		return rep;
	}

	public void setRep(List<String> rep) {
		this.rep = rep;
	}

	public List<String> getReponses() {
		return reponses;
	}

	public void setReponses(List<String> reponses) {
		this.reponses = reponses;
	}
	
	
	
}
