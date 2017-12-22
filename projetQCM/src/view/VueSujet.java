package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import model.ProjetModel;

public class VueSujet extends ProjetVue implements ActionListener, ItemListener{
	
	private JPanel sujet;
	private JButton niveau1;
	private JButton niveau2;
	private JButton niveau3;
	private Box bottom2;
	private Box bottom1;
	private Box middle;
	
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
	private JButton retourSujet;
	private JTextField addQuestion;
	private JTextField addRep1;
	private JTextField addRep2;
	private JTextField addRep3;
	private JTextField addRep4;
	private JTextField addSujet;
	private JTextField addNiveau;
  
	
		
	private JTextField textPoints;
	private JTextField math;
	private JTextField info;
	private JTextField elec;
	
	private String choix; //choix du sujet
	
	//La box, les 4 boutons réponses possibles + le JTextField de la question pour le quizz
	private Box quizz;
	private JTextArea textQuest;
	private JButton quizzReponse1;
	private JButton quizzReponse2;
	private JButton quizzReponse3;
	private JButton quizzReponse4;

	private JButton chrono;
	private JTextField textChrono;
	
	public VueSujet(ProjetModel model, ProjetController controller) {
		super(model, controller);
		
		sujet = new JPanel();
		
		Box main = Box.createVerticalBox();
		
		sujet.add(main);
		
		Font f = new Font("Serif", Font.PLAIN, 20);
		Font g = new Font("Serif", Font.PLAIN, 16);
		
		Color beige = new Color(245,245,220);
		Color transparent = new Color(0,0,0,0);
		Color AntiqueWhite3 = new Color(205,192,176);
		
		sujet.setBackground(beige);
		
		
		Box bottom = Box.createHorizontalBox();
		main.add(bottom);
		
		JTextField textPrenom = new JTextField (model.getJoueur().getPrenom()); 
		textPrenom.setBackground(AntiqueWhite3);
		textPrenom.setFont(f);
		textPrenom.setEditable (false);
		textPrenom.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(textPrenom);
		textPoints = new JTextField ("Point total : " + model.getJoueur().getPoint()); 
		textPoints.setBackground(AntiqueWhite3);
		textPoints.setFont(g);
		textPoints.setEditable (false);
		textPoints.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(textPoints);
				
		Box niveau = Box.createVerticalBox();
		bottom.add(niveau);
		
		math = new JTextField ("niv math : " + model.getJoueur().getNivMath());
		math.setBackground(AntiqueWhite3);
		math.setFont(g);
		math.setEditable (false);
		niveau.add(math);
		
		info = new JTextField ("niv info : " + model.getJoueur().getNivInfo());
		info.setBackground(AntiqueWhite3);
		info.setFont(g);
		info.setEditable (false);
		niveau.add(info);
		
		elec = new JTextField ("niv elec : " + model.getJoueur().getNivElec());
		elec.setBackground(AntiqueWhite3);
		elec.setFont(g);
		elec.setEditable (false);
		niveau.add(elec);
		
		JPanel espace = new JPanel();
		espace.setPreferredSize(new Dimension(300,10));
		espace.setBackground(transparent);
		main.add(espace);
		
		middle = Box.createHorizontalBox();
		main.add(middle);
		propQuestion = new JButton("Proposer une question");
		propQuestion.setBackground(AntiqueWhite3);
		middle.add(propQuestion);
		ajoutQuestion = new JButton("Voir les questions proposées");
		ajoutQuestion.setBackground(AntiqueWhite3);
		if(model.getJoueur().getIdentifiant().equals("deMahieu")||model.getJoueur().getIdentifiant().equals("Goossens")) {
			middle.add(ajoutQuestion);
		}
		
		JPanel espace4 = new JPanel();
		espace4.setPreferredSize(new Dimension(300,20));
		espace4.setBackground(transparent);
		main.add(espace4);
		
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
		
		JPanel espace6 = new JPanel();
		espace6.setPreferredSize(new Dimension(300,10));
		espace6.setBackground(transparent);
		proposeQuestion.add(espace6);
		
		Box bouton = Box.createHorizontalBox(); 
		proposeQuestion.add(bouton);
		
		valider = new JButton("Valider");
		valider.setBackground(AntiqueWhite3);
		bouton.add(valider);
		retour = new JButton("retour");
		retour.setBackground(AntiqueWhite3);
		bouton.add(retour);
		
		
		JLabel texte = new JLabel("Question proposée : ");
		texte.setFont(g);
		question.add(texte);
		propQuest = new JTextField("");
		propQuest.setBackground(AntiqueWhite3);
		question.add(propQuest);

		JLabel rep1 = new JLabel("Bonne réponse : ");
		rep1.setFont(g);
		reponse1.add(rep1);
		propRep1 = new JTextField("");
		propRep1.setBackground(AntiqueWhite3);
		reponse1.add(propRep1);
		JLabel rep2 = new JLabel("Autre réponse : ");
		rep2.setFont(g);
		reponse2.add(rep2);
		propRep2 = new JTextField("");
		propRep2.setBackground(AntiqueWhite3);
		reponse2.add(propRep2);
		JLabel rep3 = new JLabel("Autre réponse : ");
		rep3.setFont(g);
		reponse3.add(rep3);
		propRep3 = new JTextField("");
		propRep3.setBackground(AntiqueWhite3);
		reponse3.add(propRep3);
		JLabel rep4 = new JLabel("Autre réponse : ");
		rep4.setFont(g);
		reponse4.add(rep4);
		propRep4 = new JTextField("");
		propRep4.setBackground(AntiqueWhite3);
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
		
		JPanel espace7 = new JPanel();
		espace7.setPreferredSize(new Dimension(300,10));
		espace7.setBackground(transparent);
		ajouterQuestion.add(espace7);
		
		Box b = Box.createHorizontalBox(); 
		ajouterQuestion.add(b);
		retourSujet = new JButton("Retour");
		retourSujet.setBackground(AntiqueWhite3);
		b.add(retourSujet);
		supprimer = new JButton("Supprimer");
		supprimer.setBackground(AntiqueWhite3);
		b.add(supprimer);
		ajouter = new JButton("Ajouter");
		ajouter.setBackground(AntiqueWhite3);
		b.add(ajouter);
		
		
		JLabel qProposee = new JLabel("Question proposée : ");
		q.add(qProposee);
		addQuestion = new JTextField();
		addQuestion.setBackground(AntiqueWhite3);
		q.add(addQuestion);
		
		
		JLabel r1Prop = new JLabel("Bonne réponse : ");
		r1.add(r1Prop);
		addRep1 = new JTextField();
		addRep1.setBackground(AntiqueWhite3);
		r1.add(addRep1);
		JLabel r2Prop = new JLabel("Autre réponse : ");
		r2.add(r2Prop);
		addRep2 = new JTextField();
		addRep2.setBackground(AntiqueWhite3);
		r2.add(addRep2);
		JLabel r3Prop = new JLabel("Autre réponse : ");
		r3.add(r3Prop);
		addRep3 = new JTextField();
		addRep3.setBackground(AntiqueWhite3);
		r3.add(addRep3);
		JLabel r4Prop = new JLabel("Autre réponse : ");
		r4.add(r4Prop);
		addRep4 = new JTextField();
		addRep4.setBackground(AntiqueWhite3);
		r4.add(addRep4);
		JLabel sujetProp = new JLabel("Sujet : ");
		suj.add(sujetProp);
		addSujet = new JTextField();
		addSujet.setBackground(AntiqueWhite3);
		suj.add(addSujet);
		JLabel nivProp = new JLabel("Niveau : ");
		niv.add(nivProp);
		addNiveau = new JTextField();
		addNiveau.setBackground(AntiqueWhite3);
		niv.add(addNiveau);
		
		
		
		
		JLabel label = new JLabel("Choisis un sujet: "); 
		label.setHorizontalAlignment(JLabel.RIGHT);
		label.setPreferredSize (new Dimension (200, 20));
		label.setBackground(Color.cyan);
		bottom1.add(label);
		JComboBox<String> combo = new JComboBox<String>();
		combo.setBackground(AntiqueWhite3);
		combo.addItem("*");
		combo.addItem("informatique");
		combo.addItem("mathématique");
		combo.addItem("électronique");
		bottom1.add(combo);
		combo.addItemListener(this);
		
		JPanel espace5 = new JPanel();
		espace5.setPreferredSize(new Dimension(300,10));
		espace5.setBackground(transparent);
		main.add(espace5);
		
		bottom2 = Box.createHorizontalBox(); 
		main.add(bottom2);
		bottom2.setVisible(false);
		
		
		niveau1 = new JButton ("niveau1");
		niveau1.setBackground(AntiqueWhite3);
		bottom2.add(niveau1);
		niveau2 = new JButton ("niveau2");
		niveau2.setBackground(AntiqueWhite3);
		bottom2.add(niveau2);
		niveau3 = new JButton ("niveau3");
		niveau3.setBackground(AntiqueWhite3);
		bottom2.add(niveau3);
		
		quizz = Box.createVerticalBox();
		main.add(quizz);
		quizz.setVisible(false);
		
		textQuest = new JTextArea (""); 
		textQuest.setPreferredSize (new Dimension (250, 80));
		textQuest.setEditable(false);
		textQuest.setLineWrap(true);
		textQuest.setWrapStyleWord(true);
		textQuest.setFont(g);
		textQuest.setBackground(AntiqueWhite3);
		quizz.add(textQuest);
		
		JPanel espace2 = new JPanel();
		espace2.setPreferredSize(new Dimension(300,20));
		espace2.setBackground(transparent);
		quizz.add(espace2);
		
		Box rep12 = Box.createHorizontalBox(); 
		quizz.add(rep12);
		
		JPanel espace3 = new JPanel();
		espace3.setPreferredSize(new Dimension(300,10));
		espace3.setBackground(transparent);
		quizz.add(espace3);
		
		Box rep34 = Box.createHorizontalBox(); 
		quizz.add(rep34);
		
		
		
		quizzReponse1 = new JButton ();
		quizzReponse1.setBackground(AntiqueWhite3);
		rep12.add(quizzReponse1);
		quizzReponse2 = new JButton ();
		quizzReponse2.setBackground(AntiqueWhite3);
		rep12.add(quizzReponse2);
		
		quizzReponse3 = new JButton (); 
		quizzReponse3.setBackground(AntiqueWhite3);
		rep34.add(quizzReponse3);
		quizzReponse4 = new JButton (); 
		quizzReponse4.setBackground(AntiqueWhite3);
		rep34.add(quizzReponse4);
		
		JPanel espace1 = new JPanel();
		espace1.setPreferredSize(new Dimension(300,20));
		espace1.setBackground(transparent);
		quizz.add(espace1);
		
		chrono = new JButton();
		chrono.setBackground(AntiqueWhite3);
		quizz.add(chrono);
				

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
		retourSujet.addActionListener(this);
		supprimer.addActionListener(this);
		ajouter.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == niveau1) {
			affiche();
			controller.choixQuestion(choix, 1);
			controller.PageQuestions();
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
			ajouterQuestion.setVisible(false);
			middle.setVisible(false);
			bottom1.setVisible(false);
			bottom2.setVisible(false);
			quizz.setVisible(false);
		}
		if(e.getSource() == ajoutQuestion) {
			ajouterQuestion.setVisible(true);
			proposeQuestion.setVisible(false);
			middle.setVisible(false);
			bottom1.setVisible(false);
			bottom2.setVisible(false);
			quizz.setVisible(false);
			
			if(controller.showProposition().size()!=0) {
				addQuestion.setText(controller.showProposition().get(0));
				addRep1.setText(controller.showProposition().get(1));
				addRep2.setText(controller.showProposition().get(2));
				addRep3.setText(controller.showProposition().get(3));
				addRep4.setText(controller.showProposition().get(4));
			} else {
				ajouterQuestion.setVisible(false);
				bottom1.setVisible(true);
				middle.setVisible(true);
				bottom2.setVisible(false);
			}
		}
		if (e.getSource() == retour) {
			proposeQuestion.setVisible(false);
			ajouterQuestion.setVisible(false);
			middle.setVisible(true);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
			quizz.setVisible(false);
		}
		if (e.getSource() == valider) {
			controller.proposeQuestion(propQuest.getText(), propRep1.getText(), propRep2.getText(), propRep3.getText(), propRep4.getText());
			proposeQuestion.setVisible(false);
			ajouterQuestion.setVisible(false);
			bottom1.setVisible(true);
			middle.setVisible(true);
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
				middle.setVisible(true);
				bottom2.setVisible(false);
			}
		}
		if (e.getSource() == retourSujet) {
			proposeQuestion.setVisible(false);
			ajouterQuestion.setVisible(false);
			middle.setVisible(true);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
			quizz.setVisible(false);
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
				middle.setVisible(true);
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
		middle.setVisible(false);
		quizz.setVisible(true);
	}
	
	
	
	
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

	public JButton getChrono() {
		return chrono;
	}

	public void setChrono(JButton chrono) {
		this.chrono = chrono;
	}

	public Box getMiddle() {
		return middle;
	}

	public void setMiddle(Box middle) {
		this.middle = middle;
	}

	

}
	
