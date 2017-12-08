package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ProjetController;
import lombok.Getter;
import lombok.Setter;
import model.ProjetModel;
@Getter
@Setter
public class VueQuestion extends ProjetVue implements ActionListener{
	
	private JPanel panel;
	private JTextField text;
	private JTextArea question;
	private JButton rep1;
	private JButton rep2;
	private JButton rep3;
	private JButton rep4;
		
	
	public VueQuestion(ProjetModel model, ProjetController controller) {
		super(model, controller);
		
		panel = new JPanel();
		
		Box main = Box.createVerticalBox();
		
		panel.add(main);
		
		Color c_bleu = new Color(0,191,255);
		question = new JTextArea (model.getQuest().getQuestion()); 
		question.setPreferredSize (new Dimension (400, 100));
		question.setBackground(c_bleu);
		question.setLineWrap(true);
		question.setForeground(Color.WHITE);
		question.setEditable (false); 
		main.add(question);
		
		Box bottom = Box.createHorizontalBox(); 
		main.add(bottom);
		
		Box bottom1 = Box.createHorizontalBox(); 
		main.add(bottom1);
		
		text = new JTextField (""); 
		text.setPreferredSize (new Dimension (250, 50));
		text.setBackground(Color.lightGray);
		main.add(text);
		
		rep1 = new JButton ();
		bottom.add(rep1);
		rep2 = new JButton (); 
		bottom.add(rep2);
		
		rep3 = new JButton (); 
		bottom1.add(rep3);
		rep4 = new JButton (); 
		bottom1.add(rep4);
		affiche();
		
		rep1.addActionListener(this);
		rep2.addActionListener(this);
		rep3.addActionListener(this);
		rep4.addActionListener(this);
		
	}
		
	@Override
	public void update(Observable arg0, Object arg1) {
		question.setText(model.getQuest().getQuestion());
		rep1.setText(model.getQuest().getRep1());
		rep2.setText(model.getQuest().getRep2());
		rep3.setText(model.getQuest().getRep3());
		rep4.setText(model.getQuest().getRep4());
	}

	@Override
	public void affiche(String msg) {
		text.setText(msg);
	}
	
	public void affiche() {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == rep1) {
			controller.verification("rep" + 1);
			try {
				controller.questionSuivante();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == rep2) {
			controller.verification("rep" + 2);
			try {
				controller.questionSuivante();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == rep3){
			controller.verification("rep" + 3);
			try {
				controller.questionSuivante();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource() == rep4){
			controller.verification("rep" + 4);
			try {
				controller.questionSuivante();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	
	
	
	
	
	//Getter and Setter
	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public JTextField getText() {
		return text;
	}

	public void setText(JTextField text) {
		this.text = text;
	}

	public JTextArea getQuestion() {
		return question;
	}

	public void setQuestion(JTextArea question) {
		this.question = question;
	}

	public JButton getRep1() {
		return rep1;
	}

	public void setRep1(JButton rep1) {
		this.rep1 = rep1;
	}

	public JButton getRep2() {
		return rep2;
	}

	public void setRep2(JButton rep2) {
		this.rep2 = rep2;
	}

	public JButton getRep3() {
		return rep3;
	}

	public void setRep3(JButton rep3) {
		this.rep3 = rep3;
	}

	public JButton getRep4() {
		return rep4;
	}

	public void setRep4(JButton rep4) {
		this.rep4 = rep4;
	}

	
}
