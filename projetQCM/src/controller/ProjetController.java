package controller;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
import view.ProjetVue;
import view.SujetConsole;
import view.VueSujet;

/**
 * Cette classe est le controller du mod�le MVC, il sert � lier les vues aux diff�rents mod�les.
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 * On utilise aussi le Jar Lombok qui permet de g�n�rer les getter et setter sans les �crire
 */
@Getter
@Setter
public class ProjetController {
	
	private ProjetModel model;
	private ProjetVue vue;
	private ProjetVue console;
	private int i;
	protected String page = "intro";
	private int points = 0;
	private int nombreQuestion = 4;//nombre de question-1
	private int nombre; //Ce nombre sert pour le nombre de point pour passer d'un niveau a l'autre
	
	//Variables utiles pour le compte a rebours
	private boolean arret = false;
	private long tempsFinal;
	
	/**
	 * Constructeur qui instancie le model de ce pattern MVC
	 * @param model � instancier
	 */
	public ProjetController(ProjetModel model) {
		this.model = model;
	}
	
	/**
	 * Cette m�thode instancie une vue GUI
	 * @param vue � instancier
	 */
	public void addview(ProjetVue vue) {
		this.vue = vue;
	}
	
	/**
	 * Cette m�thode instancie une vue console
	 * @param vue console � instancier
	 */
	public void addview2(ProjetVue console) {
		this.console = console;
	}
	
	/**
	 * Cette m�thode va v�rifier la r�ponse � la question et fait donc appel � la m�thode comparaison de la classe ProjetModel
	 * @param choix de la r�ponse � la question
	 */
	public void verification(String choix) {
		if(model.comparaison(choix)) {
			console.affiche("Bonne r�ponse");
			//vue.affiche("Bonne r�ponse");
			points = model.getJoueur().getPoint() + 1;
			model.getJoueur().setPoint(points);
		}
		else {
			console.affiche("Mauvaise r�ponse");
			//vue.affiche("Mauvaise r�ponse");
		}
	}
	
	/**
	 * Cette m�thode utilise la m�thode verifIdentifier de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param identifiant � v�rifier
	 * @return false si le pseudo est en BDD et affiche que l'identifiant existe d�j� 
	 * @return true dans les autres cas et affiche que l'identifiant est correct
	 */
	public boolean verifIdentite(String identifiant) {
		if(model.verifIdentifier(identifiant)) {
			console.affiche("Cette identifiant existe d�j�");
			JOptionPane.showMessageDialog(null, "Cette identifiant existe d�j�", "Erreur", JOptionPane.ERROR_MESSAGE);
			//vue.affiche("Cette identifiant existe d�j�");
			return false;
		}
		else {
			console.affiche("Identifiant correct");
			//vue.affiche("Identifiant correct");
			return true;
		}
	}
	
	/**
	 * Cette m�thode utilise la m�thode verifConnecter de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param identifiant unique du joueur
	 * @param prenom qui permet la v�rification de la combinaison avec le pseudo 
	 * @return true si la combinaison est bonne et affiche que le compte est correct
	 * @return false dans les autres cas et affiche que l'identifiant ou le pr�nom est incorrect
	 */
	public boolean verifconnecte(String identifiant, String prenom) {
		if(model.verifConnecter(identifiant, prenom)) {
			console.affiche("Ce compte est correct");
			//vue.affiche("Ce compte est correct");
			return true;
		}
		else {
			console.affiche("Identifiant ou prenom incorrect");
			JOptionPane.showMessageDialog(null, "Identifiant ou prenom incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
			//vue.affiche("Identifiant ou prenom incorrect");
			return false;
		}
	}
	
	
	/**
	 * Cette m�thode utilise la m�thode questionSuivante de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * 
	 */
	public void questionSuivante() {
		if(i<nombreQuestion) {
			i++;
			model.questionSuivante(i);
			vue.affiche();
		}
		else {
			console.affiche("C'est termin�");
			//vue.affiche("C'est termin�");
			arret = false;
			try {
				//points = model.getJoueur().getPoint() + points;
				model.changerPoints(model.getJoueur().getIdentifiant(), model.getJoueur().getPoint());
				//model.getJoueur().setPoint(points);
				((VueSujet)vue).getTextPoints().setText("Point total: " + model.getJoueur().getPoint());
				i = 0;
				//points = 0;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			console.affiche();
			page = "sujet";
			((VueSujet)vue).getBottom1().setVisible(true);
			((VueSujet)vue).getBottom2().setVisible(true);
			((VueSujet)vue).getPropQuestion().setVisible(true);
			((VueSujet)vue).getQuizz().setVisible(false);
		}
		
	}
	
	/**
	 * Cette m�thode v�rifie le nombre de point du joueur et son niveau. Selon ces param�tres il pourra passer au niveau suivant.
	 * @param choix Ce choix est le sujet qu'il a choisis d'augmenter (info, math ou elec pour le moment)
	 * @param niveau Le niveau est celui qu'il veut augmenter (2 ou 3)
	 * @return return true si le niveau est insuffisant et qu'il n'a pas assez de points pour le passer
	 * @return return false si son niveau est insuffisant mais qu'il a assez de points pour passer au niveau suivant
	 */
	public int niv(String choix, int niveau) {
		if(choix.equals("informatique")) {
			if (model.getJoueur().getNivInfo() < niveau && model.getJoueur().getPoint() < nombre)return 1;
			if (model.getJoueur().getNivInfo() == niveau-1 && model.getJoueur().getPoint() > nombre)return 2;
			if (model.getJoueur().getNivInfo() < niveau && model.getJoueur().getPoint() > nombre)return 3;
			if(model.getJoueur().getNivInfo() > niveau) return 4;
		}
		if (choix.equals("math�matique")) {
			if (model.getJoueur().getNivMath() < niveau && model.getJoueur().getPoint() < nombre)return 1;
			if (model.getJoueur().getNivMath() == niveau-1 && model.getJoueur().getPoint() > nombre)return 2;
			if (model.getJoueur().getNivMath() < niveau && model.getJoueur().getPoint() > nombre)return 3;
			if(model.getJoueur().getNivMath() > niveau) return 4;
		}
		if (choix.equals("�lectronique")) {
			if (model.getJoueur().getNivElec() < niveau && model.getJoueur().getPoint() < nombre)return 1;
			if (model.getJoueur().getNivElec() == niveau-1 && model.getJoueur().getPoint() > nombre)return 2;
			if (model.getJoueur().getNivInfo() < niveau && model.getJoueur().getPoint() > nombre)return 3;
			if(model.getJoueur().getNivElec() > niveau) return 4;
		}
		return 0;
	}
	
	/**
	 * Cette m�thode va permettre de signaler si le joueur n'a pas assez de points, si c'est le cas soustraire le nombre de points
	 * ou alors de poser des questions d'un niveau diff�rents a 1
	 * @param choix Ce choix est le sujet qu'il a choisis d'augmenter (info, math ou elec pour le moment)
	 * @param niveau Le niveau est celui qu'il veut augmenter (2 ou 3)
	 * @return
	 */
	public boolean niveau(String choix, int niveau) {
		if (niveau == 2) {
			nombre = 200;
		}
		else {
			nombre = 400;
		}
		if (niv(choix, niveau) == 1) {
			console.affiche(("Pas assez de points. Il faut " + nombre + " points"));
			JOptionPane.showMessageDialog(null, "Pas assez de points.\nIl faut " + nombre + " points", "Erreur", JOptionPane.ERROR_MESSAGE); 
			return false;
		}
		
		if(niv(choix, niveau) == 2) {
			points = model.getJoueur().getPoint() - nombre;
			try {
				model.getQuest().changerNiv(model.getJoueur().getIdentifiant(), choix, niveau);
				model.getJoueur().setPoint(points);
				changerNiveau(choix, niveau);
				model.getQuest().changerPoints(model.getJoueur().getIdentifiant(), points);
				points = 0;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
		if (niv(choix, niveau) == 3){
			console.affiche(("Il faut valider le niveau " + (niveau-1) + " avant"));
			JOptionPane.showMessageDialog(null, "Il faut valider le niveau " + (niveau-1) + " avant", "Erreur", JOptionPane.ERROR_MESSAGE); 
			return false;
		}
		return true;
	}
	
	/**
	 * Cette m�thode change le niveau du joueur dans la DB
	 * @param choix Ce choix est le sujet qu'il a choisis d'augmenter (info, math ou elec pour le moment)
	 * @param niveau Le niveau est celui qu'il veut augmenter (2 ou 3)
	 */
	public void changerNiveau(String choix, int niveau){
		if(choix.equals("informatique")) {
			model.getJoueur().setNivInfo(niveau);
		}
		if(choix.equals("math�matique")) {
			model.getJoueur().setNivMath(niveau);
		}
		if(choix.equals("�lectronique")) {
			model.getJoueur().setNivElec(niveau);
		}
	}
	/**
	 * Cette m�thode utilise la m�thode choixQuestion de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param sujet choisi pour �tre interrog� dessus
	 * @param niveau de question qui sera pos�e dans le sujet choisi
	 */
	public void choixQuestion(String sujet, int niveau) {
		try {
			model.choixQuestion(sujet, niveau);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * Cette m�thode utilise la m�thode proposerQuestion de la classe ProjetModel qui pourra donc �tre utilis�e dans la vue
	 * @param q La question propos�e par le Joueur
	 * @param r1 La bonne r�ponse � la question
	 * @param r2 Une autre r�ponse
	 * @param r3 Une autre r�ponse
	 * @param r4 Une autre r�ponse
	 */
	public void proposeQuestion(String question, String r1, String r2, String r3, String r4) {
		model.proposerQuestion(question, r1, r2, r3, r4);
	}
	
	/**
	 * Cette m�thode va cr�er la page de choix de sujet en utilisant les constructeurs des classes SujetConsole et VueSujet
	 * Des modifiication � la vue GUI sont faites ici
	 * @param identifiant qui permettra de r�cup�rer le pr�nom, les points et les niveaux du joueur
	 */
	public void PageSujet(String identifiant) {
		page = "sujet";
		vue.setVisible(false);
		model.connecter(identifiant);
		//ProjetController ctrlSujet = new ProjetController(model);
		console = new SujetConsole(model, this);
		this.addview2(console);
		vue = new VueSujet(model, this);
		this.addview(vue);
		console.affiche();
		vue.setTitle("Sujet");
		vue.setLocation(700, 50); //(horizontal, vertical)
		//vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(600,350);
		vue.setVisible(true);
		vue.getContentPane().add(((VueSujet)vue).getSujet());
	}
	
	/**
	 * Cette m�thode va cr�er la page d'affichage des questions en utilisant les constructeurs 
	 * des classes QuestionConsole et VueQuestion
	 * Des modifications � la vue GUI sont faites ici
	 * C'est ici que je lance le thread pour le compte a rebours.
	 */
	public void PageQuestions() {
		page = "question";
		model.questionSuivante(0);
		arret = true;
		new Thread (new Chrono()).start();
		((VueSujet)vue).getBottom1().setVisible(false);
		((VueSujet)vue).getPropQuestion().setVisible(false);
		((VueSujet)vue).getQuizz().setVisible(true);
	}

	/**
	 * Cette m�thode permet de mettre ou de remettre le compteur � 10
	 */
	public void recommence() {
		tempsFinal = System.currentTimeMillis() + 10000;
	}
	
	/**
	 * Cette classe est utilis� par le thread initi� par la m�thode PageQuestions
	 * Elle permet de lancer le chronom�tre et de passer a la question suivante.
	 * @author B
	 *
	 */
	private class Chrono implements Runnable{
		public void run() {
			recommence();
			while(arret) {
				if(System.currentTimeMillis() > tempsFinal) {
					((VueSujet)vue).affiche("0");
					((VueSujet)vue).getBut().setText("0");
					if (i<nombreQuestion) {
						recommence();
						questionSuivante();
					}
					else {
						questionSuivante();
					}
				}
				else {
					long reste = tempsFinal - System.currentTimeMillis();
					((VueSujet)vue).getBut().setText(""+reste/1000);
				}
			}
		}
	}
	
	
	//Getter and Setter
	public String getPage() {
		return page;
	}
	
}
