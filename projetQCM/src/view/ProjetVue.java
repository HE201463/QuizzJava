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
	 * Ce constructeur attribue le mod�le et le controler � la vue souhait�e.
	 * @param model C'est le mod�le MVC
	 * @param controller C'est le controller MVC
	 */
	public ProjetVue(ProjetModel model, ProjetController controller){
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}
		
	/**
	 * Cette m�thode permet d'afficher un message soit dans un JTexteField soit dans la console
	 * @param msg C'est le message � afficher.
	 */
	public abstract void affiche (String msg);
	
	/**
	 * Cette m�thode me permet d'afficher des choses bien d�finit qui doit s'afficher que � certains moments.
	 */
	public abstract void affiche();
}
