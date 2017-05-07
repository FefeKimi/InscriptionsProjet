package inscriptions;
import src.Connect;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Point d'entrée dans l'application, un seul objet de type Inscription
 * permet de gérer les compétitions, candidats (de type equipe ou personne)
 * ainsi que d'inscrire des candidats à des compétition.
 */

public class Inscriptions implements Serializable
{
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	private Connect connect;
	private SortedSet<Competition> competitions = null;
	private SortedSet<Candidat> candidats = null;
	//Doit faire manip avec base
	public static boolean SERIALIZE = true; 
	
	
	private Inscriptions()
	{
	}
	
	/**
	 * Retourne les compétitions.
	 * @return
	 * @throws SQLException 
	 */
	
	public SortedSet<Competition> getCompetitions() throws SQLException
	{
		//if (!SERIALIZE)
		if(competitions == null){
			this.openConnection();
			competitions = connect.getCompetitions();
			this.closeConnection();
		}
		return Collections.unmodifiableSortedSet(competitions);
		//return connect.getCompetitions();
	}
	
	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * @return
	 * @throws SQLException 
	 */
	
	public SortedSet<Candidat> getCandidats() throws SQLException
	{
		if (candidats == null){
			this.openConnection();
			candidats = connect.getCandidats();
			this.closeConnection();
		}
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Retourne toutes les personnes.
	 * @return
	 * @throws SQLException 
	 */
	
	public SortedSet<Personne> getPersonnes() throws SQLException
	{
		/*if (candidats == null){*/
			
			SortedSet<Personne> personnes = new TreeSet<Personne>();
			for (Candidat c : getCandidats())
				if (c instanceof Personne)
					personnes.add((Personne)c);
			return Collections.unmodifiableSortedSet(personnes);
		/*}else{
			return (SortedSet<Personne>) connect.getPersonnes();
		}*/
	}

	/**
	 * Retourne toutes les équipes.
	 * @return
	 * @throws SQLException 
	 */
	
	public SortedSet<Equipe> getEquipes() throws SQLException
	{
		/*if (!SERIALIZE){*/
			SortedSet<Equipe> equipes = new TreeSet<>();
			for (Candidat c : getCandidats())
				if (c instanceof Equipe)
					equipes.add((Equipe)c);
			return Collections.unmodifiableSortedSet(equipes);
		/*}else{
			return (SortedSet<Equipe>) connect.getEquipes();
		}*/
	}

	/**
	 * Créée une compétition. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Competition}.
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 * @throws SQLException 
	 */
	
	public Competition createCompetition(int idCompetition, String nom,LocalDate dateCloture, boolean enEquipe) throws SQLException
	{
		Competition competition = new Competition(this, nom, dateCloture, enEquipe);
		competition.setIdcompetition(idCompetition);
		if(competition.getIdcompetition() == 0){
			this.openConnection();
			connect.add(competition);
			this.closeConnection();
		}
		if(competitions == null){
			competitions = new TreeSet<Competition>();
		}
		competitions.add(competition);
		return competition;
	}

	/**
	 * Créée une Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.

	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 * @throws SQLException 
	 */
	
	public Personne createPersonne(int idPersonne, String nom, String prenom, String mail) throws SQLException
	{
		Personne personne = new Personne(this, nom, prenom, mail);
		personne.setIdCandidat(idPersonne);
		if(personne.getIdCandidat() == 0){
			connect.add(personne);
			this.closeConnection();
		}
		if(candidats == null){
			candidats = new TreeSet<Candidat>();
		}
		candidats.add(personne);
		return personne;
	}
	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	
	
	/**
	 * Créée une Candidat de type équipe. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Equipe}.
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 * @throws SQLException 
	 */
	
	public Equipe createEquipe(int id, String nom) throws SQLException
	{
		Equipe equipe = new Equipe(this, nom);
		equipe.setIdCandidat(id);
		if(equipe.getIdCandidat() == 0){
			this.openConnection();
			connect.add(equipe);
			this.closeConnection();
		}
		if(candidats == null){
			candidats = new TreeSet<Candidat>();
		}
		candidats.add(equipe);
		return equipe;
	}
	
	public void remove(Competition competition)
	{
		competitions.remove(competition);
		this.openConnection();
		connect.delComp(competition.getIdcompetition());
		this.closeConnection();
	}
	
	public void remove(Candidat candidat)
	{
		candidats.remove(candidat);
		this.openConnection();
		connect.delCandidat(candidat.getIdCandidat());
		this.closeConnection();
	}
	
	/**
	 * Retourne l'unique instance de cette classe.
	 * Crée cet objet s'il n'existe déjà.
	 * @return l'unique objet de type {@link Inscriptions}.
	 */
	
	public static Inscriptions getInscriptions()
	{
		if (inscriptions == null)
		{
			/*if (SERIALIZE)
				inscriptions = readObject();*/
			//if (inscriptions == null)
				inscriptions = new Inscriptions();
			//if (!SERIALIZE)
			inscriptions.connect = new Connect();
		}
		return inscriptions;
	}

	/**
	 * Retourne un object inscriptions vide. Ne modifie pas les compétitions
	 * et candidats déjà existants.
	 */
	
	public Inscriptions reinitialiser()
	{
		inscriptions = new Inscriptions();
		return getInscriptions();
	}

	/**
	 * Efface toutes les modifications sur Inscriptions depuis la dernière sauvegarde.
	 * Ne modifie pas les compétitions et candidats déjà existants.
	 */
	
	public Inscriptions recharger()
	{
		inscriptions = null;
		return getInscriptions();
	}
	
	private static Inscriptions readObject()
	{
		ObjectInputStream ois = null;
		try
		{
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions)(ois.readObject());
		}
		catch (IOException | ClassNotFoundException e)
		{
			return null;
		}
		finally
		{
				try
				{
					if (ois != null)
						ois.close();
				} 
				catch (IOException e){}
		}	
	}
	
	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement 
	 * lors d'une exécution ultérieure du programme.
	 * @throws IOException 
	 */
	
	public void sauvegarder() throws IOException
	{
		ObjectOutputStream oos = null;
		try
		{
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		}
		catch (IOException e)
		{
			throw e;
		}
		finally
		{
			try
			{
				if (oos != null)
					oos.close();
			} 
			catch (IOException e){}
		}
	}
	
	@Override
	public String toString()
	{
		try {
			return "Candidats : " + getCandidats().toString()
				+ "\nCompetitions  " + getCompetitions().toString();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void openConnection()
	{
		connect = new Connect();
	}
	
	public void closeConnection()
	{
		connect.close();
	}
	
}
