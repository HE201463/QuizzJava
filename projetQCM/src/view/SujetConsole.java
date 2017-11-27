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
		affiche(model.toString(1));
		affiche("Choisis un sujet : info, elec, math + 1, 2 ou 3 (un espace entre les deux)");
		affiche("Pour proposer une question: question + 1 (un espace entre les deux)");
	}

	@Override
	public void affiche(String msg) {
		System.out.println(msg);		
	}
	
	
	private class ReadInput implements Runnable{
		public void run() {
			while(arret){
				try{
					String c = sc.next();
					affiche("tata");
					String niv = sc.next();
					int niveau = Integer.parseInt(niv);
					if(niveau < 0 || niveau > 4) {
						affiche("Niveau incorrect");
					}
					else {
						switch(c) {
							case "info" : 
								affiche("choix de info");
								controller.choixQuestion("info", niveau);
								controller.PageQuestions();
								arret = false;
								break;	
							case "elec" : 
								affiche("choix de elec");
								controller.choixQuestion("elec", niveau);
								controller.PageQuestions();
								arret = false;
								break;
							case "math" : 	
								affiche("choix de math");
								controller.choixQuestion("math", niveau);
								controller.PageQuestions();
								arret = false;
								break;
							case "question" :
								affiche("Proposez votre question");
								sc.nextLine();
								String q = sc.nextLine();
								affiche("Tapez maintenant la bonne réponse !");
								String r1 = sc.nextLine();
								affiche("Tapez une autre réponse !");
								String r2 = sc.nextLine();
								affiche("Tapez une autre réponse !");
								String r3 = sc.nextLine();
								affiche("Tapez une autre réponse !");
								String r4 = sc.nextLine();
								affiche("Votre question a bien été envoyée ! Merci de votre participation !\n\n");
								controller.proposeQuestion(q, r1, r2, r3, r4);
								update(null, null);
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


	@Override
	public void affiche() {
		
	}
}
