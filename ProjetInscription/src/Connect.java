package src;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
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
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;

import com.mysql.jdbc.CallableStatement;
//
//CTRL + SHIFT + O pour générer les imports
public class Connect {
	
	private Connection conn;
	
//    public static void main(String[]args){
//     Connect c = new Connect();
//     LocalDate dateCloture = LocalDate.of(2017,Month.APRIL,10);
//     LocalDate newDate = LocalDate.of(2015,Month.APRIL,25);
//     //c.setDateComp(newDate,2);
//     //c.addComp("Tennis", dateCloture, false);
//     //c.addPersonne("NGUY", "Fabrice", "fabrice.nguy@gmail.com");
//     //c.addComp("basketball", dateCloture, true);
//     //c.addEquipe("Zea");
//     //System.out.println(c.getNameCandidat(3));
//     // c.addMembreEquipe(1, 3);
//     //c.addMembreEquipe(17, 18);
//     //System.out.println(c.getDateComp(1));
//     //LocalDate date = LocalDate.now();
//     //System.out.println(c.CompOuverte(1));
//     System.out.println(c.enEquipeComp(1));
//    }
    
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
 
 /*Candidat*/
// public List<Candidat>
 public void setNameCandidat(String prenom,int id){
   requete("call SET_NAME_CANDIDAT('"+prenom+"','"+id+"')");
 }
 public void delCandidat(int id){
   requete("call DEL_CANDIDAT('"+id+"')");
 }
 public String getNameCandidat(int id){
	 String resultat = Connect.readBDD("call GET_NAME_CANDIDAT('"+id+"')","NomCandidat");
	 return resultat;
}
 /*competition*/
 public SortedSet<Competition> getCompetitions(){
	 SortedSet<Competition> competitions = new SortedSet<Competition>() {
		
		@Override
		public <T> T[] toArray(T[] a) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Object[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public int size() {
			// TODO Auto-generated method stub
			return 0;
		}
		
		@Override
		public boolean retainAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean removeAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean remove(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public Iterator<Competition> iterator() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean containsAll(Collection<?> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean contains(Object o) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean addAll(Collection<? extends Competition> c) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean add(Competition e) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public SortedSet<Competition> tailSet(Competition fromElement) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public SortedSet<Competition> subSet(Competition fromElement,
				Competition toElement) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Competition last() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public SortedSet<Competition> headSet(Competition toElement) {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Competition first() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public Comparator<? super Competition> comparator() {
			// TODO Auto-generated method stub
			return null;
		}
	};
	 Connect.requete
	 return competitions;
 }
 public void add(Competition competition){
   Connect.requete("call ADD_COMP('"+competition.getNom()+"','"+
		   competition.getDateCloture()+"',"+competition.estEnEquipe()+")");
   // TODO récupérer l'ID
   // ....
   competition.setId(/* */);
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
public Boolean CompOuverte(int id){  
	    
	    Date today = Date.valueOf(LocalDate.now());
		Date dateCloture = getDateComp(id);
		
		Boolean resultat = dateCloture.after(today);
		return resultat;
}

 public Date getDateComp(int id) {

    // Information d'accès à la base de données
    Date Resultat = null;  
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

     String sql = ("call date_cloture('"+id+"')");

     // Etape 4 : exécution requête
     rs = st.executeQuery(sql);

     if(rs.first()){
        Resultat = rs.getDate("DateCloture");
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
 public boolean enEquipeComp(int id){
	   return Connect.requeteBoolean("call EN_EQUIPE_COMP('"+id+"')","EnEquipe");
 }
 public void delComp(int id){
	   Connect.requete("call DEL_COMP('"+id+"')");
 }
 /*Personne*/
 public void add(Personne p){
   Connect.requete("call ADD_PERSONNE('"+p.getNom()+
		   "','"+p.getMail()+
		   "','"+p.getPrenom()+"')");
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
 public void addEquipe(Equipe equipe){
   Connect.requete("call ADD_EQUIPE('"+equipe.nom()+"')");
 }
 
 public void addMembreEquipe(int idEquipe,int idPersonne){
   Connect.requete("call ADD_MEMBRE('"+idEquipe+"','"+idPersonne+"')");
 }
 public void delMembreEquipe(int idEquipe,int idPersonne){
	   Connect.requete("call DEL_MEMBRE('"+idEquipe+"','"+idPersonne+"')");
 }
 /*Participation*/
 public void addParticipation(int idCandidat, int idComp){
	   Connect.requete("call ADD_PARTICIPATION('"+idCandidat+"','"+idComp+"')");
 }
 public void delParticipation(int idCandidat, int idComp){
	   Connect.requete("call DEL_PARTICIPATION('"+idCandidat+"','"+idComp+"')");
} 
}