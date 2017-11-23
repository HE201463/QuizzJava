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
	DemandeQuestions quest;
	
	public ProjetModel() {
		joueur = new Joueur();
		try {
			quest = new DemandeQuestions();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public void enregistrer(String pseudo, String prenom) {
		joueur.enregistrer(pseudo, prenom);
	}
	
	public String toString(int i) {
		if(i==1)
		return ("Identifiant: " + joueur.getPseudo() + " Prénom: " + joueur.getPrenom() 
				+ " Niveau Math: " + joueur.getNivMath() + " Niveau Elec: " + joueur.getNivElec()
				+ " Niveau Info: " + joueur.getNivInfo() + " Points: " + joueur.getPoint());
		return ("Question : " + quest.getQuestion() + "\n1)" + quest.getRep1() + "\n2)" 
				+ quest.getRep2() + "\n3)" + quest.getRep3() + "\n4)" + quest.getRep4());
	}
	
	public void questionSuivante(int i) {
		quest.questionSuivante(i);
	}
	
	public boolean comparaison(String choix) {
		if(quest.comparaison(choix))return true;
		return false;
	}
	
	
}
