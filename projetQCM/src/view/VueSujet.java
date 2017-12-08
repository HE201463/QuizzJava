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
import javax.swing.JFrame;
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
	private JFrame pageSujet;
	private JButton propQuestion;
	private JButton addQuestion;
	private JButton niveau1;
	private JButton niveau2;
	private JButton niveau3;
	private Box bottom2;
	private Box proposeQuestion;
	private Box ajouterQuestion;
	private Box bottom1;
	private JButton valider;
	private JButton retour;
	private JButton supprimer;
	private JButton ajouter;
	private JTextField quest;
	private JTextField rep11;
	private JTextField rep22;
	private JTextField rep33;
	private JTextField rep44;
	private JTextArea question1;
	private JTextArea reponse11;
	private JTextArea reponse12;
	private JTextArea reponse13;
	private JTextArea reponse14;
	
	private JTextArea test = new JTextArea();
	private JTextArea test1 = new JTextArea();
	private JTextArea test2 = new JTextArea();
	private JTextArea test3 = new JTextArea();
	private JTextArea test4 = new JTextArea();
	private JTextArea sujetTest = new JTextArea();
	private JTextArea niveauTest = new JTextArea();
	
	private String choix;
	
	public VueSujet(ProjetModel model, ProjetController controller) {
		super(model, controller);
		
		sujet = new JPanel();
		
		
		/*pageSujet = new JFrame();
		pageSujet.setTitle("Page des sujets");
		pageSujet.setSize(400, 300);
		pageSujet.setLocation(700, 50); //(horizontal, vertical)
		pageSujet.setAlwaysOnTop(true);
		pageSujet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pageSujet.setBackground(Color.BLUE);
		pageSujet.setVisible(true);
		/*Container contentpane = pageSujet.getContentPane();
		contentpane.add (main);*/
		Box main = Box.createVerticalBox();
		
		
		sujet.add(main);
		
		Box bottom = Box.createHorizontalBox();
		main.add(bottom);
		
		JTextField text = new JTextField (model.getJoueur().getPrenom()); 
		text.setBackground(Color.LIGHT_GRAY);
		text.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(text);
		text = new JTextField ("Point total: " + model.getJoueur().getPoint()); 
		text.setBackground(Color.GREEN);
		text.setHorizontalAlignment(JLabel.CENTER);
		bottom.add(text);
		
				
		Box niveau = Box.createVerticalBox();
		bottom.add(niveau);
		
		JTextField math = new JTextField ("niv math: " + model.getJoueur().getNivMath());
		niveau.add(math);
		
		JTextField info = new JTextField ("niv info: " + model.getJoueur().getNivInfo());
		niveau.add(info);
		
		JTextField elec = new JTextField ("niv elec: " + model.getJoueur().getNivElec());
		niveau.add(elec);
		
		propQuestion = new JButton("Proposer une question");
		main.add(propQuestion);

		addQuestion = new JButton("Voir les questions proposées");
		if(model.getJoueur().getIdentifiant().equals("deMahieu")||model.getJoueur().getIdentifiant().equals("Goossens")) {
			main.add(addQuestion);
		}
		
		bottom1 = Box.createHorizontalBox(); 
		main.add(bottom1);
		
		proposeQuestion = Box.createVerticalBox();
		main.add(proposeQuestion);
		proposeQuestion.setVisible(false);
		
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
		Box niv1 = Box.createHorizontalBox(); 
		ajouterQuestion.add(niv1);
		Box b = Box.createHorizontalBox(); 
		ajouterQuestion.add(b);
		
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
		b.add(retour);
		supprimer = new JButton("Supprimer");
		b.add(supprimer);
		ajouter = new JButton("Ajouter");
		b.add(ajouter);
		
		
		JLabel qProposee = new JLabel("Question proposée ");
		q.add(qProposee);
		test.setBackground(Color.RED);
		q.add(test);
		question1 = new JTextArea();
		
		
		JLabel r11 = new JLabel("Bonne réponse ");
		r1.add(r11);
		test1.setBackground(Color.RED);
		r1.add(test1);
		JLabel r22 = new JLabel("Autre réponse ");
		r2.add(r22);
		test2.setBackground(Color.RED);
		r2.add(test2);
		JLabel r33 = new JLabel("Autre réponse ");
		r3.add(r33);
		test3.setBackground(Color.RED);
		r3.add(test3);
		JLabel r44 = new JLabel("Autre réponse ");
		r4.add(r44);
		test4.setBackground(Color.RED);
		r4.add(test4);
		JLabel sujet = new JLabel("Sujet : ");
		suj.add(sujet);
		suj.add(sujetTest);
		JLabel niv = new JLabel("Niveau : ");
		niv1.add(niv);
		niv1.add(niveauTest);
		
		JLabel texte = new JLabel("Question proposÃ©e ");
		question.add(texte);
		quest = new JTextField("");
		question.add(quest);
		
		JLabel rep1 = new JLabel("Bonne rÃ©ponse ");
		reponse1.add(rep1);
		rep11 = new JTextField("");
		reponse1.add(rep11);
		JLabel rep2 = new JLabel("Autre rÃ©ponse ");
		reponse2.add(rep2);
		rep22 = new JTextField("");
		reponse2.add(rep22);
		JLabel rep3 = new JLabel("Autre rÃ©ponse ");
		reponse3.add(rep3);
		rep33 = new JTextField("");
		reponse3.add(rep33);
		JLabel rep4 = new JLabel("Autre rÃ©ponse ");
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
		
		niveau1.addActionListener(this);
		niveau2.addActionListener(this);
		niveau3.addActionListener(this);
		propQuestion.addActionListener(this);
		addQuestion.addActionListener(this);
		valider.addActionListener(this);
		retour.addActionListener(this);
		supprimer.addActionListener(this);
		ajouter.addActionListener(this);
		
	}
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == niveau1) {
			controller.choixQuestion(choix, 1);
			controller.PageQuestions();
		}
		if(e.getSource() == niveau2) {
			controller.choixQuestion(choix, 2);
			controller.PageQuestions();
		}
		if(e.getSource() == niveau2) {
			controller.choixQuestion(choix, 3);
			controller.PageQuestions();
		}
		if(e.getSource() == propQuestion) {
			proposeQuestion.setVisible(true);
			bottom1.setVisible(false);
			bottom2.setVisible(false);
		}
		if(e.getSource() == addQuestion) {
			ajouterQuestion.setVisible(true);
			bottom1.setVisible(false);
			bottom2.setVisible(false);
			if(controller.showProposition().size()!=0) {
				test.setText(controller.showProposition().get(0));
				test1.setText(controller.showProposition().get(1));
				test2.setText(controller.showProposition().get(2));
				test3.setText(controller.showProposition().get(3));
				test4.setText(controller.showProposition().get(4));
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
		}
		if (e.getSource() == valider) {
			/*System.out.println(quest.getText());
			System.out.println(rep11.getText());
			System.out.println(rep22.getText());
			System.out.println(rep33.getText());
			System.out.println(rep44.getText());*/
			controller.proposeQuestion(quest.getText(), rep11.getText(), rep22.getText(), rep33.getText(), rep44.getText());
			proposeQuestion.setVisible(false);
			ajouterQuestion.setVisible(false);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
		}
		if(e.getSource() == supprimer) {
			controller.deleteProposition(test.getText(), test1.getText());
			if(controller.showProposition().size()!=0) {
				test.setText(controller.showProposition().get(0));
				test1.setText(controller.showProposition().get(1));
				test2.setText(controller.showProposition().get(2));
				test3.setText(controller.showProposition().get(3));
				test4.setText(controller.showProposition().get(4));
			} else {
				proposeQuestion.setVisible(false);
				ajouterQuestion.setVisible(false);
				bottom1.setVisible(true);
				bottom2.setVisible(false);
			}
		}
		if(e.getSource() == ajouter) {
			controller.addProposition(test.getText(), test1.getText(), test2.getText(), test3.getText(), test4.getText(), sujetTest.getText(), Integer.parseInt(niveauTest.getText()));
			controller.deleteProposition(test.getText(), test1.getText());
			if(controller.showProposition().size()!=0) {
				test.setText(controller.showProposition().get(0));
				test1.setText(controller.showProposition().get(1));
				test2.setText(controller.showProposition().get(2));
				test3.setText(controller.showProposition().get(3));
				test4.setText(controller.showProposition().get(4));
			} else {
				proposeQuestion.setVisible(false);
				ajouterQuestion.setVisible(false);
				bottom1.setVisible(true);
				bottom2.setVisible(false);
			}
		}
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		
	}



	@Override
	public void affiche(String msg) {
		
	}





	@Override
	public void itemStateChanged(ItemEvent e) {
		
		bottom2.setVisible(true);
		choix = (String) e.getItem();
		niveau1.setText(e.getItem() + " 1");
		niveau2.setText(e.getItem() + " 2");
		niveau3.setText(e.getItem() + " 3");
	}





	@Override
	public void affiche() {		
	}
}
