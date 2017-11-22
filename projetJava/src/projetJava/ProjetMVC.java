package projetJava;

import java.sql.SQLException;

import controller.ProjetController;
import model.DemandeQuestions;
import model.Joueur;
import view.ProjetVue;
import view.VueIntro;
import view.IntroConsole;
import view.VueQuestion;
import view.QuestionConsole;

public class ProjetMVC {
	
	/*public ProjetMVC(int j) throws ClassNotFoundException, SQLException {
				
		if (j == 1) {
		ProjetController ctrlVueIntro = new ProjetController(modelJoueur);
		ProjetVue gui = new VueIntro(modelJoueur, ctrlVueIntro);
		ctrlVueIntro.addview(gui);
		ProjetController ctrlVueIntroConsole = new ProjetController(modelJoueur);
		ProjetVue consoleIntro = new VueIntroConsole(modelJoueur, ctrlVueIntroConsole);
		ctrlVueIntroConsole.addview(consoleIntro);
		}
		else if (j==2) {
			ProjetController ctrlQuestion = new ProjetController(modelQuestion);
			ProjetVue question = new VueQuestion(modelQuestion, ctrlQuestion);
			ctrlQuestion.addview(question);
			ProjetController ctrlConsole = new ProjetController(modelQuestion);
			ProjetVue console = new VueQuestionConsole(modelQuestion, ctrlConsole);
			ctrlConsole.addview(console);
		}		
	}*/
}
