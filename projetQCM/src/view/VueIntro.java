package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ProjetController;
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
@Getter
@Setter

/**
 * Cette classe permet de créer l'interface de l'introduction. Le joueur va pouvoir s'enregistrer ou se connecter grâce à des boutons
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
		Box main = Box.createVerticalBox();
		intro.add(main);
			
		Color c_bleu = new Color(0,191,255);
		Color c_vert = new Color(144,238,144);
		
		JTextArea question = new JTextArea("Nom du jeux"); 
		question.setFont(f);
		//question.setPreferredSize (new Dimension (470, 50));
		question.setBackground(c_vert);
		question.setForeground(Color.WHITE);
		question.setEditable (false); 
		main.add(question);
		
		JTextArea consignes = new JTextArea("S'enregistrer pour un nouveau joueur\nSe connecter si tu as déjà joué"); 
		//consignes.setPreferredSize (new Dimension (400, 50));
		consignes.setFont(f);
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
				
		verif = new JTextArea ("pour verifier"); 
		//verif.setPreferredSize (new Dimension (400, 50));
		verif.setBackground(c_bleu);
		verif.setFont(f);
		verif.setForeground(Color.GRAY);
		verif.setEditable (false); 
		main.add(verif);
		
		JTextArea identifiantTexte = new JTextArea("Identifiant : "); 
		//identifiantTexte.setPreferredSize (new Dimension (5, 10));
		identifiantTexte.setFont(f);
		bottom.add(identifiantTexte);
		
		identifiant = new JTextField ("Identifiant"); 
		identifiant.setPreferredSize (new Dimension (350, 20));
		identifiant.setBackground(Color.CYAN);
		bottom.add(identifiant);
		
		JTextArea prenomTexte = new JTextArea ("Prénom: "); 
		//prenomTexte.setPreferredSize (new Dimension (5, 10));
		prenomTexte.setFont(f);
		bottom1.add(prenomTexte);
		
		prenom = new JTextField ("Prénom");
		prenom.setPreferredSize (new Dimension (350, 20));
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

	@Override
	public void affiche() {
		
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
