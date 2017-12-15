package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import model.ProjetModel;

/**
 * Cette classe affiche les sujets en console. Elle va attendre une demande de la part du joueur.
 * @author B
 *
 */
public class SujetConsole extends ProjetVue implements Observer{

	public SujetConsole(ProjetModel model, ProjetController controller) {
		super(model, controller);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model.toString(2));
		affiche("Choisis la bonne r�ponse en tappant 1, 2, 3 ou 4 (tu as 10 secondes)");
	}

	@Override
	public void affiche(String msg) {
		System.out.println(msg);		
	}

	@Override
	public void affiche() {
		affiche(model.toString(1));
		affiche("Choisis un sujet : info, elec, math + 1, 2 ou 3 (un espace entre les deux)");
		affiche("Pour proposer une question: question (un espace entre les deux)");
		if(model.getJoueur().getIdentifiant().equals("deMahieu")||model.getJoueur().getIdentifiant().equals("Goossens")) {
			affiche("Pour voir les questions propos�es: addQuestion (un espace entre les deux)");
		}
	}
}
