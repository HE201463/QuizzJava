package main;

import java.sql.SQLException;

import controller.ProjetController;
import model.ProjetModel;

public class ProjetMain {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ProjetModel model = new ProjetModel();
				ProjetController controller = new ProjetController(model);
				controller.PageIntro();
			}
		});
	}

}
