package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import model.ProjetModel;

/**
 * Cette classe affiche les questions en console. Elle va attendre la réponse du joueur et vérifier cette réponse.
 * @author B
 *
 */

public class QuestionConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	protected boolean arreter = true;
	private int i;
	public QuestionConsole(ProjetModel model, ProjetController controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
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

	/**
	 * Cette classe est utilisé par le thread;
	 * Elle va scanner ce que le joueur a entré comme commande et appeler la méthode repondre pour vérifier l'exactitude.
	 * @author B
	 *
	 */
	private class ReadInput implements Runnable{
		public void run() {
			while(arreter){
				try{
					String c = sc.next();
					repondre(c);
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
				}
			}
		}
	}

	/**
	 * Cette méthode va construire la réponse du joueur (rep + un numéro).
	 * Ceci va me permettre de comparer plus facilement avec la bonne réponse dans le modèle.
	 * Elle lancer ensuite la question suivante après la vérification.
	 * @param rep C'est la réponse que le joueur a rentré (1, 2, 3 ou 4).
	 */
	public void repondre(String rep) {
		i++;
		if(i == 5) {arreter = false;}
		controller.verification("rep" + rep);
		try {
			controller.questionSuivante();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	
	@Override
	public void affiche() {
		
	}
}
