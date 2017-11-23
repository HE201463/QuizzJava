package view;

import java.awt.Color;
import java.awt.Container;
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
import javax.swing.JTextField;

import controller.ProjetController;
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
@Getter
@Setter
public class VueSujet extends ProjetVue implements ActionListener, ItemListener{
	
	private JFrame pageSujet;
	private JButton propQuestion;
	private JButton niveau1;
	private JButton niveau2;
	private JButton niveau3;
	private Box bottom2;
	private Box proposeQuestion;
	private Box bottom1;
	private JButton valider;
	private JButton retour;
	private JTextField quest;
	private JTextField rep11;
	private JTextField rep22;
	private JTextField rep33;
	private JTextField rep44;
	
	public VueSujet(ProjetModel model, ProjetController controller) {
		super(model, controller);
		pageSujet = new JFrame();
		pageSujet.setTitle("Page des sujets");
		pageSujet.setSize(400, 300);
		pageSujet.setLocation(700, 50); //(horizontal, vertical)
		pageSujet.setAlwaysOnTop(true);
		pageSujet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pageSujet.setBackground(Color.BLUE);
		pageSujet.setVisible(true);
		
		Box main = Box.createVerticalBox();
		Container contentpane = pageSujet.getContentPane();
		contentpane.add (main);
		
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
		combo.addItem("INFO");
		combo.addItem("MATH");
		combo.addItem("ELEC");
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
		propQuestion.addActionListener(this);
		valider.addActionListener(this);
		retour.addActionListener(this);
		
	}
	
	



	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == niveau1) {
			pageSujet.setVisible(true);
			controller.PageQuestions();
		}
		if(e.getSource() == propQuestion) {
			proposeQuestion.setVisible(true);
			bottom1.setVisible(false);
			bottom2.setVisible(false);
		}
		if (e.getSource() == retour) {
			proposeQuestion.setVisible(false);
			bottom1.setVisible(true);
			bottom2.setVisible(false);
		}
		if (e.getSource() == valider) {
			System.out.println(quest.getText());
			System.out.println(rep11.getText());
			System.out.println(rep22.getText());
			System.out.println(rep33.getText());
			System.out.println(rep44.getText());
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
		niveau1.setText(e.getItem() + " 1");
		niveau2.setText(e.getItem() + " 2");
		niveau3.setText(e.getItem() + " 3");
	}
}
