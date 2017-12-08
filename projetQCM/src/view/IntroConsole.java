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

/**
 * Cette classe permet d'afficher en console l'introduction à notre jeux.
 * Le joueur va pouvoir s'enregistrer ou se connecter.
 * @author B
 *
 */
public class IntroConsole extends ProjetVue implements Observer{
	protected Scanner sc;
	
	public IntroConsole(ProjetModel model, ProjetController controller) {
		super(model, controller);
		new Thread (new ReadInput(model, controller)).start();		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	@Override
	public void affiche(String msg) {
		System.out.println(msg);		
	}
	
	@Override
	public void affiche() {
		System.out.println("Bienvenue dans ce jeu ...");
		System.out.println("Tape E + un identifiant et un prenom pour t'enregistrer");
		System.out.println("Tape C + ton identifiant et ton prenom pour te connecter");
		System.out.println("ATTENTION : identifiant et prenom en un seul mot et avec un espace entre chaque");
	}	
}
