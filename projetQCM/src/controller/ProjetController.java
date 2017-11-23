package controller;

import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
import view.ProjetVue;
import view.QuestionConsole;
import view.SujetConsole;
import view.VueQuestion;
import view.VueSujet;


public class ProjetController {
	
	ProjetModel model;
	ProjetVue vue;
	
	public ProjetController(ProjetModel model) {
		this.model = model;
	}
	public void addview(ProjetVue vue) {
		this.vue = vue;
	}
	
	public void verification(String choix) {
		if(model.comparaison(choix)) {
			vue.affiche("Bonne réponse");
		}
		else {
			vue.affiche("Mauvaise réponse");
		}
	}
	
	public boolean verifIdentite(String pseudo) {
		if(model.verifIdentifier(pseudo)) {
			vue.affiche("Cette identifiant existe déjà");
			return false;
		}
		else {
			vue.affiche("Identifiant correct");
			return true;
		}
	}
	
	public boolean verifconnecte(String pseudo, String prenom) {
		if(model.verifConnecter(pseudo, prenom)) {
			vue.affiche("Ce compte est correct");
			return true;
		}
		else {
			vue.affiche("Identifiant ou prenom incorrect");
			return false;
		}
	}
	
	public void PageSujet(String identifiant, String prenom) {
		model.connecter(identifiant);
		ProjetController ctrlSujet = new ProjetController(model);
		ProjetVue sujet = new VueSujet(model, ctrlSujet);
		ctrlSujet.addview(sujet);
		ProjetVue console = new SujetConsole(model, ctrlSujet);
		ctrlSujet.addview(console);
	}
	
	public void PageQuestions() {
		model.questionSuivante(0);
		ProjetController ctrlQuestion = new ProjetController(model);
		ProjetVue question = new VueQuestion(model, ctrlQuestion);
		ctrlQuestion.addview(question);
		ProjetController ctrlConsole = new ProjetController(model);
		ProjetVue console = new QuestionConsole(model, ctrlConsole);
		ctrlConsole.addview(console);
	}
	
}
