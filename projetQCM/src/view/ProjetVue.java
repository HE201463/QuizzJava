package view;

import java.util.Observer;

import javax.swing.JFrame;

import controller.ProjetController;
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;

@Getter
@Setter
public abstract class ProjetVue extends JFrame implements Observer{
	
	protected ProjetModel model;
	protected ProjetController controller;
	
	/**
	 * Ce constructeur attribue le modèle et le controler à la vue souhaitée.
	 * @param model C'est le modèle MVC
	 * @param controller C'est le controller MVC
	 */
	public ProjetVue(ProjetModel model, ProjetController controller){
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}
		
	/**
	 * Cette méthode permet d'afficher un message soit dans un JTexteField soit dans la console
	 * @param msg C'est le message à afficher.
	 */
	public abstract void affiche (String msg);
	
	/**
	 * Cette méthode me permet d'afficher des choses bien définit qui doit s'afficher que à certains moments.
	 */
	public abstract void affiche();
}
