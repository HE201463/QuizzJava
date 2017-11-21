package JUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Joueur;

class JoueurTest {
	
	@Test
	void testToString() {
		Joueur Jon = new Joueur("JonGoo","Jonathan");
		assertEquals("Pseudo: JonGoo\nPrénom: Jonathan\nPoint: 0\nNiveau Math: 1\nNiveau Info: 1\nNiveau Elec: 1", Jon.toString());
	}
	@Test
	void testVerifConnecter() {
		Joueur Jon = new Joueur("Jon","Jonathan");
		//Si la base de donnée est allumée sinon, il faut faire un assetFalse !
		assertTrue(Jon.verifConnecter(Jon.getPseudo(), Jon.getPrenom()));
	}
	
	
}
