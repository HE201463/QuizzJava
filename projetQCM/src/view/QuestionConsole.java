package view;

import java.util.Observable;
import java.util.Observer;

import controller.ProjetController;
import model.ProjetModel;

/**
 * Cette classe affiche les questions en console. Elle va attendre la réponse du joueur et vérifier cette réponse.
 * @author B
 *
 */

public class QuestionConsole extends ProjetVue implements Observer{
	public QuestionConsole(ProjetModel model, ProjetController controller) {
		super(model, controller);
		//model.questionSuivante(0);
		//update(null, null);
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
		/*System.out.println("Question : " + model.getQuest().getQuestion() + "\n1)" + model.getQuest().getRep1()
				+ "\n2)" + model.getQuest().getRep2() + "\n3)" + model.getQuest().getRep3() + "\n4)" 
				+ model.getQuest().getRep4()); */
	}
}
