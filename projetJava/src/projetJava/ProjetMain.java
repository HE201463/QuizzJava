package projetJava;

import java.sql.SQLException;

import controller.ProjetController;
import model.Joueur;
import view.ProjetVue;
import view.VueIntro;
import view.IntroConsole;

public class ProjetMain {

public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Joueur modelJoueur = new Joueur();
				ProjetController ctrlVueIntro = new ProjetController(modelJoueur);
				ProjetVue gui = new VueIntro(modelJoueur, ctrlVueIntro);
				ctrlVueIntro.addview(gui);
				ProjetController ctrlVueIntroConsole = new ProjetController(modelJoueur);
				ProjetVue consoleIntro = new IntroConsole(modelJoueur, ctrlVueIntroConsole);
				ctrlVueIntroConsole.addview(consoleIntro);
			}
		});
	}
	

}
