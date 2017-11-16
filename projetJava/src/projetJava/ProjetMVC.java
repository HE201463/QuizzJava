package projetJava;

import java.sql.SQLException;

import controller.ProjetController;
import model.DemandeQuestions;
import view.ProjetVue;
import view.ProjetVueConsole;
import view.VueIntro;

public class ProjetMVC {
	
	public ProjetMVC(int i) throws ClassNotFoundException, SQLException {
		
		DemandeQuestions model = new DemandeQuestions(i);
		
		ProjetController ctrlVueIntro = new ProjetController(model);
		ProjetVue gui = new VueIntro(model, ctrlVueIntro);
		ctrlVueIntro.addview(gui);
		
		ProjetController ctrlConsole = new ProjetController(model);
		ProjetVue console = new ProjetVueConsole(model, ctrlConsole);
		ctrlConsole.addview(console);
		
		//ProjetController ctrlQuest = new ProjetController(model);
		//ProjetVue quest = new ProjetVueGUI(model, ctrlQuest);
		//ctrlQuest.addview(quest);
		
	}
}
