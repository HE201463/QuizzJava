package view;

import java.util.Observer;

import javax.swing.JPanel;

import controller.ProjetController;
import model.DemandeQuestions;
import model.Joueur;

public abstract class ProjetVue extends JPanel implements Observer{
	
	protected DemandeQuestions model;
	protected Joueur modelJoueur;
	protected ProjetController controller;
	
	public ProjetVue(DemandeQuestions model, ProjetController controller){
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}
	
	public ProjetVue(Joueur modelJoueur, ProjetController controller){
		this.modelJoueur = modelJoueur;
		this.controller = controller;
		modelJoueur.addObserver(this);
	}
	
	public abstract void affiche (String msg);
}
