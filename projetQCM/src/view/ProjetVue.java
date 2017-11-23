package view;

import java.util.Observer;

import javax.swing.JPanel;

import controller.ProjetController;
import model.ProjetModel;


public abstract class ProjetVue extends JPanel implements Observer{
	
	protected ProjetModel model;
	protected ProjetController controller;
	
	public ProjetVue(ProjetModel model, ProjetController controller){
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}
		
	public abstract void affiche (String msg);
}
