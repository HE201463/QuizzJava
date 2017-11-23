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
import model.Joueur;

@Getter
@Setter
public class IntroConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	protected boolean arret = true;
	private String stop = new String("vrai");
	private String marche = new String ("vrai");
	public IntroConsole(Joueur modelJoueur, ProjetController controller) {
		super(modelJoueur, controller);
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
	
	public boolean changer() {
		int comparer = stop.compareTo(marche);
		System.out.println(comparer);
		System.out.println(stop);
		if (stop.equals(marche)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private class ReadInput implements Runnable{
		public void run() {
			changer();
			System.out.println(arret);
			while(changer()){
				try{
					String c = sc.next();
					System.out.println("toto");
					String identifiant = sc.next();
					String prenom = sc.next();
					switch(c) {
						case "C" :
							affiche("Tu veux te connecter");
							if(controller.verifconnecte(identifiant, prenom)) {
								controller.changerPage(identifiant, prenom);
							}
							arret = false;
							break;
						case "E" :
							affiche("tu veux t'enregistrer");
							if(controller.verifIdentite(identifiant)) {
								modelJoueur.enregistrer(identifiant, prenom);
								controller.changerPage(identifiant, prenom);
							}
							arret = false;
							break;
						default : 
							affiche("Problème d'écriture");
							break;
					}	
				}
				catch(InputMismatchException e){
					affiche("Format d'input incorrect");
				}
			}
		}
	}
}


