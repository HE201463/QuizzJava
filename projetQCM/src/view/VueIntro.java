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
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
@Getter
@Setter

/**
 * Cette classe permet de cr�er l'interface de l'introduction. Le joueur va pouvoir s'enregistrer ou se connecter gr�ce � des boutons
 * On a �galement le nom du jeux et l'explication a suivre pour jouer
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
	 * Cette constructeur va cr�er la vue avec les boutons, JTextField et autre �l�ments.
	 * @param model C'est le mod�le utilis� pour la structure MVC
	 * @param controller C'est le controller utilis� pour la structure MVC
	 */
	public VueIntro(ProjetModel model, ProjetController controller) {
		super(model, controller);
		intro = new JPanel();
		intro.setBackground(Color.yellow);
		Font f = new Font("Serif", Font.PLAIN, 20);
		Box main = Box.createVerticalBox();
		intro.add(main);
			
		Color c_bleu = new Color(0,191,255);
		Color c_vert = new Color(144,238,144);
		
		JTextArea question = new JTextArea("Nom du jeux"); 
		question.setFont(f);
		question.setBackground(c_vert);
		question.setForeground(Color.WHITE);
		question.setEditable (false); 
		main.add(question);
		
		JTextArea consignes = new JTextArea("S'enregistrer pour un nouveau joueur\nSe connecter si tu as d�j� jou�"); 
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
		
		JTextArea identifiantTexte = new JTextArea("Identifiant : "); 
		identifiantTexte.setEditable(false);
		identifiantTexte.setFont(f);
		identifiantTexte.setBackground(Color.GREEN);
		bottom.add(identifiantTexte);
		
		identifiant = new JTextField ("deMahieu"); 
		identifiant.setPreferredSize (new Dimension (350, 20));
		identifiant.setBackground(Color.CYAN);
		bottom.add(identifiant);
		
		JTextArea prenomTexte = new JTextArea ("Pr�nom : "); 
		prenomTexte.setEditable(false);
		prenomTexte.setBackground(Color.GREEN);
		prenomTexte.setFont(f);
		bottom1.add(prenomTexte);
		
		prenom = new JTextField ("Benoit");
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
			if(controller.verifconnecte(identifiant.getText(), prenom.getText())) {
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
