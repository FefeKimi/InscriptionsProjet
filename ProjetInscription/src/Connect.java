package src;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.awt.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;
import java.time.chrono.ChronoLocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.mysql.jdbc.CallableStatement;
//
//CTRL + SHIFT + O pour générer les imports
public class Connect {
	
   private Connection conn;
   public static void main(String[]args) throws SQLException{ 
	   Inscriptions.SERIALIZE = true;
	   Inscriptions i = Inscriptions.getInscriptions(); ;
	   SortedSet<Competition> competitions = i.getCompetitions();
	   LocalDate dateCloture = LocalDate.of(2017,Month.APRIL,10);
	   Competition c = i.createCompetition("Tennis", dateCloture, false);
	   competitions.add(c);
	   for (Competition compet : competitions) {
		   System.out.println(compet.getNom());
	   }
    }
    
    public Connect() {
    	try {
			Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver O.K.");

        String url = "jdbc:mysql://localhost/inscription2017?useSSL=false";
        String login= "root";
        String passwd = "";

        conn = DriverManager.getConnection(url, login, passwd);
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    
    public void close()
    {
    	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
 public  void requete(String requete) {
 
 Statement st =null;
 try {
      System.out.println("Requête executée !"); 
      st=conn.createStatement();
  
      st.executeUpdate(requete);
         
    } catch (Exception e) {
      e.printStackTrace();
    }

 }
 public static boolean requeteBoolean(String requete, String nomChamp) {

	  // Information d'accès à la base de données
	  Boolean Resultat = null;  
	  Connection cn = null;
	  Statement st = null;
	  ResultSet rs = null;
	  String url = "jdbc:mysql://localhost/inscription2017?useSSL=false";
	  String login= "root";
	  String passwd = "";
	  
	  try {

	   // Etape 1 : Chargement du driver
	   Class.forName("com.mysql.jdbc.Driver");

	   // Etape 2 : récupération de la connexion
	   cn = DriverManager.getConnection(url, login, passwd);

	   // Etape 3 : Création d'un statement
	   st = cn.createStatement();

	   String sql = requete;

	   // Etape 4 : exécution requête
	   rs = st.executeQuery(sql);

	   if(rs.first()){
	      Resultat = rs.getBoolean(nomChamp);
	      } 
	   return Resultat; 
	  
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } catch (ClassNotFoundException e) {
	   e.printStackTrace();
	  } finally {
	   try {
	  
	    cn.close();
	    st.close();
	   } catch (SQLException e) {
	    e.printStackTrace();
	   }
	  }
	  return false;

	 }
 public static String readBDD(String requete, String nomChamp) {

  // Information d'accès à la base de données
  String Resultat = null;  
  Connection cn = null;
  Statement st = null;
  ResultSet rs = null;
  String url = "jdbc:mysql://localhost/inscription2017?useSSL=false";
  String login= "root";
  String passwd = "";
  
  try {

   // Etape 1 : Chargement du driver
   Class.forName("com.mysql.jdbc.Driver");

   // Etape 2 : récupération de la connexion
   cn = DriverManager.getConnection(url, login, passwd);

   // Etape 3 : Création d'un statement
   st = cn.createStatement();

   String sql = requete;

   // Etape 4 : exécution requête
   rs = st.executeQuery(sql);

   if(rs.first()){
      Resultat = rs.getString(nomChamp);
      } 
   return Resultat; 
  
  } catch (SQLException e) {
   e.printStackTrace();
  } catch (ClassNotFoundException e) {
   e.printStackTrace();
  } finally {
   try {
  
    cn.close();
    st.close();
   } catch (SQLException e) {
    e.printStackTrace();
   }
  }
  return null;

 }
 public ResultSet resultatRequete(String requete) {

	  // Information d'accès à la base de données

	  Connection cn = null;
	  Statement st = null;
	  ResultSet rs = null;
	  String url = "jdbc:mysql://localhost/inscription2017?useSSL=false";
	  String login= "root";
	  String passwd = "";
	  
	  try {

	   // Etape 1 : Chargement du driver
	   Class.forName("com.mysql.jdbc.Driver");

	   // Etape 2 : récupération de la connexion
	   cn = DriverManager.getConnection(url, login, passwd);

	   // Etape 3 : Création d'un statement
	   st = cn.createStatement();

	   String sql = requete;

	   // Etape 4 : exécution requête
	   rs = st.executeQuery(sql);


	   return rs; 
	  
	  } catch (SQLException e) {
	   e.printStackTrace();
	  } catch (ClassNotFoundException e) {
	   e.printStackTrace();
	  } 
	   
	  return null;

	 } 
 /*Candidat*/
// public List<Candidat>
 public void setNameCandidat(String prenom,int id){
   requete("call SET_NAME_CANDIDAT('"+id+"','"+prenom+"')");
 }
 public void delCandidat(int id){
   requete("call DEL_CANDIDAT('"+id+"')");
 }
 
 public SortedSet<Candidat> getCandidats() throws SQLException{
	 Inscriptions i;
	 i = Inscriptions.getInscriptions();
	 SortedSet<Candidat> candidats = new TreeSet<Candidat>();
	 ResultSet rs = resultatRequete("call GET_CANDIDATS()");
	 while(rs.next()){
		int id = rs.getInt("NumCandidat");
		String nom = rs.getString("NomCandidat");
		Boolean equipe = rs.getBoolean("Equipe");
		if(equipe){
			Equipe e = i.createEquipe(nom);
			e.setIdCandidat(id);
			candidats.add(e);
		}else {
			Personne p = i.createPersonne(nom, null, null);
			p.setIdCandidat(id);
			candidats.add(p);
		}
	 }
	 return candidats;
}
 
 /*competition*/
 public SortedSet<Competition> getCompetitions() throws SQLException {
	 Inscriptions i = Inscriptions.getInscriptions();
	 SortedSet<Competition> competitions = new TreeSet<Competition>();
	 ResultSet rs = resultatRequete("call GET_COMP()");
	 while(rs.next()){
		int id = rs.getInt("NumComp");
		String nom = rs.getString("NomComp");
		LocalDate date =rs.getDate("DateCloture").toLocalDate();
		Boolean enEquipe = rs.getBoolean("EnEquipe");
		Competition competition = i.createCompetition(nom,date, enEquipe); 
		competition.setIdcompetition(id);
		competitions.add(competition);
	 }
	 return competitions;
 }
 
 public void SetNomCompetition(int id,String nom){
	 requete("call SET_NAME_COMPETITION('"+id+"','"+nom+"')");
 }
	 
 public Competition add(Competition competition) throws SQLException{
   int idComp=0;
   requete("call ADD_COMP('"+competition.getNom()+"','"+
		   competition.getDateCloture()+"',"+competition.estEnEquipe()+")");
   ResultSet rs = resultatRequete("SELECT MAX(NumComp) AS Numcomp FROM COMPETITION");
   while(rs.next()){
	   idComp = rs.getInt("NumComp");
   }
   competition.setIdcompetition(idComp);
   return competition;
 }
 
 public void setDateComp(LocalDate newDate,int id){
   requete("call SET_DATE_CLOTURE('"+id+"','"+newDate+"')");
 }

 public void addCandCompet(Personne p,int idComp) throws SQLException{
	requete("call SET_DATE_CLOTURE('"+p.getIdCandidat()+"','"+idComp+"')");
 }
public void addCandCompet(Equipe e, int idComp) throws SQLException{
	requete("call ADD_PARTICIPATION('"+e.getIdCandidat()+"','"+idComp+"')");
}
 public boolean enEquipeComp(int id){
	   return Connect.requeteBoolean("call EN_EQUIPE_COMP('"+id+"')","EnEquipe");
 }
 public void delComp(int id){
	   requete("call DEL_COMP('"+id+"')");
 }
 /*Personne*/
 public Personne add(Personne p) throws SQLException{
	 int idCandidat=0;
	 requete("call ADD_PERSONNE('"+p.getNom()+"','"+p.getPrenom()+"','"+p.getMail()+"')");
	 ResultSet rs = resultatRequete("SELECT MAX(NumCandidat) AS NumCandidat FROM CANDIDAT");
	 while(rs.next()){
		 idCandidat = rs.getInt("NumCandidat");
	 }
	   p.setIdCandidat(idCandidat);
	   return p;
 }
 
 public Set<Personne> getPersonnes() throws SQLException{
	 Inscriptions i = Inscriptions.getInscriptions();
	 SortedSet<Personne> personnes = new TreeSet<Personne>();
	 ResultSet rs = resultatRequete("call GET_PERSONNE()");
	 while(rs.next()){
		String nom = rs.getString("NomCandidat");
		String prenom = rs.getString("Prenom");
		String mail = rs.getString("Mail");
		Personne p = i.createPersonne(nom, prenom, mail);		
		personnes.add(p);
	 }
	 return personnes;
 }
 
 public Set<Equipe> getEquipesFromPersonne(int idCandidat) throws SQLException{
	 Inscriptions i = Inscriptions.getInscriptions();
	 SortedSet<Equipe> equipes = new TreeSet<Equipe>();
	 ResultSet rs = resultatRequete("call GET_EQUIPE_FROM_PERS('"+idCandidat+"')");
	 while(rs.next()){
		String nom = rs.getString("NomCandidat");
		Equipe e = i.createEquipe(nom);
		equipes.add(e);
	 }
	 return equipes;
 }
 
 public void setPrenomPersonne(int id,String prenom){
   requete("call SET_PRENOM_PERSONNE('"+id+"','"+prenom+"')");
 }
 public void setMailPersonne(int id,String mail){
   requete("call SET_MAIL_PERSONNE('"+id+"','"+mail+"')");
 }
 
 /*Equipe*/
 public Set<Equipe> getEquipes() throws SQLException{
	 Inscriptions i = Inscriptions.getInscriptions();
	 SortedSet<Equipe> equipes = new TreeSet<Equipe>();
	 ResultSet rs = resultatRequete("call GET_EQUIPE()");
	 while(rs.next()){
		String nom = rs.getString("NomCandidat");
		Equipe e = i.createEquipe(nom);		
		equipes.add(e);
	 }
	 return equipes;
 }
 
 public Equipe add(Equipe equipe) throws SQLException{
	 int idCandidat=0;
	 requete("call ADD_EQUIPE('"+equipe.getNom()+"')");
	 ResultSet rs = resultatRequete("SELECT MAX(NumCandidat) AS NumCandidat FROM CANDIDAT");
	 while(rs.next()){
		idCandidat = rs.getInt("NumCandidat");
	 }
	 equipe.setIdCandidat(idCandidat);
	 return equipe;
 }
 public SortedSet<Personne> getMembreEquipe(String nomEquipe) throws SQLException{
	 Inscriptions i = Inscriptions.getInscriptions();
	 SortedSet<Personne> personnes = new TreeSet<Personne>();
	 ResultSet rs = resultatRequete("call GET_MEMBRE_EQUIPE('"+nomEquipe+"')");
	 while(rs.next()){
		String nom = rs.getString("NomCandidat");
		String prenom = rs.getString("Prenom");
		String mail = rs.getString("Mail");
		Personne p = i.createPersonne(nom,prenom,mail);
		personnes.add(p);
	 }
	 return personnes;
 }
 public boolean addMembreEquipe(int idEquipe,int idPersonne) throws SQLException{
	 boolean add=true;
	 int idCandidat=0;
	 requete("call ADD_MEMBRE('"+idEquipe+"','"+idPersonne+"')");
	 ResultSet rs = resultatRequete("SELECT NumCandidatPers FROM ETRE_DANS WHERE NumCandidatEquipe ='"+idEquipe+"' AND NumCandidatPers='"+idPersonne+"'");
	 while(rs.next()){
		idCandidat = rs.getInt("NumCandidat");
	 }
	 if(idCandidat==0){
		 add=false;
	 }
	 return add;
 }
 public void delMembreEquipe(int idEquipe,int idPersonne){
	   requete("call DEL_MEMBRE('"+idEquipe+"','"+idPersonne+"')");
 }
 /*Participation*/
public void addParticipation(int idCandidat, int idComp){
	   requete("call ADD_PARTICIPATION('"+idCandidat+"','"+idComp+"')");
 }
 public void delParticipation(int idCandidat, int idComp){
	   requete("call DEL_PARTICIPATION('"+idCandidat+"','"+idComp+"')");
} 
}