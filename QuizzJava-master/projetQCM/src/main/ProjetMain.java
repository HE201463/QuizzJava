package main;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;

import controller.ProjetController;
import model.ProjetModel;
import view.IntroConsole;
import view.ProjetVue;
import view.VueIntro;

public class ProjetMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProjetModel model = new ProjetModel();
				ProjetController ctrlIntro = new ProjetController(model);
				ProjetVue console = new IntroConsole(model, ctrlIntro);
				console.affiche();
				ctrlIntro.addview2(console);
				ProjetVue vue = new VueIntro(model, ctrlIntro);
				ctrlIntro.addview(vue);
				
				vue.setTitle("ProjetQCM");
				vue.setLocation(700, 50); //(horizontal, vertical)
				vue.setAlwaysOnTop(true);
				vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				vue.setBackground(Color.BLUE);
				vue.setSize(500,300);
				vue.setVisible(true);
				vue.getContentPane().add(((VueIntro)vue).getIntro());
			}
		});
	}

}
