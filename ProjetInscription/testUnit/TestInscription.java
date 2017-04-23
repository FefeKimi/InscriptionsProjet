package testUnit;

import static org.junit.Assert.*;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.SortedSet;
import java.util.TreeSet;

import junit.framework.TestCase;
import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import org.junit.Before;
import org.junit.Test;

public class TestInscription extends TestCase {
	Inscriptions i ;
	SortedSet<Candidat> candidats;
	SortedSet<Competition> compets;
	Personne p;
	LocalDate dateCloture;
	
	/*protected void setUp() throws Exception
	{
		//Inscriptions.SERIALIZE = true;
		i = Inscriptions.getInscriptions();
		//candidats = i.getCandidats();
		//compets = i.getCompetitions();
	}*/
	
	@Test
	public void testAddcompetition() throws SQLException {
		i.openConnection();
		LocalDate dateCloture = LocalDate.of(2017,Month.APRIL,10);
		Competition c = i.createCompetition("Tennis", dateCloture, false);
		assertNotNull(c);
		assertEquals(c.getNom(), "Tennis");
		assertEquals(c.getDateCloture(),dateCloture);
		assertEquals(c.estEnEquipe(), false);
		/*vérifie l'insertion a bien été effectuée*/
		i.closeConnection();
		i.openConnection();
		Inscriptions.SAVE_OBJECT = false;
		c = i.getCompetitions().last();
		assertNotNull(c);
		assertEquals(c.getNom(), "Tennis");
		assertEquals(c.getDateCloture(),dateCloture);
		assertEquals(c.estEnEquipe(), false);
		i.closeConnection();
	}  
	
	@Test
	public void testAddPersonne() throws SQLException {
		i.openConnection();
		p  = i.createPersonne("Dupuis", "Michel", "dm@gmail.com");
		assertNotNull(p);
		assertEquals(p.getNom(), "Dupuis");
		assertEquals(p.getPrenom(), "Michel");
		assertEquals(p.getMail(), "dm@gmail.com");
		/*vérifie l'insertion a bien été effectuée*/
		i.closeConnection();
		i.openConnection();
		Inscriptions.SAVE_OBJECT = false;
		p = (Personne) i.getCandidats().last();
		assertNotNull(p);
		assertEquals(p.getNom(), "Dupuis");
		assertEquals(p.getPrenom(), "Michel");
		assertEquals(p.getMail(), "dm@gmail.com");
		i.closeConnection();
	}
	
	
	@Test
	public void testAddEquipe() throws SQLException {
		i.openConnection();
		Equipe e = i.createEquipe("PSG");
		assertNotNull(e);
		assertEquals(e.getNom(), "PSG");
		i.closeConnection();
		i.openConnection();
		Inscriptions.SAVE_OBJECT = false;
		e = (Equipe) i.getCandidats().last();
		assertNotNull(e);
		assertEquals(e.getNom(), "PSG");
		i.closeConnection();
	}
	
	@Test
	public void testRemoveCompet() throws SQLException {
		i.openConnection();
		boolean suppr = false;
		LocalDate dateCloture = LocalDate.of(2017,Month.APRIL,10);
		Competition c = i.createCompetition("Volley", dateCloture, false);
		int idCompet = c.getIdcompetition();
		i.remove(c);
		Inscriptions.SAVE_OBJECT = false;
		for(Competition compet : i.getCompetitions()){
			if(compet.getIdcompetition()!= idCompet){
				suppr = true;
			}
		}
		assertEquals(suppr, true);
		i.closeConnection();
	}
	
	
	@Test
	public void testRemoveCandidat() throws SQLException {
		i.openConnection();
		boolean suppr = false;
		p  = i.createPersonne("Rose", "Lola", "rl@gmail.com");
		int idCand = p.getIdCandidat();
		i.remove(p);
		Inscriptions.SAVE_OBJECT = false;
		for(Candidat c : i.getCandidats()){
			if(c.getIdCandidat()!= idCand){
				suppr = true;
			}
		}
		assertEquals(suppr, true);
	}
	
}
