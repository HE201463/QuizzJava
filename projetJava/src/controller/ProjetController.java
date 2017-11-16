package controller;

import model.DemandeQuestions;
import model.Joueur;
import view.ProjetVue;

public class ProjetController {
	DemandeQuestions model;
	Joueur model1;
	ProjetVue vue;
	
	public ProjetController(DemandeQuestions model) {
		this.model = model;
	}
	
	public ProjetController(Joueur model1) {
		this.model1 = model1;
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
}
