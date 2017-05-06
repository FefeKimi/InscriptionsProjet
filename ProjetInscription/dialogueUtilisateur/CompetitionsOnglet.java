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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
		
		/*liste d�roulante des comp�titions*/
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
	
		
		competitions.setBounds(10, 36, 76, 20);
		this.add(competitions);
		
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
						int i=0;
						for (Candidat cand : candidatFromComp){
							i++;
						}
						/*V�rifie si les inscriptions � la comp�tition sont compl�tes*/
						if(i==2){
							JPanel myPanel = new JPanel();
							boxErreur("Vous ne pouvez plus ajouter de candidat.");
						}else{	
							Set<Candidat> candiatsNonInscript;
							try {
								candiatsNonInscript = c.getCandidatsNotSign();
								if(candiatsNonInscript==null) {
									boxErreur("Aucun candidat.");
								}else {
									JList candidatsNonInscritList = new JList();
									candidatsNonInscritList.setListData(candiatsNonInscript.toArray());
									JPanel myPanel = new JPanel();
									myPanel.add(Box.createHorizontalStrut(15)); // a spacer
									myPanel.add(candidatsNonInscritList);
									int result = JOptionPane.showConfirmDialog(null, myPanel, "Inscrire un candidat", JOptionPane.OK_CANCEL_OPTION);
									if (result == JOptionPane.OK_OPTION) {
										Inscriptions ins = Inscriptions.getInscriptions();
										/*Inscription du candidat � la comp�tition*/
										ArrayList<Candidat> listCand = new ArrayList();
										for (Candidat cand : candiatsNonInscript){
											listCand.add(cand);
										}
										int index = candidatsNonInscritList.getSelectedIndex();
										Candidat candidatselect = listCand.get(index);
										if(c.estEnEquipe()==true){
											Equipe equipe = ins.createEquipe(candidatselect.getIdCandidat(),candidatselect.getNom());
											c.add(equipe);
										}else {
											Personne p = candidatselect.getPersonne() ;
											c.add(p);
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
		
		/*Bannir Candidat*/
		JButton btnSupprCand = new JButton("Bannir");
		btnSupprCand.setBounds(10, 285, 99, 23);
		add(btnSupprCand);
		btnSupprCand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inscriptions i = Inscriptions.getInscriptions();
				ArrayList<Candidat> listCand = new ArrayList();
				try {
					for (Candidat cand : i.getCandidats()){
						listCand.add(cand);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
				try {
					Competition c = (Competition) competitions.getSelectedItem();
					int index = candidatsList.getSelectedIndex();
					Candidat candidatselect = listCand.get(index);
					// TODO Candidat ne veut pas se drop tout de suite de la liste
					if (index != -1) {
						candidatsList.remove(index);
					}
					c.remove(candidatselect);
				} catch (SQLException e1) {
					e1.printStackTrace();
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
			    
			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Modifier la comp�tition", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) {
			        System.out.println("Nom: " + nomField.getText());
			        System.out.println("Date de cloture: " + dateField.getText());
					String dateClot = dateField.getText();
					LocalDate date_cloture = LocalDate.parse(dateClot, formatter);
			        c.setCompetition(nomField.getText(),date_cloture);
			      }

			}
		});
		btnModifier.setBounds(127, 35, 89, 23);
		this.add(btnModifier);
		btnModifier.setSize(100,20);
		
		/*SUPPRIMER UNE COMPETITION*/
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Competition c = (Competition)competitions.getSelectedItem();
				Inscriptions i = Inscriptions.getInscriptions();
				i.openConnection();
				i.remove(c);
				competitions.removeItem(c);
				i.closeConnection();
			}
		});
		btnSupprimer.setBounds(250, 35, 89, 23);
		btnSupprimer.setSize(100,20);
		this.add(btnSupprimer);
		
		/*FORMULAIRE DE CREATION D'UNE COMPETITION*/
		JLabel lblCrerUneComptition = new JLabel("Nouvelle comp\u00E9tition");
		lblCrerUneComptition.setBackground(Color.LIGHT_GRAY);
		lblCrerUneComptition.setBounds(199, 79, 166, 14);
		this.add(lblCrerUneComptition);
		
		JLabel nomCompetlbl = new JLabel("Nom ");
		nomCompetlbl.setForeground(Color.DARK_GRAY);
		nomCompetlbl.setBounds(231, 107, 38, 14);
		this.add(nomCompetlbl);
		
		JLabel dateClotlbl = new JLabel("Date de cl\u00F4ture");
		dateClotlbl.setForeground(Color.DARK_GRAY);
		dateClotlbl.setBounds(186, 151, 99, 14);
		this.add(dateClotlbl);
		
		
		
		JLabel enEquipelbl = new JLabel("En \u00E9quipe ?");
		enEquipelbl.setForeground(Color.DARK_GRAY);
		enEquipelbl.setBounds(186, 193, 77, 14);
		this.add(enEquipelbl);
		
		JRadioButton rdbtnOui = new JRadioButton("Oui");
		rdbtnOui.setBackground(Color.WHITE);
		rdbtnOui.setForeground(Color.BLACK);
		rdbtnOui.setBounds(277, 189, 54, 23);
		this.add(rdbtnOui);
		
		JRadioButton rdbtnNon = new JRadioButton("Non");
		rdbtnNon.setBackground(Color.WHITE);
		rdbtnNon.setForeground(Color.BLACK);
		rdbtnNon.setBounds(333, 189, 57, 23);
		this.add(rdbtnNon);
		
		JLabel lblNewLabel_3 = new JLabel("Candidats :");
		lblNewLabel_3.setBounds(10, 79, 64, 14);
		this.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Ajouter");
		btnNewButton_3.setBounds(287, 223, 89, 23);
		this.add(btnNewButton_3);
		
		nomCompetition = new JTextField();
		nomCompetition.setBounds(279, 104, 111, 20);
		this.add(nomCompetition);
		nomCompetition.setColumns(10);
		
		date_cloture = new JTextField();
		date_cloture.setBounds(279, 148, 111, 20);
		add(date_cloture);
		date_cloture.setColumns(10);
		
		
		
		
	}
	public void boxErreur(String message){
		JPanel myPanel = new JPanel();
		myPanel.add(Box.createHorizontalStrut(1)); // a spacer
		JLabel noadd = new JLabel(message);
	    myPanel.add(noadd);
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Erreur", JOptionPane.OK_CANCEL_OPTION);
	}
	
}
