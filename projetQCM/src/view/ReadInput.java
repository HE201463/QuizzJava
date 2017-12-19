package view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

import controller.ProjetController;
import model.ProjetModel;

public class ReadInput extends ProjetVue implements Runnable{
	
	protected Scanner sc;
	
	public ReadInput(ProjetModel model, ProjetController controller) {
		super(model, controller);
		sc = new Scanner(System.in);
	}
	
	public void run() {
		while(true){
			try{
				String c = sc.next();
				System.out.println(controller.getPage());
				if(controller.getPage().equals("intro")) {
					if(c.equals("C") || c.equals("E")) {
						intro(c);
					}
					else {
						affiche("Mauvaise Ã©criture");
					}
				}
				else if(controller.getPage().equals("sujet")) {
					if(c.equals("info") || c.equals("math") || c.equals("elec")) {
						sujet(c);
					}
					else if(c.equals("question")) {
						propQuestion();
					}
					else if(c.equals("addQuestion") && controller.showProposition().size() != 0) {
						showPropQuestion();
					} if(c.equals("addQuestion") && controller.showProposition().size() == 0) {
						affiche("Il n'y a pas de propositions de questions disponible !");
					} 
					else {
						affiche("Ce sujet n'existe pas");
					}
				}
				else if(controller.getPage().equals("question"))
					if (c.equals("1") | c.equals("2") | c.equals("3") | c.equals("4")) {
						question(c);
				}
			}
			catch(InputMismatchException e){
				affiche("Format d'input incorrect");
			}
		}
}
	/**
	 * Cette méthode permet au joueur de proposer une question en console, est appelée quand il tape question
	 */
	public void propQuestion() {
		affiche("Proposez votre question");
		sc.nextLine();
		String q = sc.nextLine();
		affiche("Tapez maintenant la bonne rÃ©ponse !");
		String r1 = sc.nextLine();
		affiche("Tapez une autre rÃ©ponse !");
		String r2 = sc.nextLine();
		affiche("Tapez une autre rÃ©ponse !");
		String r3 = sc.nextLine();
		affiche("Tapez une autre rÃ©ponse !");
		String r4 = sc.nextLine();
		affiche("Votre question a bien Ã©tÃ© envoyÃ©e ! Merci de votre participation !\n\n");
		controller.proposeQuestion(q, r1, r2, r3, r4);
		
	}
	/**
	 * Cette méthode sert à afficher les questions proposées en console
	 * Elle est appelée quand le joueur tape addQuestion
	 */
	public void showPropQuestion() {
		List<String> propositions = controller.showProposition();
		affiche("Question proposÃ©e : "+ propositions.get(0));
		affiche("La bonne rÃ©ponse : "+propositions.get(1));
		affiche("Autre rÃ©ponse : "+propositions.get(2));
		affiche("Autre rÃ©ponse : "+propositions.get(3));
		affiche("Autre rÃ©ponse : "+propositions.get(4));
		affiche("Si vous voulez ajouter la question tapez \"add\"");
		affiche("Si vous voulez supprimer la question tapez \"delete\"");
		String scan= sc.next();
		if(scan.equals("add")) {
			addQuestion(propositions);
			if(controller.showProposition().size()!=0) {
				showPropQuestion();
			} else {
				controller.retourAffiche();
			}
		}else if(scan.equals("delete")) {
			controller.deleteProposition(propositions.get(0), propositions.get(1));
			affiche("Question supprimÃ©e !");
			if(controller.showProposition().size()!=0) {
				showPropQuestion();
			} else {
				controller.retourAffiche();
			}
		}
		
	}
	/**
	 * Cette méthode sert à ajouter une question dans la BDD via la console !
	 * Elle est appelée quand le joueur tape add 
	 * @param propositions, Question proposées avec ses réponses
	 */
	public void addQuestion(List<String> propositions) {
		affiche("Tapez le sujet de la question (info, elec ou math) et le niveau de celle-ci (1, 2 ou 3)");
		String sujet = sc.next();
		int niveau = sc.nextInt();
		if(sujet.equals("info")||sujet.equals("elec")||sujet.equals("math")) {
			if(niveau==1||niveau==2||niveau==3) {
				controller.addProposition(propositions.get(0), propositions.get(1), propositions.get(2), propositions.get(3), propositions.get(4), sujet, niveau);
				controller.deleteProposition(propositions.get(0), propositions.get(1));
				
			} else {
				
				affiche("Niveau Incorrect ! Try Again");
				addQuestion(propositions);
			}
		} else {
			affiche("Sujet Incorrect ! Try Again");
			addQuestion(propositions);
		}
	}
	/**
	 * Cette méthode est appelée lorsque le joueur veut se connecter ou s'enregistrer en console
	 * Elle permet de récupérer l'identifiant et le prenom !
	 * Si le joueur tape C, c'est qu'il veut se connecter, s'il tape E, il veut s'enregistrer
	 * @param c ce paramètre sera soit C soit E en fonction de ce que le joueur aura tapé !
	 */
	public void intro(String c) {
		String identifiant = sc.next();
		String prenom = sc.next();
		if(c.equals("C")) {
			if(controller.verifConnecte(identifiant, prenom)) {
				controller.PageSujet(identifiant);
			}
		}
		else {
			if(controller.verifIdentite(identifiant)) {
				model.enregistrer(identifiant, prenom);
				controller.PageSujet(identifiant);
			}
		}
	}
	/**
	 * Cette méthode est utilisée pour le choix de sujet, lorsque le joueur tape le sujet en console avec le niveau
	 * Si le niveau est plus petit que 1 ou qu'il est plus grand que 3, le niveau n'est pas bon
	 * @param c sujet que le joueur a entré 
	 */
	public void sujet(String c) {
		int niveau = sc.nextInt();
		if(niveau <= 0 || niveau >= 4) {
			affiche("Niveau incorrect");
		}
		else {
			choix(c, niveau);
		}
	}
	/**
	 * 
	 * @param c
	 */
	public void question(String c) {
		controller.verification("rep" + c);
		try {
			controller.questionSuivante();
			controller.recommence();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	/**
	 * Cette méthode permet de rentrer dans le bon sujet de question et dans le bon niveau
	 * si je joueur à la bon niveau dans le sujet ou s'il a suffisamment de point pour l'acheter, lance les questions
	 * @param choix sujet que le joueur a choisi
	 * @param niveau Niveau des questions qui seront posées au joueur
	 */
	public void choix(String choix, int niveau) {
		if (niveau == 2) {
			if(controller.niveau(choix, 2)) {
				controller.choixQuestion(choix, niveau);
				controller.PageQuestions();
			}
		}
		else if (niveau == 3) {
			if (controller.niveau(choix, 3)) {
				controller.choixQuestion(choix, niveau);
				controller.PageQuestions();
			}
		}
		else {
			controller.choixQuestion(choix, niveau);
			controller.PageQuestions();
		}
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}
	@Override
	public void affiche(String msg) {
		System.out.println(msg);		
	}
	
	
	
	@Override
	public void affiche() {
		
	}
}
