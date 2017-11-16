package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ProjetController;
import model.DemandeQuestions;
import model.Joueur;
import projetJava.ProjetMVC;

public class VueIntro extends ProjetVue implements ActionListener{
	
	private JFrame pageIntro;
	private JTextField identifiant;
	private JTextField prenom;
	private JLabel label;
	private JButton creation;
	private JButton connexion;
	
	public VueIntro(DemandeQuestions model, ProjetController controller) {
		super(model, controller);
		pageIntro = new JFrame();
		pageIntro.setTitle("Page d'introduction");
		pageIntro.setSize(400, 200);
		pageIntro.setLocation(700, 50); //(horizontal, vertical)
		pageIntro.setAlwaysOnTop(true);
		pageIntro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pageIntro.setBackground(Color.BLUE);
		pageIntro.setVisible(true);
		
		
		Box main = Box.createVerticalBox();
		Container contentpane = pageIntro.getContentPane();
		contentpane.add (main);
		
		Color c_bleu = new Color(0,191,255);
		Color c_vert = new Color(144,238,144);
		
		JTextArea question = new JTextArea ("Nom du jeux"); 
		question.setPreferredSize (new Dimension (400, 50));
		question.setBackground(c_vert);
		question.setForeground(Color.WHITE);
		question.setEditable (false); 
		main.add(question);
		
		JTextArea consignes = new JTextArea ("Création pour un nouveau joueur ou connexion si tu as déjà joué"); 
		consignes.setPreferredSize (new Dimension (400, 50));
		consignes.setBackground(c_bleu);
		consignes.setForeground(Color.WHITE);
		consignes.setEditable (false); 
		main.add(consignes);
		
		Box bottom = Box.createHorizontalBox(); 
		main.add(bottom);
		
		Box bottom1 = Box.createHorizontalBox(); 
		main.add(bottom1);
		
		Box bottom2 = Box.createHorizontalBox();
		bottom2.setBackground(Color.MAGENTA);  //Cette ligne ne fonctionne pas 
		main.add(bottom2);
		
		label = new JLabel("Identifiant: "); 
		label.setPreferredSize (new Dimension (80, 20));
		label.setBackground(Color.cyan);
		bottom.add(label);
		
		identifiant = new JTextField ("Identifiant"); 
		identifiant.setPreferredSize (new Dimension (100, 20));
		identifiant.setBackground(Color.CYAN);
		bottom.add(identifiant);
		
		label = new JLabel ("Prénom: "); 
		label.setPreferredSize (new Dimension (80, 20));
		label.setBackground(Color.lightGray);
		bottom1.add(label);
		
		prenom = new JTextField ("Prénom"); 
		prenom.setPreferredSize (new Dimension (100, 20));
		prenom.setBackground(Color.lightGray);
		bottom1.add(prenom);
		
		creation = new JButton ("S'enregistrer");
		creation.setBackground(Color.MAGENTA);
		bottom2.add(creation);
		connexion = new JButton ("Se connecter"); 
		connexion.setBackground(Color.MAGENTA);
		bottom2.add(connexion);
		
		connexion.addActionListener(this);
		creation.addActionListener(this);
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	@Override
	public void affiche(String msg) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == connexion) {
			pageIntro.setVisible(false);
			ProjetController ctrlSujet = new ProjetController(model);
			ProjetVue sujet = new VueSujet(model, ctrlSujet);
			ctrlSujet.addview(sujet);
		}
		if(e.getSource() == creation) {
			Joueur model = new Joueur(identifiant.getText(), prenom.getText());
		}
	}
	
}
