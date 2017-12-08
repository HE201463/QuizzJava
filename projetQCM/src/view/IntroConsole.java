package view;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import controller.ProjetController;
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;

@Getter
@Setter
public class IntroConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	protected volatile boolean arret = true;
	public IntroConsole(ProjetModel model, ProjetController controller) {
		super(model, controller);
		update(null, null);
		sc = new Scanner(System.in);
		new Thread (new ReadInput()).start();
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("Bienvenue dans ce jeu ...");
		System.out.println("Tape E + un identifiant et un prenom pour t'enregistrer");
		System.out.println("Tape C + ton identifiant et ton prenom pour te connecter");
		System.out.println("ATTENTION : identifiant et prenom en un seul mot et avec un espace entre chaque");
	}

	@Override
	public void affiche(String msg) {
		System.out.println(msg);		
	}
	
	public void arret() {
		arret = false;
	}
	
	
	private class ReadInput implements Runnable{
		public void run() {
			while(arret){
				try{
					String c = sc.next();
					System.out.println("toto");
					String identifiant = sc.next();
					String prenom = sc.next();
					switch(c) {
						case "C" :
							if(controller.verifConnecte(identifiant, prenom)) {
								arret = false;
								controller.PageSujet(identifiant);
							}
							break;
						case "E" :
							if(controller.verifIdentite(identifiant)) {
								model.enregistrer(identifiant, prenom);
								controller.PageSujet(identifiant);
							}
							arret = false;
							break;
						default : 
							affiche("Probl�me d'�criture");
							break;
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
		System.out.println("Bienvenue dans ce jeu ...");
		System.out.println("Tape E + un identifiant et un prenom pour t'enregistrer");
		System.out.println("Tape C + ton identifiant et ton prenom pour te connecter");
		System.out.println("ATTENTION : identifiant et prenom en un seul mot et avec un espace entre chaque");
	}
}