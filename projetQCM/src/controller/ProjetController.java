package controller;

import java.awt.Color;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.ProjetModel;
import view.ProjetVue;
import view.SujetConsole;
import view.VueSujet;

/**
 * Cette classe est le controller du modèle MVC, il sert à  lier les vues aux différents modèles.
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 * On utilise aussi le Jar Lombok qui permet de générer les getter et setter sans les écrire
 */

public class ProjetController {
	
	private ProjetModel model;
	private ProjetVue vue;
	private ProjetVue console;
	private int i=0;
	protected String page = "intro";
	private int points = 0;
	private int nombreQuestion = 4; //Nombre de question-1
	private int nombre; //Ce nombre sert pour le nombre de point pour passer d'un niveau a l'autre
	
	//Variables utiles pour le compte a rebours
	private boolean arret = false;
	private long tempsFinal;
	/**
	 * Constructeur qui instancie le model de ce pattern MVC
	 * @param model à instancier
	 */
	public ProjetController(ProjetModel model) {
		this.model = model;
	}
	
	/**
	 * Cette méthode instancie une vue GUI
	 * @param vue Vue GUI à instancier
	 */
	public void addview(ProjetVue vue) {
		this.vue = vue;
	}
	
	/**
	 * Cette méthode instancie une vue console
	 * @param console Vue console à instancier
	 */
	public void addview2(ProjetVue console) {
		this.console = console;
	}
	
	/**
	 * Cette méthode va vérifier la réponse à  la question et fait donc appel à  la méthode comparaison de la classe ProjetModel
	 * @param choix de la réponse à  la question
	 */
	public void verification(String choix) {
		if(model.comparaison(choix)) {
			console.affiche("Bonne réponse");
			points = model.getJoueur().getPoint() + 1;
			model.getJoueur().setPoint(points);
		}
		else {
			console.affiche("Mauvaise réponse");
		}
	}
	
	/**
	 * Cette méthode utilise la méthode verifIdentifier de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param identifiant à  vérifier
	 * @return false si le pseudo est en BDD et affiche que l'identifiant existe déjà  
	 * @return true dans les autres cas et affiche que l'identifiant est correct
	 */
	public boolean verifIdentite(String identifiant) {
		if(model.verifIdentifier(identifiant)) {
			console.affiche("Cette identifiant existe déjà ");
			JOptionPane.showMessageDialog(null, "Cette identifiant existe déjà ", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			console.affiche("Identifiant correct");
			return true;
		}
	}
	
	/**
	 * Cette méthode utilise la méthode verifConnecter de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param identifiant unique du joueur
	 * @param prenom qui permet la vérification de la combinaison avec le pseudo 
	 * @return true si la combinaison est bonne et affiche que le compte est correct
	 * @return false dans les autres cas et affiche que l'identifiant ou le prénom est incorrect
	 */
	public boolean verifConnecte(String identifiant, String prenom) {
		if(model.verifConnecter(identifiant, prenom)) {
			console.affiche("Ce compte est correct");
			return true;
		}
		else {
			console.affiche("Identifiant ou prenom incorrect");
			JOptionPane.showMessageDialog(null, "Identifiant ou prenom incorrect", "Erreur", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	/**
	 * Cette méthode utilise la méthode questionSuivante de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * 
	 */
	public void questionSuivante() {
		if(i<nombreQuestion) {
			i++;
			model.questionSuivante(i);
			vue.affiche();
		}
		else {
			console.affiche("C'est terminé");
			arret = false;
			try {
				model.changerPoints(model.getJoueur().getIdentifiant(), model.getJoueur().getPoint());
				((VueSujet)vue).getTextPoints().setText("Point total: " + model.getJoueur().getPoint());
				i = 0;
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			console.affiche();
			page = "sujet";
			((VueSujet)vue).getBottom1().setVisible(true);
			((VueSujet)vue).getBottom2().setVisible(true);
			((VueSujet)vue).getPropQuestion().setVisible(true);
			((VueSujet)vue).getQuizz().setVisible(false);
			((VueSujet)vue).getMiddle().setVisible(true);
		}
		
	}
	
	/**
	 * Cette méthode vérifie le nombre de point du joueur et son niveau. Selon ces paramètres il pourra passer au niveau suivant.
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
		if (choix.equals("mathématique")) {
			if (model.getJoueur().getNivMath() < niveau && model.getJoueur().getPoint() < nombre)return 1;
			if (model.getJoueur().getNivMath() == niveau-1 && model.getJoueur().getPoint() > nombre)return 2;
			if (model.getJoueur().getNivMath() < niveau && model.getJoueur().getPoint() > nombre)return 3;
			if(model.getJoueur().getNivMath() > niveau) return 4;
		}
		if (choix.equals("électronique")) {
			if (model.getJoueur().getNivElec() < niveau && model.getJoueur().getPoint() < nombre)return 1;
			if (model.getJoueur().getNivElec() == niveau-1 && model.getJoueur().getPoint() > nombre)return 2;
			if (model.getJoueur().getNivInfo() < niveau && model.getJoueur().getPoint() > nombre)return 3;
			if(model.getJoueur().getNivElec() > niveau) return 4;
		}
		return 0;
	}
	
	/**
	 * Cette méthode va permettre de signaler si le joueur n'a pas assez de points, si c'est le cas soustraire le nombre de points
	 * ou alors de poser des questions d'un niveau différents a 1
	 * @param choix Ce choix est le sujet qu'il a choisis d'augmenter (info, math ou elec pour le moment)
	 * @param niveau Le niveau est celui qu'il veut augmenter (2 ou 3)
	 * @return true si le joueur à suffisement de points pour passer au niveau suivant, false s'il n'en a pas assez ou s'il n'a pas débloqué le niveau précédent
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
	 * Cette méthode change le niveau du joueur dans la DB
	 * @param choix Ce choix est le sujet qu'il a choisis d'augmenter (info, math ou elec pour le moment)
	 * @param niveau Le niveau est celui qu'il veut augmenter (2 ou 3)
	 */
	public void changerNiveau(String choix, int niveau){
		if(choix.equals("informatique")) {
			model.getJoueur().setNivInfo(niveau);
		}
		if(choix.equals("mathématique")) {
			model.getJoueur().setNivMath(niveau);
		}
		if(choix.equals("électronique")) {
			model.getJoueur().setNivElec(niveau);
		}
	}
	/**
	 * Cette méthode utilise la méthode choixQuestion de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param sujet choisi pour àªtre interrogé dessus
	 * @param niveau de question qui sera posée dans le sujet choisi
	 */
	public void choixQuestion(String sujet, int niveau) {
		try {
			model.choixQuestion(sujet, niveau);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
	/**
	 * Cette méthode utilise la méthode proposerQuestion de la classe ProjetModel qui pourra donc àªtre utilisée dans la vue
	 * @param question La question proposée par le Joueur
	 * @param r1 La bonne réponse à  la question
	 * @param r2 Une autre réponse
	 * @param r3 Une autre réponse
	 * @param r4 Une autre réponse
	 */
	public void proposeQuestion(String question, String r1, String r2, String r3, String r4) {
		model.proposerQuestion(question, r1, r2, r3, r4);
		console.affiche();
	}
	
	
	
	/**
	 * Cette méthode va créer la page de choix de sujet en utilisant les constructeurs des classes SujetConsole et VueSujet
	 * Des modifiication à  la vue GUI sont faites ici
	 * @param identifiant qui permettra de récupérer le prénom, les points et les niveaux du joueur
	 */
	public void PageSujet(String identifiant) {
		page = "sujet";
		vue.setVisible(false);
		model.connecter(identifiant);
		console = new SujetConsole(model, this);
		this.addview2(console);
		vue = new VueSujet(model, this);
		this.addview(vue);
		console.affiche();
		vue.setTitle("Sujet");
		//vue.setLocation(700, 50); //(horizontal, vertical)
		vue.setLocationRelativeTo(null);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(600, 350);
		vue.setLocationRelativeTo(null);
		vue.setVisible(true);
		vue.getContentPane().add(((VueSujet)vue).getSujet());
	}
	
	/**
	 * Cette méthode va créer la page d'affichage des questions en utilisant les constructeurs des classes QuestionConsole et VueQuestion
	 * Des modifications à  la vue GUI sont faites ici
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
	 * Envoie une question proposée avec ses réponses
	 * @return la question proposée avec ses réponses
	 */
	public List<String> showProposition() {
		return model.showProposition();
		
	}
	/**
	 * Demande la suppression de la question proposée 
	 * @param q question à  supprimé
	 * @param r réponse à  supprimer (Pour le where)
	 */
	public void deleteProposition(String q, String r) {
		model.deleteProposition(q, r);
	}
	/**
	 * Demande l'ajout d la question proposée  la BDD
	 * @param q question à  ajouter
	 * @param r1 Bonne réponse
	 * @param r2 Autre réponse
	 * @param r3 Autre réponse 
	 * @param r4 Autre réponse
	 * @param sujet Sujet de la question
	 * @param niveau Niveau de la question
	 */
	public void addProposition(String q, String r1, String r2, String r3, String r4, String sujet, int niveau) {
		model.addProposition(q, r1, r2, r3, r4, sujet, niveau);
	}
	/**
	 * Afficher la base de la page de sujet en console
	 */
	public void retourAffiche() {
		console.affiche();
	}

	/**
	 * Cette méthode permet de mettre ou de remettre le compteur à 20
	 */
	public void recommence() {
		tempsFinal = System.currentTimeMillis() + 20000;
	}
	
	/**
	 * Cette classe est utilisée par le thread initié par la méthode PageQuestions
	 * Elle permet de lancer le chronomètre et de passer a la question suivante.
	 * @author B
	 *
	 */
	private class Chrono implements Runnable{
		public void run() {
			recommence();
			while(arret) {
				if(System.currentTimeMillis() > tempsFinal) {
					((VueSujet)vue).getChrono().setText("0");
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
					((VueSujet)vue).getChrono().setText(""+reste/1000);
				}
			}
		}
	}
	
	//Getter and Setter
	public String getPage() {
		return page;
	}
	
}
