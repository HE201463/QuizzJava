package controller;

import lombok.Getter;
import lombok.Setter;
import model.DemandeQuestions;
import model.Joueur;
import view.ProjetVue;
import view.SujetConsole;
import view.VueSujet;
import view.IntroConsole;
@Getter
@Setter
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
	
	public void changerPage(String identifiant, String prenom) {
		Joueur model = modelJoueur.connecter(identifiant);
		ProjetController ctrlSujet = new ProjetController(model);
		ProjetVue sujet = new VueSujet(model, ctrlSujet, identifiant, prenom);
		ctrlSujet.addview(sujet);
		System.out.println(model);
		ProjetVue console = new SujetConsole(model, ctrlSujet);
		ctrlSujet.addview(console);
	}
	
}
