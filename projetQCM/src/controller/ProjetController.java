package controller;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;

import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
import view.IntroConsole;
import view.ProjetVue;
import view.QuestionConsole;
import view.SujetConsole;
import view.VueIntro;
import view.VueQuestion;
import view.VueSujet;

@Getter
@Setter
public class ProjetController {
	
	ProjetModel model;
	ProjetVue vue;
	ProjetVue console;
	int i=0;
	int points = 0;
	
	public ProjetController(ProjetModel model) {
		this.model = model;
	}
	
	public void addview(ProjetVue vue) {
		this.vue = vue;
	}
	
	public void addview2(ProjetVue console) {
		this.console = console;
	}
	
	public void verification(String choix) {
		if(model.comparaison(choix)) {
			console.affiche("Bonne réponse");
			vue.affiche("Bonne réponse");
			points++;
		}
		else {
			console.affiche("Mauvaise réponse");
			vue.affiche("Mauvaise réponse");
		}
	}
	
	public boolean verifIdentite(String identifiant) {
		if(model.verifIdentifier(identifiant)) {
			console.affiche("Cette identifiant existe déjà");
			vue.affiche("Cette identifiant existe déjà");
			return false;
		}
		else {
			console.affiche("Identifiant correct");
			vue.affiche("Identifiant correct");
			return true;
		}
	}
	
	public boolean verifconnecte(String identifiant, String prenom) {
		if(model.verifConnecter(identifiant, prenom)) {
			console.affiche("Ce compte est correct");
			vue.affiche("Ce compte est correct");
			return true;
		}
		else {
			console.affiche("Identifiant ou prenom incorrect");
			vue.affiche("Identifiant ou prenom incorrect");
			return false;
		}
	}
	
	public void questionSuivante() {
		if(i<2) {
			i++;
			model.questionSuivante(i);
			console.affiche();
			console.affiche("Choisis la bonne réponse en tappant 1, 2, 3 ou 4 (tu as 10 secondes)");
			vue.affiche();
		}
		else {
			console.affiche("C'est terminé");
			vue.affiche("C'est terminé");
			try {
				points = model.getJoueur().getPoint() + points;
				model.getQuest().changerPoints(model.getJoueur().getIdentifiant(), points);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			vue.setVisible(false);
			PageSujet(model.getJoueur().getIdentifiant(), model.getJoueur().getPrenom());
		}
		
	}
	
	public void choixQuestion(String sujet, int niveau) {
		try {
			model.choixQuestion(sujet, niveau);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void PageIntro() {
			
		ProjetController ctrlIntro = new ProjetController(model);
		console = new IntroConsole(model, ctrlIntro);
		ctrlIntro.addview2(console);
		vue = new VueIntro(model, ctrlIntro);
		ctrlIntro.addview(vue);
		
		
		vue.setTitle("ProjetQCM");
		vue.setLocation(700, 50); //(horizontal, vertical)
		vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(500,300);
		vue.setVisible(true);
		vue.getContentPane().add(((VueIntro)vue).getIntro());
		
	}
	public void PageSujet(String identifiant, String prenom) {
		vue.setVisible(false);
		model.connecter(identifiant);
		ProjetController ctrlSujet = new ProjetController(model);
		console = new SujetConsole(model, ctrlSujet);
		ctrlSujet.addview2(console);
		
		vue = new VueSujet(model, ctrlSujet);

		ctrlSujet.addview(vue);
		
		vue.setTitle("ProjetQCM");
		vue.setLocation(700, 50); //(horizontal, vertical)
		vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(450,300);
		vue.setVisible(true);
		vue.getContentPane().add(((VueSujet)vue).getSujet());
	}
	
	public void PageQuestions() {
		vue.setVisible(false);
		model.questionSuivante(0);
		ProjetController ctrlQuestion = new ProjetController(model);
		vue = new VueQuestion(model, ctrlQuestion);
		ctrlQuestion.addview(vue);
		console = new QuestionConsole(model, ctrlQuestion);
		ctrlQuestion.addview2(console);
		vue.setTitle("ProjetQCM");
		vue.setLocation(700, 50); //(horizontal, vertical)
		vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(450,300);
		vue.setVisible(true);
		vue.getContentPane().add(((VueQuestion)vue).getPanel());
	}	
}
