package controller;

import model.DemandeQuestions;
import model.Joueur;
import view.ProjetVue;

public class ProjetController {
	
	DemandeQuestions modelQuestion;
	Joueur modelJoueur;
	ProjetVue vue;
	
	public ProjetController(DemandeQuestions modelQuestion) {
		this.modelQuestion = modelQuestion;
	}
	
	public ProjetController(Joueur modelJoueur) {
		this.modelJoueur = modelJoueur;
	}
	
	public void addview(ProjetVue vue) {
		this.vue = vue;
	}
	
	public void verification(String choix) {
		if(modelQuestion.comparaison(choix)) {
			vue.affiche("Bonne réponse");
		}
		else {
			vue.affiche("Mauvaise réponse");
		}
	}
	
	public boolean verifIdentite(String pseudo) {
		if(modelJoueur.verifIdentifier(pseudo)) {
			vue.affiche("Cette identifiant existe déjà");
			return false;
		}
		else {
			vue.affiche("Identifiant correct");
			return true;
		}
	}
	
	public boolean verifconnecte(String pseudo, String prenom) {
		if(modelJoueur.verifConnecter(pseudo, prenom)) {
			vue.affiche("Ce compte est correct");
			return true;
		}
		else {
			vue.affiche("Identifiant ou prenom incorrect");
			return false;
		}
	}
	
}
