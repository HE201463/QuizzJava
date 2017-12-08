package view;

import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import model.ProjetModel;

public class SujetConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	protected volatile boolean stop = true;
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
			while(stop){
				try{
					String c = sc.next();
					affiche("tata");
					String niv = sc.next();
					int niveau = Integer.parseInt(niv);
					if(niveau < 0 || niveau > 4) {
						affiche("Niveau incorrect");
					}
					if(c.equals("info") || c.equals("math") || c.equals("elec")) {
						choix(c, niveau);
					}
					else if (c.equals("question")) {
						affiche("Proposez votre question");
						sc.nextLine();
						String q = sc.nextLine();
						affiche("Tapez maintenant la bonne r√©ponse !");
						String r1 = sc.nextLine();
						affiche("Tapez une autre r√©ponse !");
						String r2 = sc.nextLine();
						affiche("Tapez une autre r√©ponse !");
						String r3 = sc.nextLine();
						affiche("Tapez une autre r√©ponse !");
						String r4 = sc.nextLine();
						affiche("Votre question a bien √©t√© envoy√©e ! Merci de votre participation !\n\n");
						controller.proposeQuestion(q, r1, r2, r3, r4);
						affiche();
						
				} else {
					affiche("Il n'y a pas ce sujet !");
				}
			}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
				}
			}
		}
	}
	
	/**
	 * Cette mÈthode va dÈpendre du choix et du niveau que le joueur souhaite.
	 * Selon son choix la mÈthode va lancer la mÈthode niveau, choixQuestion et pageQuestion.
	 * @param choix
	 * @param niveau
	 */
	public void choix(String choix, int niveau) {
		if (niveau == 2) {
			if(controller.niveau(choix, 2)) {
				controller.choixQuestion(choix, niveau);
				controller.PageQuestions();
			}
		}
		else if (niveau == 3) {
			if (controller.niveau(choix, 3)) {
				controller.choixQuestion(choix, niveau);
				controller.PageQuestions();
			}
		}
		else {
			controller.choixQuestion(choix, niveau);
			controller.PageQuestions();
		}
	}


	@Override
	public void affiche() {
		System.out.println(model.toString(1));
		System.out.println("Choisis un sujet : info, elec, math + 1, 2 ou 3 (un espace entre les deux)");
		System.out.println("Pour proposer une question: question + 1 (un espace entre les deux)");
	}
}
