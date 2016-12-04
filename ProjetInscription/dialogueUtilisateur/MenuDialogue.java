package dialogueUtilisateur;

import src.Connect;
import utilitaires.ligneDeCommande.Liste;
import utilitaires.ligneDeCommande.Option;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MenuDialogue {
	public static void main(String[]args){
		final Scanner sc = new Scanner(System.in);
		
		/*Création du menu principal*/
		Menu menuPrincipal = new Menu("Menu principal");

		/*Candidats*/
		Menu menuCandidats = new Menu("Menu Candidats","a");
		menuPrincipal.ajoute(menuCandidats);
		menuCandidats.ajoute(new Option("Visualiser les candidats","a",new Action() {
			public void optionSelectionnee()
			{
				Connect.readBDD("call GET_CANDIDAT()","NumCandidat","NomCandidat");
			}
		}));
		menuCandidats.ajoute(new Option("Supprimer un candidat","b",new Action() {
			public void optionSelectionnee()
			{
				System.out.print("Saisir le numéro du candidat à supprimer: ");
				int id = sc.nextInt();
				Connect.requete("call DEL_CANDIDAT("+id+")");
			}
		}));
		menuCandidats.ajouteRevenir("r");
		
		/*Compétitions*/
		Menu menuCompetition = new Menu("Menu Competition","b");
		menuCompetition.ajoute(new Option("Visualiser les compétitions","a",new Action() {
			public void optionSelectionnee()
			{
				Connect.readBDD("call GET_COMP()","labelComp","NomComp");
			}
		}));
		menuCompetition.ajoute(new Option("Créer une compétition","e",new Action() {
			public void optionSelectionnee()
			{
				/*à faire*/
			}
		}));
		menuPrincipal.ajoute(menuCompetition);
		menuCompetition.ajoute(new Option("Inscrire une équipe à une compétition","b",new Action() {
			public void optionSelectionnee()
			{
				/*Fonction Pas réussie*/
			}
		}));
		menuCompetition.ajoute(new Option("Supprimer un candidat d'une compétition","c",new Action() {
			public void optionSelectionnee()
			{
				System.out.print("Saisir le label de la compéttion: ");
				int id = sc.nextInt();
				Connect.requete("call DEL_COMP("+id+")");
			}
		}));
		menuCompetition.ajouteRevenir("r");
		
		/*Equipes*/
		final Menu menuEquipes = new Menu("Menu Equipe","c");
		menuPrincipal.ajoute(menuEquipes);
		menuEquipes.ajoute(new Option("Visualiser les équipes","a",new Action() {
			public void optionSelectionnee()
			{
				Connect.readBDD("call GET_EQUIPE()","NumCandidat","NomCandidat");
			}
		}));
		menuEquipes.ajoute(new Option("Creer une équipe","f",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuEquipes.ajoute(new Option("Visualiser les membres d'une equipe","d",new Action() {
			public void optionSelectionnee()
			{
				System.out.print("Saisir le numéro de l'equipe à visualiser: ");
				InputStreamReader saisie = new InputStreamReader(System.in);
				try {
					int id =  (int) saisie.read();
					Connect.readBDD("call GET_MEMBRE_EQUIPE(id)","NumCandidat","NomCandidat");;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}));
		menuEquipes.ajoute(new Option("Supprimer un membre d'une équipe","c",new Action() {
			public void optionSelectionnee()
			{
				System.out.print("Saisir le numero de l'équipe: ");
				int idEq = sc.nextInt();
				System.out.print("Saisir le numero de la personne: ");
				int idPers = sc.nextInt();
				Connect.requete("call DEL_MEMBRE("+idEq+","+idPers+")");
			}
		}));
	
		/*Personnes*/
		Menu menuPersonne = new Menu("Menu Personne","e");
		menuPrincipal.ajoute(menuPersonne);
		menuPersonne.ajoute(new Option("Visualiser les personnes","c",new Action() {
			public void optionSelectionnee()
			{
				Connect.readBDD("call GET_PERSONNE()","NumCandidat","PrenomPersonne");
			}
		}));
		menuPersonne.ajoute(new Option("Créer une personne","g",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuPrincipal.ajouteQuitter("q");
		
		menuPrincipal.start();

	}
	
}
