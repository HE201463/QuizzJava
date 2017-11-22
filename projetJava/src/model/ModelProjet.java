package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Observable;

public class ModelProjet extends Observable{
	Joueur joueur;
	DemandeQuestions question;
	
	public ModelProjet() {
		
	}
	
	public ModelProjet(String pseudo, String prenom, int point, int nivMath, int nivInfo, int nivElec) throws ClassNotFoundException, SQLException {
		joueur = new Joueur(pseudo, prenom, point, nivMath, nivInfo, nivElec);
		question = new DemandeQuestions();
	}
}
