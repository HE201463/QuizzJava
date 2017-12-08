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
import javax.swing.JTextField;

import controller.ProjetController;
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
@Getter
@Setter
public class VueSujet extends ProjetVue implements ActionListener, ItemListener{
	
	private JPanel sujet;
	private JButton propQuestion;
	private JButton niveau1;
	private JButton niveau2;
	private JButton niveau3;
	private Box bottom2;
	private Box proposeQuestion;
	private Box bottom1;
	private Box quizz;
	private JButton valider;
	private JButton retour;
	private JTextField quest;
	private JTextField rep11;
	private JTextField rep22;
	private JTextField rep33;
	private JTextField rep44;
	private JTextField textQuest;
	private JTextField textPoints;
	private JTextField math;
	private JTextField info;
	private JTextField elec;
	private String choix;
	private JButton quizzReponse1;
	private JButton quizzReponse2;
	private JButton quizzReponse3;
	private JButton quizzReponse4;
	
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
		quest = new JTextField("");
		question.add(quest);
		
		JLabel rep1 = new JLabel("Bonne réponse ");
		reponse1.add(rep1);
		rep11 = new JTextField("");
		reponse1.add(rep11);
		JLabel rep2 = new JLabel("Autre réponse ");
		reponse2.add(rep2);
		rep22 = new JTextField("");
		reponse2.add(rep22);
		JLabel rep3 = new JLabel("Autre réponse ");
		reponse3.add(rep3);
		rep33 = new JTextField("");
		reponse3.add(rep33);
		JLabel rep4 = new JLabel("Autre réponse ");
		reponse4.add(rep4);
		rep44 = new JTextField("");
		reponse4.add(rep44);
		
		
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
		
		quizzReponse1.addActionListener(this);
		quizzReponse2.addActionListener(this);
		quizzReponse3.addActionListener(this);
		quizzReponse4.addActionListener(this);
		
		niveau1.addActionListener(this);
		niveau2.addActionListener(this);
		niveau3.addActionListener(this);
		propQuestion.addActionListener(this);
		valider.addActionListener(this);
		retour.addActionListener(this);
		
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
			bottom1.setVisible(false);
			bottom2.setVisible(false);
			quizz.setVisible(false);
		}
		if (e.getSource() == retour) {
			proposeQuestion.setVisible(false);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
			quizz.setVisible(false);
		}
		if (e.getSource() == valider) {
			controller.proposeQuestion(quest.getText(), rep11.getText(), rep22.getText(), rep33.getText(), rep44.getText());
			proposeQuestion.setVisible(false);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
		}
		
		if(e.getSource() == quizzReponse1) {
			controller.verification("rep" + 1);
			questionSuivante();
		}
		else if(e.getSource() == quizzReponse2) {
			controller.verification("rep" + 2);
			questionSuivante();
		}
		else if(e.getSource() == quizzReponse3){
			controller.verification("rep" + 3);
			questionSuivante();
		}
		else if(e.getSource() == quizzReponse4){
			controller.verification("rep" + 4);
			questionSuivante();
		}
	}
	
	public void questionSuivante() {
		controller.questionSuivante();
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
	
}
