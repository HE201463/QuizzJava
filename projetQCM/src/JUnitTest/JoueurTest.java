package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Joueur;
/**
 * Cette classe de test va tester la classe Joueur !
 * On test 2 méthode qui renvoie un booléen
 * Groupe 12
 * @author Jonathan Goossens 2TL2
 * @author Benoit de Mahieu 2TL2
 *
 */
class JoueurTest {
	
	/**
	 * Ce test va vérifier si la combinaison de l'identifiant et du prénom est bonne
	 * Si elle est bonne renvoie true, si fausse, renvoie false !
	 */
	@Test
	void testVerifConnecter() {
		Joueur Jo = new Joueur();
		
		Jo.enregistrer("Jo", "John");
		assertTrue(Jo.verifConnecter("Jo", "John"));
		assertFalse(Jo.verifConnecter("Jo", "Patrick"));
	}
	
	/**
	 * Vérifie si le pseudo est déjà dans la base de donnée
	 * S'il l'est renvoie true, false sinon.
	 */
	@Test
	void testVerifIdentifier() {
		Joueur Jo = new Joueur();
		Jo.enregistrer("Jo", "John");
		assertTrue(Jo.verifIdentifier("Jo"));
		assertFalse(Jo.verifIdentifier("Jean"));
	}
	
	
}
