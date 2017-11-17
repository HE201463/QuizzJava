package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import model.Joueur;

public class VueIntroConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	public VueIntroConsole(Joueur modelJoueur, ProjetController controller) {
		super(modelJoueur, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		//System.out.println(modelJoueur);
		System.out.println("Bienvenue dans ce jeu ...");
		System.out.println("Tappe E + un identifiant et un prenom pour t'enregistrer");
		System.out.println("Tappe C + ton identifiant et ton prenom pour te connecter");
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
							affiche("Mauvaise réponse");
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
				}
			}
		}
	}
	
}
