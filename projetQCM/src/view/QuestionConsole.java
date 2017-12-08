package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import model.ProjetModel;



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
	
	public void repondre(String rep) {
		i++;
		if(i == 3) {arreter = false;}
		controller.verification("rep" + rep);
		try {
			controller.questionSuivante();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void affiche() {
		System.out.println("Question : " + model.getQuest().getQuestion() + "\n1)" + model.getQuest().getRep1()
				+ "\n2)" + model.getQuest().getRep2() + "\n3)" + model.getQuest().getRep3() + "\n4)" 
				+ model.getQuest().getRep4());		
	}
}
