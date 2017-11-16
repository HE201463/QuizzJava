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
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controller.ProjetController;
import model.DemandeQuestions;

public class ProjetVueGUI extends ProjetVue implements ActionListener{
	
	private JFrame projetJFrame;
	public JTextField text;
	private JButton rep1;
	private JButton rep2;
	private JButton rep3;
	private JButton rep4;
	
	public ProjetVueGUI(DemandeQuestions model, ProjetController controller) {
		super(model, controller);
		projetJFrame = new JFrame();
		projetJFrame.setTitle("Page des questions");
		//projetJFrame.setResizable(false); // impossible à redimensionner
		projetJFrame.setSize(400, 200);
		//projetJFrame.setLocationRelativeTo(null); Pour afficher au milieu mais mettre après setSize
		projetJFrame.setLocation(700, 50); //(horizontal, vertical)
		projetJFrame.setAlwaysOnTop(true);
		projetJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		projetJFrame.setBackground(Color.BLUE);
		projetJFrame.setVisible(true);
		
		Box main = Box.createVerticalBox();
		Container contentpane = projetJFrame.getContentPane();
		contentpane.add (main);
		
		Color c_bleu = new Color(0,191,255);
		
		JTextArea question = new JTextArea (model.getQuestion()); 
		question.setPreferredSize (new Dimension (400, 100));
		question.setBackground(c_bleu);
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
		
		rep1 = new JButton (model.getRep1());
		bottom.add(rep1);
		rep2 = new JButton (model.getRep2()); 
		bottom.add(rep2);
		
		rep3 = new JButton (model.getRep3()); 
		bottom1.add(rep3);
		rep4 = new JButton (model.getRep4()); 
		bottom1.add(rep4);
		
		rep1.addActionListener(this);
		rep2.addActionListener(this);
		rep3.addActionListener(this);
		rep4.addActionListener(this);
		
	}
	
	
	
	
	
		
	@Override
	public void update(Observable arg0, Object arg1) {
		
	}

	@Override
	public void affiche(String msg) {
		text.setText(msg);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == rep1) {
			controller.verification("rep" + 1);
		}
		else if(e.getSource() == rep2) {
			controller.verification("rep" + 2);
		}
		else if(e.getSource() == rep3){
			controller.verification("rep" + 3);
		}
		else if(e.getSource() == rep4){
			controller.verification("rep" + 4);
		}
	}

}
