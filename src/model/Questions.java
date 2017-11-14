package model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Questions extends Observable{
	protected String question;
	protected List<String> reponses;
	protected String bonneReponse;
	protected String rep1;
	protected String rep2;
	protected String rep3;
	protected String rep4;
	
	public Questions() throws ClassNotFoundException, SQLException {
		List<String> reponses = new ArrayList<String>();
		Connexion jdbc = new Connexion();
		question = jdbc.getQuestion();
		bonneReponse = jdbc.getRep1();
		reponses.add(jdbc.getRep1());
		reponses.add(jdbc.getRep2());
		reponses.add(jdbc.getRep3());
		reponses.add(jdbc.getRep4());
		Collections.shuffle(reponses);
		rep1 = reponses.get(0);
		rep2 = reponses.get(1);
		rep3 = reponses.get(2);
		rep4 = reponses.get(3);
				
		if (rep1.equals(bonneReponse)) {
			bonneReponse = "rep1";
		}
		else if (rep2.equals(bonneReponse)) {
			bonneReponse = "rep2";
		}
		else if (rep3.equals(bonneReponse)) {
			bonneReponse = "rep3";
		}
		else {
			bonneReponse = "rep4";
		}
	}
	
	public boolean comparaison(String rep) {
		if(rep == bonneReponse) {
			//setChanged();
			notifyObservers();
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		 return ("Question : " + this.question + ":\n1)" + rep1 + "\n2)" + rep2 + "\n3)" + rep3 + "\n4)" + rep4);
	}
}
