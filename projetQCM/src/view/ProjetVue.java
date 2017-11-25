package view;

import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ProjetController;
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;

@Getter
@Setter
public abstract class ProjetVue extends JFrame implements Observer{
	
	protected ProjetModel model;
	protected ProjetController controller;
	
	public ProjetVue(ProjetModel model, ProjetController controller){
		this.model = model;
		this.controller = controller;
		model.addObserver(this);
	}
		
	public abstract void affiche (String msg);
	
	public abstract void affiche();
}
