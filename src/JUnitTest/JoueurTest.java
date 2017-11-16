package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Joueur;

class JoueurTest {
	
	@Test
	void testToString() {
		Joueur Jon = new Joueur("JonGoo","Jonathan");
		assertEquals("Pseudo: JonGoo\nPrénom: Jonathan\nPoint: 0\nLevel: 1", Jon.toString());	
	}
	@Test
	void testVerifJoueur() {
		Joueur Jon = new Joueur("JonGoo","Jonathan");
		assertFalse(Jon.verifJoueur());
	}
	
	
}
