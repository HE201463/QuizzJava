package controller;

import java.awt.Color;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
import view.IntroConsole;
import view.ProjetVue;
import view.QuestionConsole;
import view.SujetConsole;
import view.VueIntro;
import view.VueQuestion;
import view.VueSujet;

/**
 * Cette classe est le controller du modèle MVC, il sert à lier les vues aux différents modèles.
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 * On utilise aussi le Jar Lombok qui permet de générer les getter et setter sans les écrire
 */
@Getter
@Setter
public class ProjetController {
	
	private ProjetModel model;
	private ProjetVue vue;
	private ProjetVue console;
<<<<<<< HEAD
	private static int i=0;
	protected static String page = "intro";
	private static int points = 0;
	private int nombre; //Ce nombre sert pour le nombre de point pour passer d'un niveau a l'autre
	
=======
	private int i=0;
	private int points = 0;
	int nombre;
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
	
	/**
	 * Constructeur qui instancie le model de ce pattern MVC
	 * @param model à instancier
	 */
	public ProjetController(ProjetModel model) {
		this.model = model;
	}
	
	/**
	 * Cette méthode instancie une vue GUI
	 * @param vue à instancier
	 */
	public void addview(ProjetVue vue) {
		this.vue = vue;
	}
	
	/**
	 * Cette méthode instancie une vue console
	 * @param vue console à instancier
	 */
	public void addview2(ProjetVue console) {
		this.console = console;
	}
	
	/**
	 * Cette méthode va vérifier la réponse à la question et fait donc appel à la méthode comparaison de la classe ProjetModel
	 * @param choix de la réponse à la question
	 */
	public void verification(String choix) {
		if(model.comparaison(choix)) {
			console.affiche("Bonne r�ponse");
			vue.affiche("Bonne r�ponse");
			points++;
		}
		else {
			console.affiche("Mauvaise r�ponse");
			vue.affiche("Mauvaise r�ponse");
		}
	}
	
	/**
	 * Cette méthode utilise la méthode verifIdentifier de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param identifiant à vérifier
	 * @return false si le pseudo est en BDD et affiche que l'identifiant existe déjà 
	 * @return true dans les autres cas et affiche que l'identifiant est correct
	 */
	public boolean verifIdentite(String identifiant) {
		if(model.verifIdentifier(identifiant)) {
<<<<<<< HEAD
			console.affiche("Cette identifiant existe d�j�");
			vue.affiche("Cette identifiant existe d�j�");
=======
			console.affiche("Cette identifiant existe déjà ");
			vue.affiche("Cette identifiant existe déjà ");
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
			return false;
		}
		else {
			console.affiche("Identifiant correct");
			vue.affiche("Identifiant correct");
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
	public boolean verifconnecte(String identifiant, String prenom) {
		if(model.verifConnecter(identifiant, prenom)) {
			console.affiche("Ce compte est correct");
			vue.affiche("Ce compte est correct");
			return true;
		}
		else {
			console.affiche("Identifiant ou prenom incorrect");
			vue.affiche("Identifiant ou prenom incorrect");
			return false;
		}
	}
	
	/**
	 * Cette méthode utilise la méthode questionSuivante de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * 
	 */
	public void questionSuivante() {
<<<<<<< HEAD
		if(i<1) {
			i++;
			model.questionSuivante(i);
=======
		if(i<4) {
			i++;
			model.questionSuivante(i);
			console.affiche();
			console.affiche("Choisis la bonne rÃ©ponse en tappant 1, 2, 3 ou 4 (tu as 10 secondes)");
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
			vue.affiche();
		}
		else {
			console.affiche("C'est termin�");
			vue.affiche("C'est termin�");
			try {
				points = model.getJoueur().getPoint() + points;
				model.changerPoints(model.getJoueur().getIdentifiant(), points);
				model.getJoueur().setPoint(points);
				((VueSujet)vue).getTextPoints().setText("Point total: " + points);
				i = 0;
				points = 0;
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
	
	public boolean niv(String choix, int niveau) {
		if(choix.equals("info")) {
			if (model.getJoueur().getNivInfo() < niveau && model.getJoueur().getPoint() < 200)return true;
			return false;
		}
		if (choix.equals("math")) {
			if (model.getJoueur().getNivMath() < niveau && model.getJoueur().getPoint() < 200) return true;
			return false;
		}
		if (choix.equals("elec")) {
			if (model.getJoueur().getNivElec() < niveau && model.getJoueur().getPoint() < 200) return true;
			return false;
		}
		return false;
	}
	
<<<<<<< HEAD
	public boolean niv2(String choix, int niveau) {
		if(choix.equals("info")) {
			if (model.getJoueur().getNivInfo() < niveau)return true;
			return false;
		}
		if (choix.equals("math")) {
			if (model.getJoueur().getNivMath() < niveau) return true;
			return false;
		}
		if (choix.equals("elec")) {
			if (model.getJoueur().getNivElec() < niveau) return true;
			return false;
		}
		return false;
	}
	
	public boolean niveau(String choix, int niveau) {
=======
	public boolean niveau(String choix, int niveau)  {
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
		if (niveau == 2) {
			nombre = 200;
		}
		else {
			nombre = 400;
		}
		if (niv(choix, niveau)) {
			JOptionPane.showMessageDialog(null, "Pas assez de points.\nIl faut " + nombre + " points", "Erreur", JOptionPane.ERROR_MESSAGE); 
<<<<<<< HEAD
			console.affiche(("Pas assez de points. Il faut " + nombre + " points"));
			return false;
		}
		
		if(niv2(choix, niveau)) {
			points = model.getJoueur().getPoint() - nombre;
		
		try {
			model.getQuest().changerNiv(model.getJoueur().getIdentifiant(), choix, niveau);
			model.getQuest().changerPoints(model.getJoueur().getIdentifiant(), points);
			points = 0;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} 
		}
=======
			System.out.println("Pas assez de points. Il faut " + nombre + " points");
			return false;
		}
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
		return true;
	}
	
	/**
	 * Cette méthode utilise la méthode choixQuestion de la classe ProjetModel pour pouvoir l'utiliser dans la vue
	 * @param sujet choisi pour être interrogé dessus
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
<<<<<<< HEAD
	 * Cette m�thode utilise la m�thode proposerQuestion de la classe ProjetModel qui pourra donc �tre utilis�e dans la vue
	 * @param q La question propos�e par le Joueur
	 * @param r1 La bonne r�ponse � la question
	 * @param r2 Une autre r�ponse
	 * @param r3 Une autre r�ponse
	 * @param r4 Une autre r�ponse
	 */
	public void proposeQuestion(String question, String r1, String r2, String r3, String r4) {
		model.proposerQuestion(question, r1, r2, r3, r4);
=======
	 * Cette méthode va créer la page d'introduction en utilisant les constructeurs des classes IntroConsole et VueIntro
	 * Des modifiication à la vue GUI sont faites ici
	 */
	public void PageIntro() {
			
		ProjetController ctrlIntro = new ProjetController(model);
		console = new IntroConsole(model, ctrlIntro);
		((IntroConsole)console).affiche();
		ctrlIntro.addview2(console);
		vue = new VueIntro(model, ctrlIntro);
		ctrlIntro.addview(vue);
		
		
		vue.setTitle("ProjetQCM");
		vue.setLocation(700, 50); //(horizontal, vertical)
		vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(500,300);
		vue.setVisible(true);
		vue.getContentPane().add(((VueIntro)vue).getIntro());
		
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
	}
	
	
	
	/**
	 * Cette méthode va créer la page de choix de sujet en utilisant les constructeurs des classes SujetConsole et VueSujet
	 * Des modifiication à la vue GUI sont faites ici
	 * @param identifiant qui permettra de récupérer le prénom, les points et les niveaux du joueur
	 */
	public void PageSujet(String identifiant) {
		page = "sujet";
		vue.setVisible(false);
		model.connecter(identifiant);
		ProjetController ctrlSujet = new ProjetController(model);
		console = new SujetConsole(model, ctrlSujet);
		ctrlSujet.addview2(console);
<<<<<<< HEAD
=======
		console.affche();
		
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
		vue = new VueSujet(model, ctrlSujet);
		ctrlSujet.addview(vue);
		console.affiche();
		vue.setTitle("Sujet");
		vue.setLocation(700, 50); //(horizontal, vertical)
		//vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(450,300);
		vue.setVisible(true);
		vue.getContentPane().add(((VueSujet)vue).getSujet());
	}
	
	/**
<<<<<<< HEAD
	 * Cette m�thode va cr�er la page d'affichage des questions en utilisant les constructeurs des classes QuestionConsole et VueQuestion
	 * Des modifications � la vue GUI sont faites ici
=======
	 * Cette méthode va créer la page d'affichage des questions en utilisant les constructeurs des classes QuestionConsole et VueQuestion
	 * Des modifiication à la vue GUI sont faites ici
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
	 */
	public void PageQuestions() {
		page = "question";
		model.questionSuivante(0);
<<<<<<< HEAD
		((VueSujet)vue).getBottom1().setVisible(false);
		((VueSujet)vue).getPropQuestion().setVisible(false);
		((VueSujet)vue).getQuizz().setVisible(true);
=======
		ProjetController ctrlQuestion = new ProjetController(model);
		vue = new VueQuestion(model, ctrlQuestion);
		ctrlQuestion.addview(vue);
		console = new QuestionConsole(model, ctrlQuestion);
		ctrlQuestion.addview2(console);
		
		vue.setTitle("ProjetQCM");
		vue.setLocation(700, 50); //(horizontal, vertical)
		//vue.setAlwaysOnTop(true);
		vue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vue.setBackground(Color.BLUE);
		vue.setSize(450,300);
		vue.setVisible(true);
		vue.getContentPane().add(((VueQuestion)vue).getPanel());
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
	}

	
<<<<<<< HEAD
	//Getter and Setter
	public String getPage() {
		return page;
=======
	/**
	 * Cette méthode utilise la méthode proposerQuestion de la classe ProjetModel qui pourra donc être utilisée dans la vue
	 * @param q La question proposée par le Joueur
	 * @param r1 La bonne réponse à la question
	 * @param r2 Une autre réponse
	 * @param r3 Une autre réponse
	 * @param r4 Une autre réponse
	 */
	public void proposeQuestion(String question, String r1, String r2, String r3, String r4) {
		model.proposerQuestion(question, r1, r2, r3, r4);
>>>>>>> 4068533e8064f8844ff202649bc470458a19bb12
	}
	
}
