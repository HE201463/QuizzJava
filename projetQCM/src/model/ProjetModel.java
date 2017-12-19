package model;


import java.sql.SQLException;
import java.util.List;
import java.util.Observable;

import lombok.Getter;
import lombok.Setter;

/**
 * Cette classe hérite de Observable, elle est utilisé pour instancié des joueurs et les questions
 * Elle permet de lier les différents model entre eux
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 */
@Getter
@Setter
public class ProjetModel extends Observable{
	private Joueur joueur;
	private Questions quest;
	
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
	 * Cette méthode utilise la méthode connecter de la classe joueur qui pourra donc être utilisée dans le controller
	 * @param identifiant pour se connecter
	 */
	public void connecter(String identifiant) {
		joueur.connecter(identifiant);
	}
	
	/**
	 * Cette méthode utilise la méthode verifIdentifier de la classe joueur qui pourra donc être utilisée dans le controller
	 * @param identifiant à vérifier
	 * @return true si le pseudo est en BDD, false par défaut
	 */
	public boolean verifIdentifier(String identifiant) {
		if(joueur.verifIdentifier(identifiant)) return true; 
		return false;
	}
	
	/**
	 * Cette méthode utilise la méthode verifConnecter de la classe joueur qui pourra donc être utilisée dans le controller
	 * @param identifiant unique du joueur
	 * @param prenom qui permet la vérification de la combinaison avec le pseudo 
	 * @return true si la combinaison est bonne, false par défaut
	 */
	public boolean verifConnecter(String identifiant, String prenom) {
		if(joueur.verifConnecter(identifiant, prenom)) return true; 
		return false;
	}
	
	/**
	 * Cette méthode utilise la méthode enregistrer de la classe joueur qui pourra donc être utilisée dans le controller
	 * @param identifiant à ajouter dans la BDD
	 * @param prenom qui sera associé à l'identifiant
	 */
	public void enregistrer(String identifiant, String prenom) {
		joueur.enregistrer(identifiant, prenom);
		
	}
	
	/**
	 * Cette méthode renvoie une représentation textuelle des classes Joueur et Questions avec un paramètre qui permet de choisir
	 * quelle classe va être représentée !
	 * @param i 
	 * @return la représentation du Joueur si le paramètre est égal 1, la représentation des Questions sinon
	 */
	public String toString(int i) {
		if(i==1)
		return ("Identifiant: " + joueur.getIdentifiant() + " Prénom: " + joueur.getPrenom() 
				+ " Niveau Math: " + joueur.getNivMath() + " Niveau Elec: " + joueur.getNivElec()
				+ " Niveau Info: " + joueur.getNivInfo() + " Points: " + joueur.getPoint());
		return ("Question : " + quest.getQuestion() + "\n1)" + quest.getRep1() + "\n2)" 
				+ quest.getRep2() + "\n3)" + quest.getRep3() + "\n4)" + quest.getRep4());
	}
	
	/**
	 * Cette méthode utilise la méthode questionSuivante de la classe Questions qui pourra donc être utilisée dans le controller
	 * @param i
	 */
	public void questionSuivante(int i) {
		quest.questionSuivante(i);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * Cette méthode utilise la méthode choixQuestion de la classe Questions qui pourra donc être utilisée dans le controller
	 * @param sujet de question que le joueur va choisir
	 * @param niveau de question qui sera posée dans le sujet choisi
	 */
	public void choixQuestion(String sujet, int niveau) throws ClassNotFoundException, SQLException {
		quest.choixQuestion(sujet, niveau);
	}
	
	/**
	 * Cette méthode utilise la méthode comparaison de la classe Questions qui pourra donc être utilisée dans le controller
	 * @param choix de la réponse 
	 * @return true si le bonne réponse a été choisie, false sinon
	 */
	public boolean comparaison(String choix) {
		if(quest.comparaison(choix))return true;
		return false;
	}
	
	/**
	 * Cette méthode utilise la méthode proposerQuestion de la classe joueur qui pourra donc être utilisée dans le controller
	 * @param q La question proposée par le Joueur
	 * @param r1 La bonne réponse à la question
	 * @param r2 Une autre réponse
	 * @param r3 Une autre réponse
	 * @param r4 Une autre réponse
	 */
	public void proposerQuestion(String q, String r1, String r2, String r3, String r4) {
		joueur.proposerQuestion(q, r1, r2, r3, r4);
	}
	/**
	 * Changer les points du joueur lors de la r�ponse � une question
	 * @param identifiant du joueur sur lequel il faut changer les points
	 * @param points � changer
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void changerPoints(String identifiant, int points) throws ClassNotFoundException, SQLException {
		quest.changerPoints(identifiant, points);
	}
	/**
	 * Envoie une question propos�e avec ses r�ponses
	 * @return la question propos�e avec ses r�ponses
	 */
	public List<String> showProposition() {
		return quest.showProposition();
		
	}
	/**
	 * Demande la suppression de la question propos�e 
	 * @param q question � supprim�
	 * @param r r�ponse � supprimer (Pour le where)
	 */
	public void deleteProposition(String q, String r) {
		quest.deleteProposition(q, r);
	}
	/**
	 * Demande l'ajout d la question propos�e � la BDD
	 * @param q question � ajouter
	 * @param r1 Bonne r�ponse
	 * @param r2 Autre r�ponse
	 * @param r3 Autre r�ponse 
	 * @param r4 Autre r�ponse
	 * @param sujet Sujet de la question
	 * @param niveau Niveau de la question
	 */
	public void addProposition(String q, String r1, String r2, String r3, String r4, String sujet, int niveau) {
		quest.addProposition(q, r1, r2, r3, r4, sujet, niveau);
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
