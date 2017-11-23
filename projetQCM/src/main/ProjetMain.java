package main;

import java.sql.SQLException;

import controller.ProjetController;
import model.Joueur;
import model.ProjetModel;
import view.IntroConsole;
import view.ProjetVue;
import view.VueIntro;

public class ProjetMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				
				ProjetModel model = new ProjetModel();
				ProjetController ctrlVueIntro = new ProjetController(model);
				ProjetVue gui = new VueIntro(model, ctrlVueIntro);
				ctrlVueIntro.addview(gui);
				
				ProjetController ctrlVueIntroConsole = new ProjetController(model);
				ProjetVue consoleIntro = new IntroConsole(model, ctrlVueIntroConsole);
				ctrlVueIntroConsole.addview(consoleIntro);
			}
		});
		
		//ProjetModel test = new ProjetModel("deMahieu");
		//System.out.println(test);
	}

}
