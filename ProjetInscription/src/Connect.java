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
     /*LocalDate dateCloture = LocalDate.of(2017,Month.APRIL,10);
     c.addComp("badminton", dateCloture,false);
     c.addPersonne("Jules","Cesar","jc@gmail.com");*/
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
 public static ResultSet readBDD(String requete,String id, String nom) {

  // Information d'accès à la base de données

  Connection cn =null;
  Statement st =null;
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
   System.out.println();
   while(rs.next()){
    System.out.print("| ");
       System.out.print(rs.getString(id));
       System.out.print(" | ");
       System.out.println(rs.getString(nom));
      } System.out.println();
   
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
  return rs;
 }
 
 /*inscriptions*/
 public void addComp(String nom, LocalDate dateCloture, boolean enEquipe){
	 Connect.requete("call ADD_COMP('"+nom+"','"+dateCloture+"',"+enEquipe+")");
 }
 public void setNameComp(String newName){
	 Connect.requete("call SET_NAME_COMP('"+newName+"')");
 }
 /*Personne*/
 public void addPersonne(String nom,String prenom, String mail){
	 Connect.requete("call ADD_PERSONNE('"+nom+"','"+mail+"','"+prenom+"')");
 }
 
 /*Equipe*/
 public void addEquipe(String nom){
	 Connect.requete("call ADD_EQUIPE('"+nom+"')");
 }
 
 public void addMembreEquipe(int idEquipe,int idPersonne){
	 Connect.requete("call ADD_MEMBRE('"+idEquipe+"','"+idPersonne+"')");
 }
 
}