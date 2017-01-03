package testUnit;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

public class TestCompetitions {
	Equipe e;
	Personne p;
	Inscriptions i = Inscriptions.getInscriptions();
	Competition compet = i.createCompetition("Tennis",LocalDate.of(2017,Month.APRIL,14),true);
	
	protected void setUp() throws Exception
	{
		i = Inscriptions.getInscriptions();
		compet = i.createCompetition("Tennis",null,true);
		p = i.createPersonne("Julien","Jul","j@gmail.com");
	}
	
	@Test
	public void testAddPersonne() {
		assert(compet.add(p));
	}

	@Test
	public void testAddEquipe() {
		assert(compet.add(e));
	}
	
	@Test
	public void testCompareTo() {
		Competition compet2 = i.createCompetition("golf", null, false);
		assertNotNull(compet.compareTo(compet2));
	}

	@Test
	public void testDelete() {
		compet.delete();
	}
	
	@Test
	public void estEnEquipe() {
		assert(compet.estEnEquipe());
	}
	
	@Test
	public void testGetCandidats() {
		assertNotNull(compet.getCandidats());
	}
	
	@Test
	public void testGetDateCloture() {
		assertNotNull(compet.getDateCloture());
	}
	
	@Test
	public void testGetNom() {
		assertNotNull(compet.getNom());
	}
	
	@Test
	public void testInscriptionsOuvertes() {
		assert(compet.inscriptionsOuvertes());
	}
	
	@Test
	public void testRemove() {
		assert(compet.remove(e));
	}
	
	@Test
	public void testSetDateCloture() {
		compet.setDateCloture(LocalDate.of(2017,Month.APRIL,10));
		assertEquals(compet.getDateCloture(),LocalDate.of(2017,Month.APRIL,10));
	}
	
	@Test
	public void testSetNom() {
		String ancienNom = compet.getNom();
		
		compet.setNom("Basket");
		
		assertNotEquals(compet.getNom(),ancienNom);
	}
	
	
	
}
