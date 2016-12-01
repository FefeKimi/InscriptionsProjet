package testUnit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.TestCase;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

import org.junit.Before;
import org.junit.Test;

public class TestInscription extends TestCase {
	Inscriptions i ;
	SortedSet<Candidat> cand;
	SortedSet<Competition> compet;

	
	protected void setUp() throws Exception
	{
		i = Inscriptions.getInscriptions();
		cand = i.getCandidats();
		compet = i.getCompetitions();
	}
	
	@Test
	public void testGetCandidats() {
		assertNotNull(i);
		assertEquals(i.getCandidats(),cand);
	}
	
	@Test
	public void testGetCompetitions() {
		assertNotNull(i);
		assertEquals(i.getCompetitions(),compet);
	}

	@Test
	public void testGetInscription() {
		assertNotNull(i);
		assertEquals(Inscriptions.getInscriptions(),i);
	}
	
	@Test
	public void testGetEquipes() {
		SortedSet<Equipe> equipes = new TreeSet<>();
		equipes = i.getEquipes();
		assertNotNull(i);
		assertEquals(i.getEquipes(),equipes);
	}
	
	@Test
	public void testRecharger() {
		i.recharger();
	}
	
	@Test
	public void testReinit() {
		i.reinitialiser();
		assertNotEquals(Inscriptions.getInscriptions(),i);
	}
	
	@Test
	public void testSauvegarder() throws IOException {
		i.sauvegarder();
	}

}
