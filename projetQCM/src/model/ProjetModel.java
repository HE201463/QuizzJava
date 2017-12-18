package model;


import java.sql.SQLException;
import java.util.Observable;

import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe h�rite de Observable, elle est utilis� pour instanci� des joueurs et les questions
 * Elle permet de lier les diff�rents model entre eux
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 */
@Getter
@Setter
public class ProjetModel extends Observable{
	Joueur joueur;
	Questions quest;
	
	/**
	 * Ce constructeur va instancier le Joueur et les Questions
	 */
	public ProjetModel() {
		joueur = new Joueur();
		try {
			quest = new Questions();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Cette m�thode utilise la m�thode connecter de la classe joueur qui pourra donc �tre utilis�e dans le controller
	 * @param identifiant pour se connecter
	 */
	public void connecter(String identifiant) {
		joueur.connecter(identifiant);
	}
	
	/**
	 * Cette m�thode utilise la m�thode verifIdentifier de la classe joueur qui pourra donc �tre utilis�e dans le controller
	 * @param identifiant � v�rifier
	 * @return true si le pseudo est en BDD, false par d�faut
	 */
	public boolean verifIdentifier(String identifiant) {
		if(joueur.verifIdentifier(identifiant)) return true; 
		return false;
	}
	
	/**
	 * Cette m�thode utilise la m�thode verifConnecter de la classe joueur qui pourra donc �tre utilis�e dans le controller
	 * @param identifiant unique du joueur
	 * @param prenom qui permet la v�rification de la combinaison avec le pseudo 
	 * @return true si la combinaison est bonne, false par d�faut
	 */
	public boolean verifConnecter(String identifiant, String prenom) {
		if(joueur.verifConnecter(identifiant, prenom)) return true; 
		return false;
	}
	
	/**
	 * Cette m�thode utilise la m�thode enregistrer de la classe joueur qui pourra donc �tre utilis�e dans le controller
	 * @param identifiant � ajouter dans la BDD
	 * @param prenom qui sera associ� � l'identifiant
	 */
	public void enregistrer(String identifiant, String prenom) {
		joueur.enregistrer(identifiant, prenom);
		
	}
	
	/**
	 * Cette m�thode renvoie une repr�sentation textuelle des classes Joueur et Questions avec un param�tre qui permet de choisir
	 * quelle classe va �tre repr�sent�e !
	 * @param i 
	 * @return la repr�sentation du Joueur si le param�tre est �gal 1, la repr�sentation des Questions sinon
	 */
	public String toString(int i) {
		if(i==1)
		return ("Identifiant: " + joueur.getIdentifiant() + " Pr�nom: " + joueur.getPrenom() 
				+ " Niveau Math: " + joueur.getNivMath() + " Niveau Elec: " + joueur.getNivElec()
				+ " Niveau Info: " + joueur.getNivInfo() + " Points: " + joueur.getPoint());
		return ("Question : " + quest.getQuestion() + "\n1)" + quest.getRep1() + "\n2)" 
				+ quest.getRep2() + "\n3)" + quest.getRep3() + "\n4)" + quest.getRep4());
	}
	
	/**
	 * Cette m�thode utilise la m�thode questionSuivante de la classe Questions qui pourra donc �tre utilis�e dans le controller
	 * @param i
	 */
	public void questionSuivante(int i) {
		quest.questionSuivante(i);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Cette m�thode utilise la m�thode choixQuestion de la classe Questions qui pourra donc �tre utilis�e dans le controller
	 * @param sujet de question que le joueur va choisir
	 * @param niveau de question qui sera pos�e dans le sujet choisi
	 */
	public void choixQuestion(String sujet, int niveau) throws ClassNotFoundException, SQLException {
		quest.choixQuestion(sujet, niveau);
	}
	
	/**
	 * Cette m�thode utilise la m�thode comparaison de la classe Questions qui pourra donc �tre utilis�e dans le controller
	 * @param choix de la r�ponse 
	 * @return true si le bonne r�ponse a �t� choisie, false sinon
	 */
	public boolean comparaison(String choix) {
		if(quest.comparaison(choix))return true;
		return false;
	}
	
	/**
	 * Cette m�thode utilise la m�thode proposerQuestion de la classe joueur qui pourra donc �tre utilis�e dans le controller
	 * @param q La question propos�e par le Joueur
	 * @param r1 La bonne r�ponse � la question
	 * @param r2 Une autre r�ponse
	 * @param r3 Une autre r�ponse
	 * @param r4 Une autre r�ponse
	 */
	public void proposerQuestion(String q, String r1, String r2, String r3, String r4) {
		joueur.proposerQuestion(q, r1, r2, r3, r4);
	}
	
	public void changerPoints(String identifiant, int points) throws ClassNotFoundException, SQLException {
		quest.changerPoints(identifiant, points);
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
