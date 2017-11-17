package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import model.DemandeQuestions;



public class VueQuestionConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	public VueQuestionConsole(DemandeQuestions model, ProjetController controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model);
		affiche("Choisis la bonne réponse en tappant 1, 2, 3 ou 4 (tu as 10 secondes)");
	}

	@Override
	public void affiche(String msg) {
		System.out.println(msg);
	}

	private class ReadInput implements Runnable{
		public void run() {
			while(true){
				try{
					String c = sc.next();
					switch(c) {
						case "1" : 
							controller.verification("rep" + 1);
							break;
						case "2" : 
							controller.verification("rep" + 2);
							break;
						case "3" : 
							controller.verification("rep" + 3);
							break;
						case "4" : 
							controller.verification("rep" + 4);
							break;
						default :
							controller.verification(c);
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
				}
			}
		}
	}
}
