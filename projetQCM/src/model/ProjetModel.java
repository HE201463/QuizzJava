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
	
	
	public void connecter(String identifiant) {
		joueur.connecter(identifiant);
		
	}
	public boolean verifIdentifier(String identifiant) {
		if(joueur.verifIdentifier(identifiant)) return true; 
		return false;
	}
	public boolean verifConnecter(String identifiant, String prenom) {
		if(joueur.verifConnecter(identifiant, prenom)) return true; 
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
		setChanged();
		notifyObservers();
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
	
	
	
	//Getter and Setter
	public Joueur getJoueur() {
		return joueur;
	}


	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}


	public Questions getQuest() {
		return quest;
	}


	public void setQuest(Questions quest) {
		this.quest = quest;
	}	
	
	
	
	
}
