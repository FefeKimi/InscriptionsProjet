package dialogueUtilisateur;

import utilitaires.ligneDeCommande.Liste;
import utilitaires.ligneDeCommande.Option;
import utilitaires.ligneDeCommande.Menu;
import utilitaires.ligneDeCommande.Action;

public class MenuDialogue {
	public static void main(String[]args){
		/*Création du menu principal*/
		Menu menuPrincipal = new Menu("Menu principal");

		/*Candidats*/
		Menu menuCandidats = new Menu("Menu Candidats","a");
		menuPrincipal.ajoute(menuCandidats);
		
		menuCandidats.ajoute(new Option("Supprimer un candidat","c",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuCandidats.ajouteRevenir("r");
		
		/*Compétitions*/
		Menu menuCompetition = new Menu("Menu Competition","b");
		menuPrincipal.ajoute(menuCompetition);
		menuCompetition.ajoute(new Option("Inscrire un candidat","a",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuCompetition.ajoute(new Option("Inscrire une equipe","b",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuCompetition.ajoute(new Option("Supprimer un candidat de la compétition","c",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuCompetition.ajouteRevenir("r");
		
		/*Equipes*/
		Menu menuEquipe = new Menu("Menu Equipe","c");
		menuPrincipal.ajoute(menuEquipe);
		
		menuEquipe.ajoute(new Option("Ajouter un membre","b",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuEquipe.ajoute(new Option("Supprimer un membre","c",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		
		/*Inscriptions*/
		Menu menuInscription = new Menu("Menu Inscription","d");
		menuPrincipal.ajoute(menuInscription);
		menuInscription.ajoute(new Option("Visualiser les candidats","a",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuInscription.ajoute(new Option("Visualiser les competitions","b",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuInscription.ajoute(new Option("Visualiser les personnes","c",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuInscription.ajoute(new Option("Visualiser les inscriptions","d",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuInscription.ajoute(new Option("Creer une competition","e",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuInscription.ajoute(new Option("Creer une equipe","f",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuInscription.ajoute(new Option("Creer une personne","g",new Action() {
			public void optionSelectionnee()
			{
			}
		}));

		/*Personnes*/
		Menu menuPersonne = new Menu("Menu Personne","e");
		menuPrincipal.ajoute(menuPersonne);
		menuPersonne.ajoute(new Option("Informations sur la personne","a",new Action() {
			public void optionSelectionnee()
			{
			}
		}));
		menuPrincipal.ajouteQuitter("q");
		
		menuPrincipal.start();

	}
	
}
