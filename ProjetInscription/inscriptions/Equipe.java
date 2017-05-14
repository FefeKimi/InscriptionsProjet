package inscriptions;

import java.sql.SQLException;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import src.Connect;

/**
 * Représente une Equipe. C'est-à-dire un ensemble de personnes pouvant 
 * s'inscrire à une compétition.
 * 
 */

public class Equipe extends Candidat
{
	private static final long serialVersionUID = 4147819927233466035L;
	private SortedSet<Personne> membres = new TreeSet<>();
	public static boolean SERIALIZE = false;
	private Connect connect;

	Equipe(Inscriptions inscriptions, String nom)
	{
		super(inscriptions, nom);
	}

	/**
	 * Retourne l'ensemble des personnes formant l'équipe.
	 * @throws SQLException 
	 */
	
	public SortedSet<Personne> getMembres() throws SQLException
	{
		if (membres.size() == 0){
			connect = new Connect();
			membres = connect.getMembreEquipe(
					this.getIdCandidat());
			connect.close();
		}
		return Collections.unmodifiableSortedSet(membres);
	}
	
	/**
	 * Ajoute une personne dans l'équipe.
	 * @param membre
	 * @return
	 * @throws SQLException 
	 */

	public boolean add(Personne membre) throws SQLException
	{
		connect = new Connect();
		membre.add(this);
		connect.addMembreEquipe(this.getIdCandidat(),membre.getIdCandidat());
		return membres.add(membre);
	}

	/**
	 * Supprime une personne de l'équipe. 
	 * @param membre
	 * @return
	 */
	
	public boolean remove(Personne membre)
	{
		membre.remove(this);
		connect = new Connect();
		connect.delMembreEquipe(this.getIdCandidat(),membre.getIdCandidat());
		connect.close();
		return membres.remove(membre);
	}

	/*@Override
	public void delete()
	{
		if (!SERIALIZE)
			super.delete();
		c.delCandidat(this.getIdCandidat());
	}*/
	
	@Override
	public String toString()
	{
		return super.toString();
	}
}
