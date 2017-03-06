package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.Month;

import com.mysql.jdbc.CallableStatement;

//CTRL + SHIFT + O pour générer les imports
public class Connect {
    public static void main(String[]args){
     Connect c = new Connect();
     LocalDate dateCloture = LocalDate.of(2017,Month.APRIL,10);
     /*LocalDate newDate = LocalDate.of(2015,Month.APRIL,25);
     c.setDateComp(newDate,2);
     */
     c.addComp("badminton", dateCloture,false);
     c.setNameComp("ping pong",1);
     //c.addPersonne("Jules","Cesar","jc@gmail.com");
     //c.setPrenomPersonne("Felana",5);
     
     System.out.println(c.getNameComp(1));
    }
    
 public static void requete(String requete) {
 
 Statement st =null;
 try {
      Class.forName("com.mysql.jdbc.Driver");
      System.out.println("Driver O.K.");

      String url = "jdbc:mysql://localhost/inscription2017?useSSL=false";
      String login= "root";
      String passwd = "";

      Connection conn = DriverManager.getConnection(url, login, passwd);
      System.out.println("Requête executée !"); 
      st=conn.createStatement();
  
      st.executeUpdate(requete);
         
    } catch (Exception e) {
      e.printStackTrace();
    }

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
 
 /*Candidat*/
 public void setNameCandidat(String prenom,int id){
	 Connect.requete("call SET_NAME_CANDIDAT('"+prenom+"','"+id+"')");
 }
 /*competition*/
 public void addComp(String nom, LocalDate dateCloture, boolean enEquipe){
	 Connect.requete("call ADD_COMP('"+nom+"','"+dateCloture+"',"+enEquipe+")");
 }
 public void setNameComp(String newName,int id){
	 Connect.requete("call SET_NAME_COMP('"+newName+"','"+id+"')");
 }
 public void setDateComp(LocalDate newDate,int id){
	 Connect.requete("call SET_DATE_COMP('"+newDate+"','"+id+"')");
 }
 public String getNameComp(int id){	 
		String resultat = Connect.readBDD("call GET_NAME_COMP('"+id+"')","NomComp");
		return resultat;
}
 /*Personne*/
 public void addPersonne(String nom,String prenom, String mail){
	 Connect.requete("call ADD_PERSONNE('"+nom+"','"+mail+"','"+prenom+"')");
 }
 public void setPrenomPersonne(String prenom,int id){
	 Connect.requete("call SET_PRENOM_PERSONNE('"+prenom+"','"+id+"')");
 }
 public void setMailPersonne(String mail,int id){
	 Connect.requete("call SET_MAIL_PERSONNE('"+mail+"','"+id+"')");
 }
 public String getPrenomPersonne(int id){	 
	String resultat = Connect.readBDD("call GET_PRENOM_PERSONNE('"+id+"')","PrenomPersonne");
	return resultat;
 }
 public String getMailPersonne(int id){	 
	String resultat = Connect.readBDD("call GET_MAIL('"+id+"')","MailPers");
	return resultat;
 }
 /*Equipe*/
 public void addEquipe(String nom){
	 Connect.requete("call ADD_EQUIPE('"+nom+"')");
 }
 
 public void addMembreEquipe(int idEquipe,int idPersonne){
	 Connect.requete("call ADD_MEMBRE('"+idEquipe+"','"+idPersonne+"')");
 }
 
}