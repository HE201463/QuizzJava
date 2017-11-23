package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import model.ProjetModel;

public class SujetConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	protected boolean arret = true;
	public SujetConsole(ProjetModel model, ProjetController controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(model.toString(1));
		System.out.println("Choisis un sujet : info, elec, math + 1, 2 ou 3 (un espace entre les deux)");		
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
					System.out.println("tata");
					String niv = sc.next();
					int niveau = Integer.parseInt(niv);
					if(niveau < 0 || niveau > 4) {
						affiche("Niveau incorrect");
					}
					else {
						switch(c) {
							case "info" : 
								affiche("choix de info");
								controller.PageQuestions();
								break;
								
							case "elec" : 
								affiche("choix de elec");
								controller.PageQuestions();
								break;
								
							case "math" : 	
								affiche("choix de math");
								controller.PageQuestions();
								break;
							default : 
								affiche("Il n'y a pas ce sujet");
						}
					}
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
				}
			}
		}
	}
}
