package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Observable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjetModel extends Observable{
	Joueur joueur;
	Questions quest;
	
	public ProjetModel() {
		joueur = new Joueur();
		try {
			quest = new Questions();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void connecter(String pseudo) {
		joueur.connecter(pseudo);
		
	}
	public boolean verifIdentifier(String pseudo) {
		if(joueur.verifIdentifier(pseudo)) return true; 
		return false;
	}
	public boolean verifConnecter(String pseudo, String prenom) {
		if(joueur.verifConnecter(pseudo, prenom)) return true; 
		return false;
	}
	
	public void enregistrer(String identifiant, String prenom) {
		joueur.enregistrer(identifiant, prenom);
		
	}
	
	public String toString(int i) {
		if(i==1)
		return ("Identifiant: " + joueur.getIdentifiant() + " Prénom: " + joueur.getPrenom() 
				+ " Niveau Math: " + joueur.getNivMath() + " Niveau Elec: " + joueur.getNivElec()
				+ " Niveau Info: " + joueur.getNivInfo() + " Points: " + joueur.getPoint());
		return ("Question : " + quest.getQuestion() + "\n1)" + quest.getRep1() + "\n2)" 
				+ quest.getRep2() + "\n3)" + quest.getRep3() + "\n4)" + quest.getRep4());
	}
	
	public void questionSuivante(int i) {
		quest.questionSuivante(i);
	}
	
	public void choixQuestion(String sujet, int niveau) throws ClassNotFoundException, SQLException {
		quest.choixQuestion(sujet, niveau);
	}
	
	public boolean comparaison(String choix) {
		if(quest.comparaison(choix))return true;
		return false;
	}
	
	public void proposerQuestion(String q, String r1, String r2, String r3, String r4) {
		joueur.proposerQuestion(q, r1, r2, r3, r4);
	}
}
