package dialogueUtilisateur;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;

public class CompetitionsOnglet extends JLayeredPane{
	
	private JComboBox competitions;
	private JTextField nomCompetition;
	private JList candidatsList;
	private JTextField date_cloture;


	public CompetitionsOnglet() {
		super();
		JLabel lblComptitions = new JLabel("Comp\u00E9titions");
		lblComptitions.setBounds(10, 11, 99, 14);
		this.add(lblComptitions);
		
		Inscriptions i = Inscriptions.getInscriptions();
		
		i.openConnection();
		SortedSet<Competition> c = null;
		try {
			c = i.getCompetitions();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		i.closeConnection();
		
		/*liste déroulante des compétitions*/
		Object[] competitionsList = c.toArray();
		competitions = new JComboBox(competitionsList);
		competitions.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				Competition c = (Competition) competitions.getSelectedItem();
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					Set<Candidat> candidats = null;
					try {
						candidats = (Set<Candidat>) c.getCandidats();
						candidatsList.setListData(candidats.toArray());
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		});
	
		
		competitions.setBounds(10, 36, 99, 20);
		this.add(competitions);
		
		/*candidat de la compétition*/
		JLabel lblCandidat = new JLabel("Candidats :");
		lblCandidat.setBounds(10, 79, 64, 14);
		this.add(lblCandidat);
		
		candidatsList = new JList();
		candidatsList.setBounds(10, 137, 166, 144);
		this.add(candidatsList);
		
		Competition premierComp = (Competition)competitionsList[0];
		try {
			candidatsList.setListData(premierComp.getCandidats().toArray());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/*Inscrire un candidat*/
		JButton inscrireCand = new JButton("Inscrire un candidat");
		inscrireCand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Competition c = (Competition)competitions.getSelectedItem();
					Set<Candidat> candidatFromComp;
					try {
						candidatFromComp = c.getCandidats();
						/*Vérifie si les inscriptions à la compétition sont complètes*/
						if(candidatFromComp.size()==2){
							JPanel myPanel = new JPanel();
							boxErreur("Vous ne pouvez plus ajouter de candidat.");
						}else{	
							Set<Candidat> candiatsNonInscript;
							try {
								candiatsNonInscript = c.getCandidatsNotSign();
								if(candiatsNonInscript.size()==0) {
									boxErreur("Aucun candidat.");
								}else {
									JList<Candidat> candidatsNonInscritList = new JList<>();
									DefaultListModel<Candidat> candidatsModel = new DefaultListModel<>();
									for(Candidat cand : candiatsNonInscript){
										candidatsModel.addElement(cand);
									}
									candidatsNonInscritList.setModel(candidatsModel);
									JPanel myPanel = new JPanel();
									myPanel.add(Box.createHorizontalStrut(15)); // a spacer
									myPanel.add(candidatsNonInscritList);
									int result = JOptionPane.showConfirmDialog(null, myPanel, "Inscrire un candidat", JOptionPane.OK_CANCEL_OPTION);
									if (result == JOptionPane.OK_OPTION) {
										Inscriptions ins = Inscriptions.getInscriptions();
										/*Inscription du candidat à la compétition*/
										int index = candidatsNonInscritList.getSelectedIndex();
										Candidat candidatselect = (Candidat) candidatsNonInscritList.getSelectedValue();
											if(candidatselect==null){
												boxErreur("Echec d'inscription. Veuillez sélectionner un candidat.");
											}else{
												if(c.estEnEquipe()==true){
													Equipe equipe = ins.createEquipe(candidatselect.getIdCandidat(),candidatselect.getNom());
													c.add(equipe);
													((DefaultListModel<Candidat>) candidatsNonInscritList.getModel()).remove(index);
												}else {
													Personne p = candidatselect.getPersonne() ;
													c.add(p);
													((DefaultListModel<Candidat>) candidatsNonInscritList.getModel()).remove(index);
												}
												/*Mise à jour des candidats non inscrit*/
												Set<Candidat> candiatsNoTSign = c.getCandidatsNotSign();
												DefaultListModel<Candidat> candiatsNoTModel = new DefaultListModel<>();
												for(Candidat cand : candiatsNoTSign){
													candiatsNoTModel.addElement(cand);
												}
												candidatsNonInscritList.setModel(candiatsNoTModel);
		
												/*Mise à jour des candidats de la compétition*/
												Set<Candidat> candidats = (Set<Candidat>) c.getCandidats();
												candidatsList.setListData(candidats.toArray());
											}
									}
								}
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}	
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
			}
			
		});
			
		inscrireCand.setBounds(10, 103, 166, 23);
		add(inscrireCand);
		
		/*Désinscrire Candidat*/
		JButton btnSupprCand = new JButton("Désinscrire");
		btnSupprCand.setBounds(10, 288, 166, 23);
		add(btnSupprCand);
		btnSupprCand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Competition c = (Competition) competitions.getSelectedItem();	
				Candidat candidatselect = (Candidat) candidatsList.getSelectedValue();
				if(candidatselect==null){
					boxErreur("Veuillez sélectionner un candidat");
				}else {
					//Candidat candidatbanni = listCand.get(index);
					// TODO Candidat ne veut pas se drop tout de suite de la liste
					try {
						JPanel myPanel = new JPanel();
						myPanel.add(Box.createHorizontalStrut(1)); // a spacer
						JLabel noadd = new JLabel("Êtes-vous sûr(e) de vouloir désinscrire ce candidat?");
					    myPanel.add(noadd);
					    int result = JOptionPane.showConfirmDialog(null, myPanel, "Alert", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							c.remove(candidatselect);
							/*Mise à jour*/
							Set<Candidat> candidats = (Set<Candidat>) c.getCandidats();
							candidatsList.setListData(candidats.toArray());
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		/*MODIFIER UNE COMPETITION*/
		JButton btnModifier= new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Competition c = (Competition)competitions.getSelectedItem();
				JTextField nomField = new JTextField(c.getNom());
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
				String formattedString = c.getDateCloture().format(formatter);
			    JTextField dateField = new JTextField(formattedString);
			    
			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Nom:"));
			    myPanel.add(nomField);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Date de cloture:"));
			    myPanel.add(dateField);
			    
			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Modifier la compétition", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) {
			    	String newName = nomField.getText();
					String dateClot = dateField.getText();
					if(newName.length()==0 || dateClot.length()==0){
						boxErreur("Échec de modification. Vous devez remplir tous les champs.");
					}else {
						try {
							LocalDate date_cloture = LocalDate.parse(dateClot, formatter);
							c.setCompetition(newName,date_cloture);
							/*TODO Mise à jour Jcombox*/
						}catch(Exception e1){
							boxErreur("Échec de modification. Vous n'avez pas respecté le bon format de la date (jj/mm/aaaa)");
						}
					}
			      }

			}
		});
		btnModifier.setBounds(139, 36, 100, 32);
		this.add(btnModifier);
		btnModifier.setSize(100,20);
		
		/*SUPPRIMER UNE COMPETITION*/
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel myPanel = new JPanel();
				myPanel.add(Box.createHorizontalStrut(1)); // a spacer
				JLabel noadd = new JLabel("Êtes-vous sûr(e) de vouloir supprimer la compétition ?");
			    myPanel.add(noadd);
			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Alert", JOptionPane.OK_CANCEL_OPTION);
				if (result == JOptionPane.OK_OPTION) {
					Competition c = (Competition)competitions.getSelectedItem();
					Inscriptions i = Inscriptions.getInscriptions();
					i.openConnection();
					i.remove(c);
					competitions.removeItem(c);
					i.closeConnection();
				}
			}
		});
		btnSupprimer.setBounds(249, 36, 100, 20);
		btnSupprimer.setSize(100,20);
		this.add(btnSupprimer);
		
		/*NOUVELLE COMPETITION*/
		
		/*title*/
		JLabel lblCrerUneComptition = new JLabel("Nouvelle comp\u00E9tition");
		lblCrerUneComptition.setBackground(Color.LIGHT_GRAY);
		lblCrerUneComptition.setBounds(186, 79, 166, 14);
		this.add(lblCrerUneComptition);
		
		/*Nom de la compétition*/
		nomCompetition = new JTextField();
		nomCompetition.setBounds(279, 104, 111, 20);
		this.add(nomCompetition);
		nomCompetition.setColumns(10);
		
		JLabel nomCompetlbl = new JLabel("Nom ");
		nomCompetlbl.setForeground(Color.DARK_GRAY);
		nomCompetlbl.setBounds(231, 107, 38, 14);
		this.add(nomCompetlbl);
		
		/*Date cloture*/
		JLabel dateClotlbl = new JLabel("Date de cl\u00F4ture");
		dateClotlbl.setForeground(Color.DARK_GRAY);
		dateClotlbl.setBounds(181, 151, 99, 14);
		this.add(dateClotlbl);
		
		date_cloture = new JTextField();
		date_cloture.setBounds(279, 148, 111, 20);
		add(date_cloture);
		date_cloture.setColumns(10);
		
		/*En Equipe*/
		final JCheckBox rdbtnEnEq = new JCheckBox("En Equipe");
		rdbtnEnEq.setSelected(false);
		rdbtnEnEq.setBackground(Color.WHITE);
		rdbtnEnEq.setForeground(Color.BLACK);
		rdbtnEnEq.setBounds(279, 187, 111, 23);
		this.add(rdbtnEnEq);
		
		
		/*Ajouter une compétition*/
		JButton btnAjouterCompetition = new JButton("Ajouter");
		btnAjouterCompetition.setBounds(279, 227, 89, 23);
		this.add(btnAjouterCompetition);
		
		JLabel lblJjmmaaaa = new JLabel("jj/mm/aaaa");
		lblJjmmaaaa.setForeground(Color.GRAY);
		lblJjmmaaaa.setBounds(181, 163, 67, 14);
		add(lblJjmmaaaa);
		btnAjouterCompetition.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*récupération des champs*/
				String nomCompet = nomCompetition.getText();
				String dateClot = date_cloture.getText();
				boolean enEquipe = rdbtnEnEq.isSelected();

				if(nomCompet.length()==0 || dateClot.length()==0){
					boxErreur("Vous devez remplir tous les champs");
				}else{
					/*conversion date*/
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
					try {
						LocalDate date_cloture_convert = LocalDate.parse(dateClot, formatter);
						if(date_cloture_convert.isBefore(LocalDate.now())){
							boxErreur("Date incorrecte.");
						}else{
							Inscriptions insc = Inscriptions.getInscriptions();
							Competition newCompetition = insc.createCompetition(0,nomCompet,date_cloture_convert,enEquipe);
							nomCompetition.setText("");
							date_cloture.setText("");
							rdbtnEnEq.setSelected(false);
							/*Mise à jour de la liste*/
							competitions.addItem(newCompetition);
						}
					}catch(Exception e1){
						boxErreur("Vous n'avez pas respecté le bon format de la date (jj/mm/aaaa)");
					}
					
					
				}
			}
		});
		
		
		
		
		
	}
	public void boxErreur(String message){
		JPanel myPanel = new JPanel();
		myPanel.add(Box.createHorizontalStrut(1)); // a spacer
		JLabel noadd = new JLabel(message);
	    myPanel.add(noadd);
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Alert", JOptionPane.OK_CANCEL_OPTION);
	}
}
