package view;

import java.util.Observable;
import java.util.Observer;

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
		affiche("Choisis la bonne réponse en tappant 1, 2, 3 ou 4 (tu as 10 secondes)");
	}

	@Override
	public void affiche(String msg) {
		System.out.println(msg);		
	}

	@Override
	public void affiche() {
		System.out.println(model.toString(1));
		System.out.println("Choisis un sujet : info, elec, math + 1, 2 ou 3 (un espace entre les deux)");
		System.out.println("Pour proposer une question: question + 1 (un espace entre les deux)");
	}
}
