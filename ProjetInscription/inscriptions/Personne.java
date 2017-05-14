package inscriptions;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

import src.Connect;

/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

public class Personne extends Candidat
{
	private static final long serialVersionUID = 4434646724271327254L;
	private String prenom, mail;
	private Set<Equipe> equipes;
	private Connect c;
	public static boolean SERIALIZE = true; 

	Personne(Inscriptions inscriptions, String nom, String prenom, String mail)
	{
		super(inscriptions, nom);
		this.prenom = prenom;
		this.mail = mail;
		equipes = new TreeSet<>();
	}

	/**
	 * Retourne le prénom de la personne.
	 * @return
	 * @throws SQLException 
	 */
	
	public String getPrenom() throws SQLException
	{
		if(prenom == null) {
			c = new Connect();
			Personne p = c.getPersonne(this.getIdCandidat());
			return p.getPrenom();
		}
		return prenom;
	}

	

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 * @throws SQLException 
	 */
	
	public String getMail() throws SQLException
	{
		if(mail == null) {
			c = new Connect();
			Personne p = c.getPersonne(this.getIdCandidat());
			return p.getMail();
		}
		return mail;
	}

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	
	public void setPersonne(String nom,String prenom,String mail)
	{
		c = new Connect();
		this.setNom(nom);
		this.prenom = prenom;
		this.mail = mail;
		c.setPersonne(this.getIdCandidat(),nom,prenom,mail);
	}

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 * @throws SQLException 
	 */
	
	public Set<Equipe> getEquipes() throws SQLException
	{
		if (equipes.size() == 0){
			c = new Connect();
			return c.getEquipesFromPersonne(this.getIdCandidat());
		}
		return Collections.unmodifiableSet(equipes);
	}
	
	boolean add(Equipe equipe)
	{
		return equipes.add(equipe);
	}
	
	boolean remove(Equipe equipe)
	{
		return equipes.remove(equipe);
	}
	/*
	@Override
	public void delete()
	{
		super.delete();
		for (Equipe e : equipes)
			e.remove(this);
	}*/
	
	@Override
	public String toString()
	{
		return super.toString();
	}
}
