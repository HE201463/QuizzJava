package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ProjetController;
import model.DemandeQuestions;
import model.Joueur;
import projetJava.ProjetMVC;

public class VueSujet extends ProjetVue implements ActionListener, ItemListener{
	
	private JFrame pageSujet;
	private JTextField text;
	private JButton propQuestion;
	private JComboBox<String> combo;
	private JLabel label;
	private JButton niveau1;
	private JButton niveau2;
	private JButton niveau3;
	private Box bottom2;
	private String identifiant;
	private String prenom;
	
	public VueSujet(Joueur model, ProjetController controller, String identifiant, String prenom) {
		super(model, controller);
		this.identifiant = identifiant;
		this.prenom = prenom;
		pageSujet = new JFrame();
		pageSujet.setTitle("Page des sujets");
		pageSujet.setSize(400, 200);
		pageSujet.setLocation(700, 50); //(horizontal, vertical)
		pageSujet.setAlwaysOnTop(true);
		pageSujet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pageSujet.setBackground(Color.BLUE);
		pageSujet.setVisible(true);
		
		Box main = Box.createVerticalBox();
		Container contentpane = pageSujet.getContentPane();
		contentpane.add (main);
		
		Box bottom = Box.createHorizontalBox();
		main.add(bottom);
		
		text = new JTextField (this.prenom); 
		text.setBackground(Color.LIGHT_GRAY);
		text.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(text);
		
		text = new JTextField ("Nombres points"); 
		text.setBackground(Color.GREEN);
		text.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(text);
		
		text = new JTextField ("Niveau Global"); ;
		text.setBackground(Color.CYAN);
		text.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(text);
		
		propQuestion = new JButton("Proposer une question");
		main.add(propQuestion);
		
		Box bottom1 = Box.createHorizontalBox(); 
		main.add(bottom1);
		
		label = new JLabel("Choisis un sujet: "); 
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setPreferredSize (new Dimension (200, 20));
		label.setBackground(Color.cyan);
		bottom1.add(label);
		combo = new JComboBox<String>();
		combo.addItem("*");
		combo.addItem("INFO");
		combo.addItem("MATH");
		combo.addItem("ELEC");
		bottom1.add(combo);
		combo.addItemListener(this);
		
		bottom2 = Box.createHorizontalBox(); 
		main.add(bottom2);
		bottom2.setVisible(false);
		
		
		niveau1 = new JButton ("niveau1");
		bottom2.add(niveau1);
		niveau2 = new JButton ("niveau2");
		bottom2.add(niveau2);
		niveau3 = new JButton ("niveau3");
		bottom2.add(niveau3);
		
		niveau1.addActionListener(this);
		
	}
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == niveau1) {
			pageSujet.setVisible(false);
			try {
				new ProjetMVC(0, 2);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
		}
	}



	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void affiche(String msg) {
		// TODO Auto-generated method stub
		
	}





	@Override
	public void itemStateChanged(ItemEvent e) {
		bottom2.setVisible(true);
		niveau1.setText(e.getItem() + "1");
		niveau2.setText(e.getItem() + "2");
		niveau3.setText(e.getItem() + "3");
	}
}
