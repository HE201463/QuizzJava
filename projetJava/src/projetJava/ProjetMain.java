package projetJava;

import java.sql.SQLException;

public class ProjetMain {

public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new ProjetMVC(0, 1);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		});
	}
	

}
