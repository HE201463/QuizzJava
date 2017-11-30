package controller;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	private String stop = "arret";
	int i=0;
	int points = 0;
	int nombre;
	
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
		if(i<4) {
			i++;
			model.questionSuivante(i);
			vue.affiche();
		}
		else {
			console.affiche("C'est terminé");
			vue.affiche("C'est terminé");
			try {
				points = model.getJoueur().getPoint() + points;
				model.getQuest().changerPoints(model.getJoueur().getIdentifiant(), points);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			} 
			vue.setVisible(false);
			PageSujet(model.getJoueur().getIdentifiant(), model.getJoueur().getPrenom());
		}
		
	}
	
	public boolean niv(String choix, int niveau) {
		if(choix.equals("info")) {
			if (model.getJoueur().getNivInfo() < niveau && model.getJoueur().getPoint() < 200)return true;
			return false;
		}
		if (choix.equals("math")) {
			if (model.getJoueur().getNivMath() < niveau && model.getJoueur().getPoint() < 200) return true;
			return false;
		}
		if (choix.equals("elec")) {
			if (model.getJoueur().getNivElec() < niveau && model.getJoueur().getPoint() < 200) return true;
			return false;
		}
		return false;
	}
	
	public boolean niveau(String choix, int niveau)  {
		if (niveau == 2) {
			nombre = 200;
		}
		else {
			nombre = 400;
		}
		if (niv(choix, niveau)) {
			JOptionPane.showMessageDialog(null, "Pas assez de points.\nIl faut " + nombre + " points", "Erreur", JOptionPane.ERROR_MESSAGE); 
			System.out.println("Pas assez de points. Il faut " + nombre + " points");
			return false;
		}
		return true;
	}
	
	
	public void choixQuestion(String sujet, int niveau) {
		try {
			model.choixQuestion(sujet, niveau);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void proposeQuestion(String question, String r1, String r2, String r3, String r4) {
		model.proposerQuestion(question, r1, r2, r3, r4);
	}
	
	
	public void PageIntro() {
			
		ProjetController ctrlIntro = new ProjetController(model);
		console = new IntroConsole(model, ctrlIntro);
		((IntroConsole)console).affiche();
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
		
		console.affiche();
		
		vue = new VueSujet(model, ctrlSujet);

		ctrlSujet.addview(vue);
		
		vue.setTitle("ProjetQCM");
		vue.setLocation(700, 50); //(horizontal, vertical)
		//vue.setAlwaysOnTop(true);
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
		//vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(450,300);
		vue.setVisible(true);
		vue.getContentPane().add(((VueQuestion)vue).getPanel());
	}

	public String getStop() {
		return stop;
	}

	public void setStop(String stop) {
		this.stop = stop;
	}
	
}
