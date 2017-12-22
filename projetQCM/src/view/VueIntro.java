package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ProjetController;
import model.ProjetModel;


/**
 * Cette classe permet de créer l'interface de l'introduction. Le joueur va pouvoir s'enregistrer ou se connecter grâce à  des boutons
 * On a également le nom du jeux et l'explication a suivre pour jouer
 * @author B
 *
 */
public class VueIntro extends ProjetVue implements ActionListener{
	
	private JPanel intro;
	private JTextField identifiant;
	private JTextField prenom;
	private JButton creation;
	private JButton connexion;
	private JTextArea verif;
	
	/**
	 * Cette constructeur va créer la vue avec les boutons, JTextField et autre éléments.
	 * @param model C'est le modèle utilisé pour la structure MVC
	 * @param controller C'est le controller utilisé pour la structure MVC
	 */
	public VueIntro(ProjetModel model, ProjetController controller) {
		super(model, controller);
		intro = new JPanel();
		Font f = new Font("Serif", Font.PLAIN, 20);
		Font g = new Font("Serif", Font.PLAIN, 16);
		Box main = Box.createVerticalBox();
		intro.add(main);
			
		Color beige = new Color(245,245,220);
		Color transparent = new Color(0,0,0,0);
		Color AntiqueWhite3 = new Color(205,192,176);
		
		intro.setBackground(beige);
		JTextArea question = new JTextArea("Super Quizz"); 
		question.setFont(f);
		question.setPreferredSize(new Dimension(300,50));
		question.setBackground(transparent);
		question.setEditable (false); 
		main.add(question);
		
		JTextArea consignes = new JTextArea("S'enregistrer pour un nouveau joueur\nSe connecter si tu as déjà  joué"); 
		consignes.setFont(g);
		consignes.setPreferredSize(new Dimension(300,60));
		consignes.setBackground(transparent);
		consignes.setEditable (false); 
		main.add(consignes);
		
		Box bottom = Box.createHorizontalBox(); 
		main.add(bottom);
		
		JPanel espace = new JPanel();
		espace.setPreferredSize(new Dimension(300,10));
		espace.setBackground(transparent);
		main.add(espace);
		
		Box bottom1 = Box.createHorizontalBox(); 
		main.add(bottom1);
		
		JPanel espace1 = new JPanel();
		espace1.setPreferredSize(new Dimension(300,20));
		espace1.setBackground(transparent);
		main.add(espace1);
		
		Box bottom2 = Box.createHorizontalBox();
		main.add(bottom2);
		
		JTextArea identifiantTexte = new JTextArea("Identifiant : "); 
		identifiantTexte.setBackground(transparent);
		identifiantTexte.setFont(f);
		identifiantTexte.setEditable (false); 
		bottom.add(identifiantTexte);
		
		//Entre ton identifiant
		identifiant = new JTextField ("Entre ton identifiant"); 
		identifiant.setPreferredSize (new Dimension (350, 20));
		identifiant.setBackground(AntiqueWhite3);
		bottom.add(identifiant);
		
		JTextArea prenomTexte = new JTextArea ("Prénom : "); 
		prenomTexte.setFont(f);
		prenomTexte.setBackground(transparent);
		prenomTexte.setEditable (false);
		bottom1.add(prenomTexte);
		
		//Entre ton prénom
		prenom = new JTextField ("Entre ton prénom");
		prenom.setPreferredSize (new Dimension (350, 20));
		prenom.setBackground(AntiqueWhite3);
		bottom1.add(prenom);
		
		creation = new JButton ("S'enregistrer");
		creation.setBackground(AntiqueWhite3);
		bottom2.add(creation);
		connexion = new JButton ("Se connecter"); 
		connexion.setBackground(AntiqueWhite3);
		bottom2.add(connexion);
		
		connexion.addActionListener(this);
		creation.addActionListener(this);
		identifiant.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				identifiant.setText("");				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
			
		});
		prenom.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent arg0) {
				prenom.setText("");				
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				
			}
			
		});
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	@Override
	public void affiche() {
		
	}
	
	@Override
	public void affiche(String msg) {
		verif.setText(msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == connexion) {
			if(controller.verifConnecte(identifiant.getText(), prenom.getText())) {
				controller.PageSujet(identifiant.getText());
			}			
		}
		if(e.getSource() == creation) {
			if(controller.verifIdentite(identifiant.getText())) {
				model.enregistrer(identifiant.getText(), prenom.getText());
				controller.PageSujet(identifiant.getText());
			}
		}
	}

	
	
	
	
	
	//Getter et Setter
	public JPanel getIntro() {
		return intro;
	}

	public void setIntro(JPanel intro) {
		this.intro = intro;
	}

	public JTextField getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(JTextField identifiant) {
		this.identifiant = identifiant;
	}

	public JTextField getPrenom() {
		return prenom;
	}

	public void setPrenom(JTextField prenom) {
		this.prenom = prenom;
	}

	public JButton getCreation() {
		return creation;
	}

	public void setCreation(JButton creation) {
		this.creation = creation;
	}

	public JButton getConnexion() {
		return connexion;
	}

	public void setConnexion(JButton connexion) {
		this.connexion = connexion;
	}

	public JTextArea getVerif() {
		return verif;
	}

	public void setVerif(JTextArea verif) {
		this.verif = verif;
	}
	
	
	
}
