package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.CallableStatement;

//CTRL + SHIFT + O pour générer les imports
public class Connect {
    public static void main(String[]args){
    	requete("DELETE from EQUIPE WHERE NumCandidat=1");
    	//readBDD("SELECT * FROM CANDIDAT");
    
    }
	public static Statement requete(String requete) {
	
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
	return st;
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
	    	}	System.out.println();
			
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
}