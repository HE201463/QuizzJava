package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
public class VueSujet extends ProjetVue implements ActionListener, ItemListener{
	
	private JPanel sujet;
	private JButton niveau1;
	private JButton niveau2;
	private JButton niveau3;
	private Box bottom2;
	private Box bottom1;
	
	//JTextField et JButton Box utilisés pour proposer une question
	private Box proposeQuestion;
	private JTextField propQuest;
	private JTextField propRep1;
	private JTextField propRep2;
	private JTextField propRep3;
	private JTextField propRep4;
	private JButton propQuestion;
	private JButton valider;
	private JButton retour;

	//JTextField et JButton Box utilisés pour ajouter une question
	private Box ajouterQuestion;
  private JButton ajoutQuestion;
	private JButton supprimer;
	private JButton ajouter;
  private JTextArea addQuestion = new JTextArea();
	private JTextArea addRep1 = new JTextArea();
	private JTextArea addRep2 = new JTextArea();
	private JTextArea addRep3 = new JTextArea();
	private JTextArea addRep4 = new JTextArea();
	private JTextArea addSujet = new JTextArea();
	private JTextArea addNiveau = new JTextArea();
  
	
		
	private JTextField textPoints;
	private JTextField math;
	private JTextField info;
	private JTextField elec;
	
	private String choix; //choix du sujet
	
	//La box, les 4 boutons réponses possibles + le JTextField de la question pour le quizz
	private Box quizz;
	private JTextField textQuest;
	private JButton quizzReponse1;
	private JButton quizzReponse2;
	private JButton quizzReponse3;
	private JButton quizzReponse4;

	private JButton but;
	private JTextField textChrono;
	private boolean arret = false;
	private long tempsFinal;
	
	public VueSujet(ProjetModel model, ProjetController controller) {
		super(model, controller);
		
		sujet = new JPanel();
		
		Box main = Box.createVerticalBox();
		
		sujet.add(main);
		
		Box bottom = Box.createHorizontalBox();
		main.add(bottom);
		
		JTextField textPrenom = new JTextField (model.getJoueur().getPrenom()); 
		textPrenom.setBackground(Color.LIGHT_GRAY);
		textPrenom.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(textPrenom);
		textPoints = new JTextField ("Point total: " + model.getJoueur().getPoint()); 
		textPoints.setBackground(Color.GREEN);
		textPoints.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(textPoints);
		
				
		Box niveau = Box.createVerticalBox();
		bottom.add(niveau);
		
		math = new JTextField ("niv math: " + model.getJoueur().getNivMath());
		niveau.add(math);
		
		info = new JTextField ("niv info: " + model.getJoueur().getNivInfo());
		niveau.add(info);
		
		elec = new JTextField ("niv elec: " + model.getJoueur().getNivElec());
		niveau.add(elec);
		
		propQuestion = new JButton("Proposer une question");
		main.add(propQuestion);
		ajoutQuestion = new JButton("Voir les questions proposées");
		if(model.getJoueur().getIdentifiant().equals("deMahieu")||model.getJoueur().getIdentifiant().equals("Goossens")) {
			main.add(ajoutQuestion);
		}
		
		bottom1 = Box.createHorizontalBox(); 
		main.add(bottom1);
		
		proposeQuestion = Box.createVerticalBox();
		main.add(proposeQuestion);
		proposeQuestion.setVisible(false);
		
		Box question = Box.createHorizontalBox(); 
		proposeQuestion.add(question);
		
		Box reponse1 = Box.createHorizontalBox(); 
		proposeQuestion.add(reponse1);
		Box reponse2 = Box.createHorizontalBox(); 
		proposeQuestion.add(reponse2);
		Box reponse3 = Box.createHorizontalBox(); 
		proposeQuestion.add(reponse3);
		Box reponse4 = Box.createHorizontalBox(); 
		proposeQuestion.add(reponse4);
		
		Box bouton = Box.createHorizontalBox(); 
		proposeQuestion.add(bouton);
		
		valider = new JButton("Valider");
		bouton.add(valider);
		retour = new JButton("retour");
		bouton.add(retour);
		
		
		JLabel texte = new JLabel("Question proposée ");
		question.add(texte);
		propQuest = new JTextField("");
		question.add(propQuest);

		JLabel rep1 = new JLabel("Bonne réponse ");
		reponse1.add(rep1);
		propRep1 = new JTextField("");
		reponse1.add(propRep1);
		JLabel rep2 = new JLabel("Autre réponse ");
		reponse2.add(rep2);
		propRep2 = new JTextField("");
		reponse2.add(propRep2);
		JLabel rep3 = new JLabel("Autre réponse ");
		reponse3.add(rep3);
		propRep3 = new JTextField("");
		reponse3.add(propRep3);
		JLabel rep4 = new JLabel("Autre réponse ");
		reponse4.add(rep4);
		propRep4 = new JTextField("");
		reponse4.add(propRep4);
		
		
		
		ajouterQuestion = Box.createVerticalBox();
		main.add(ajouterQuestion);
		ajouterQuestion.setVisible(false);
		
		Box q = Box.createHorizontalBox(); 
		ajouterQuestion.add(q);
		
		Box r1 = Box.createHorizontalBox(); 
		ajouterQuestion.add(r1);
		Box r2 = Box.createHorizontalBox(); 
		ajouterQuestion.add(r2);
		Box r3 = Box.createHorizontalBox(); 
		ajouterQuestion.add(r3);
		Box r4 = Box.createHorizontalBox(); 
		ajouterQuestion.add(r4);
		Box suj = Box.createHorizontalBox(); 
		ajouterQuestion.add(suj);
		Box niv = Box.createHorizontalBox(); 
		ajouterQuestion.add(niv);
		
		Box b = Box.createHorizontalBox(); 
		ajouterQuestion.add(b);
		b.add(retour);
		supprimer = new JButton("Supprimer");
		b.add(supprimer);
		ajouter = new JButton("Ajouter");
		b.add(ajouter);
		
		
		JLabel qProposee = new JLabel("Question proposée ");
		q.add(qProposee);
		addQuestion.setBackground(Color.RED);
		q.add(addQuestion);
		
		
		JLabel r1Prop = new JLabel("Bonne réponse ");
		r1.add(r1Prop);
		addRep1.setBackground(Color.RED);
		r1.add(addRep1);
		JLabel r2Prop = new JLabel("Autre réponse ");
		r2.add(r2Prop);
		addRep2.setBackground(Color.RED);
		r2.add(addRep2);
		JLabel r3Prop = new JLabel("Autre réponse ");
		r3.add(r3Prop);
		addRep3.setBackground(Color.RED);
		r3.add(addRep3);
		JLabel r4Prop = new JLabel("Autre réponse ");
		r4.add(r4Prop);
		addRep4.setBackground(Color.RED);
		r4.add(addRep4);
		JLabel sujetProp = new JLabel("Sujet : ");
		suj.add(sujetProp);
		suj.add(addSujet);
		JLabel nivProp = new JLabel("Niveau : ");
		niv.add(nivProp);
		niv.add(addNiveau);
		
		JLabel label = new JLabel("Choisis un sujet: "); 
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setPreferredSize (new Dimension (200, 20));
		label.setBackground(Color.cyan);
		bottom1.add(label);
		JComboBox<String> combo = new JComboBox<String>();
		combo.addItem("*");
		combo.addItem("info");
		combo.addItem("math");
		combo.addItem("elec");
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
		
		quizz = Box.createVerticalBox();
		main.add(quizz);
		quizz.setVisible(false);
		
		textQuest = new JTextField (""); 
		textQuest.setPreferredSize (new Dimension (250, 50));
		textQuest.setBackground(Color.lightGray);
		quizz.add(textQuest);
		
		Box rep12 = Box.createHorizontalBox(); 
		quizz.add(rep12);
		Box rep34 = Box.createHorizontalBox(); 
		quizz.add(rep34);
		
		quizzReponse1 = new JButton ();
		rep12.add(quizzReponse1);
		quizzReponse2 = new JButton (); 
		rep12.add(quizzReponse2);
		
		quizzReponse3 = new JButton (); 
		rep34.add(quizzReponse3);
		quizzReponse4 = new JButton (); 
		rep34.add(quizzReponse4);
		
		textChrono = new JTextField("");
		quizz.add(textChrono);
		but = new JButton();
		quizz.add(but);
				

		quizzReponse1.addActionListener(this);
		quizzReponse2.addActionListener(this);
		quizzReponse3.addActionListener(this);
		quizzReponse4.addActionListener(this);
		
		niveau1.addActionListener(this);
		niveau2.addActionListener(this);
		niveau3.addActionListener(this);
		propQuestion.addActionListener(this);
		ajoutQuestion.addActionListener(this);
		valider.addActionListener(this);
		retour.addActionListener(this);
		supprimer.addActionListener(this);
		ajouter.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == niveau1) {
			affiche();
			controller.choixQuestion(choix, 1);
			controller.PageQuestions();
			arret = true;
			//new Thread (new Chrono()).start();
		}
		if(e.getSource() == niveau2) {
			if(controller.niveau(choix, 2)) {
				affiche();
				controller.choixQuestion(choix, 2);
				controller.PageQuestions();
			}
		}
		if(e.getSource() == niveau3) {
			if(controller.niveau(choix, 3)){
				affiche();
				controller.choixQuestion(choix, 3);
				controller.PageQuestions();
			}
		}
		if(e.getSource() == propQuestion) {
			proposeQuestion.setVisible(true);
			bottom1.setVisible(false);
			bottom2.setVisible(false);
			quizz.setVisible(false);
		}
		if(e.getSource() == ajoutQuestion) {
			ajouterQuestion.setVisible(true);
			bottom1.setVisible(false);
			bottom2.setVisible(false);
			if(controller.showProposition().size()!=0) {
				addQuestion.setText(controller.showProposition().get(0));
				addRep1.setText(controller.showProposition().get(1));
				addRep2.setText(controller.showProposition().get(2));
				addRep3.setText(controller.showProposition().get(3));
				addRep4.setText(controller.showProposition().get(4));
			} else {
				ajouterQuestion.setVisible(false);
				bottom1.setVisible(true);
				bottom2.setVisible(false);
			}
		}
		if (e.getSource() == retour) {
			proposeQuestion.setVisible(false);
			ajouterQuestion.setVisible(false);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
			quizz.setVisible(false);
		}
		if (e.getSource() == valider) {
			controller.proposeQuestion(propQuest.getText(), propRep1.getText(), propRep2.getText(), propRep3.getText(), propRep4.getText());
			proposeQuestion.setVisible(false);
			ajouterQuestion.setVisible(false);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
		}
		if(e.getSource() == supprimer) {
			controller.deleteProposition(addQuestion.getText(), addRep1.getText());
			if(controller.showProposition().size()!=0) {
				addQuestion.setText(controller.showProposition().get(0));
				addRep1.setText(controller.showProposition().get(1));
				addRep2.setText(controller.showProposition().get(2));
				addRep3.setText(controller.showProposition().get(3));
				addRep4.setText(controller.showProposition().get(4));
			} else {
				proposeQuestion.setVisible(false);
				ajouterQuestion.setVisible(false);
				bottom1.setVisible(true);
				bottom2.setVisible(false);
			}
		}
		if(e.getSource() == ajouter) {
			controller.addProposition(addQuestion.getText(), addRep1.getText(), addRep2.getText(), addRep3.getText(), addRep4.getText(), addSujet.getText(), Integer.parseInt(addNiveau.getText()));
			controller.deleteProposition(addQuestion.getText(), addRep1.getText());
			if(controller.showProposition().size()!=0) {
				addQuestion.setText(controller.showProposition().get(0));
				addRep1.setText(controller.showProposition().get(1));
				addRep2.setText(controller.showProposition().get(2));
				addRep3.setText(controller.showProposition().get(3));
				addRep4.setText(controller.showProposition().get(4));
			} else {
				proposeQuestion.setVisible(false);
				ajouterQuestion.setVisible(false);
				bottom1.setVisible(true);
				bottom2.setVisible(false);
			}
		}
		if(e.getSource() == quizzReponse1) {
			controller.verification("rep" + 1);
			controller.questionSuivante();
			controller.recommence();
		}
		else if(e.getSource() == quizzReponse2) {
			controller.verification("rep" + 2);
			controller.questionSuivante();
			controller.recommence();
		}
		else if(e.getSource() == quizzReponse3){
			controller.verification("rep" + 3);
			controller.questionSuivante();
			controller.recommence();
		}
		else if(e.getSource() == quizzReponse4){
			controller.verification("rep" + 4);
			controller.questionSuivante();
			controller.recommence();
		}
	}
	
	/*public void questionSuivante() {
		controller.questionSuivante();
	}*/

	@Override
	public void update(Observable arg0, Object arg1) {
		textPoints.setText("Point total: " + model.getJoueur().getPoint());
		math.setText("niv math: " + model.getJoueur().getNivMath());
		info.setText("niv info: " + model.getJoueur().getNivInfo());
		elec.setText("niv elec: " + model.getJoueur().getNivElec());

		textQuest.setText(model.getQuest().getQuestion());
		quizzReponse1.setText(model.getQuest().getRep1());
		quizzReponse2.setText(model.getQuest().getRep2());
		quizzReponse3.setText(model.getQuest().getRep3());
		quizzReponse4.setText(model.getQuest().getRep4());
	}

	@Override
	public void affiche(String msg) {
		textChrono.setText(msg);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getItem().equals("*")) {
			bottom2.setVisible(false);
		}
		else {
			bottom2.setVisible(true);
		}
		choix = (String) e.getItem();
		niveau1.setText(e.getItem() + " 1");
		niveau2.setText(e.getItem() + " 2");
		niveau3.setText(e.getItem() + " 3");
	}
	
	/**
	 * Cette méthode permet d'afficher l'interface vers les questions
	 */
	@Override
	public void affiche() {	
		propQuestion.setVisible(false);
		bottom1.setVisible(false);
		bottom2.setVisible(false);
		quizz.setVisible(true);
	
	// Getter and Setter
	public JPanel getSujet() {
		return sujet;
	}

	public void setSujet(JPanel sujet) {
		this.sujet = sujet;
	}

	public Box getBottom2() {
		return bottom2;
	}
	public void setBottom2(Box bottom2) {
		this.bottom2 = bottom2;
	}
	public JButton getPropQuestion() {
		return propQuestion;
	}

	public void setPropQuestion(JButton propQuestion) {
		this.propQuestion = propQuestion;
	}
	public Box getBottom1() {
		return bottom1;
	}
	public void setBottom1(Box bottom1) {
		this.bottom1 = bottom1;
	}
	public Box getQuizz() {
		return quizz;
	}
	public void setQuizz(Box quizz) {
		this.quizz = quizz;
	}
	
	public JTextField getTextPoints() {
		return textPoints;
	}
	public void setTextPoints(JTextField textPoints) {
		this.textPoints = textPoints;
	}

	public JButton getBut() {
		return but;
	}

	public void setBut(JButton but) {
		this.but = but;
	}





	public Box getQuizz() {
		return quizz;
	}





	public void setQuizz(Box quizz) {
		this.quizz = quizz;
	}
	
