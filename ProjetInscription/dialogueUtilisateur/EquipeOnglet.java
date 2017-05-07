package dialogueUtilisateur;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

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
import javax.swing.JTextField;

public class EquipeOnglet extends JLayeredPane{
	
	private JComboBox equipes;
	private JList membreList;
	private JTextField nom;

	public EquipeOnglet(){
		super();
		JLabel lblEquipes = new JLabel("Equipes");
		lblEquipes.setBounds(10, 11, 46, 14);
		this.add(lblEquipes);
		
		Inscriptions i = Inscriptions.getInscriptions();
		
		i.openConnection();
		SortedSet<Equipe> e = null;
		try {
			e = i.getEquipes();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		i.closeConnection();
		
		/*Membres*/
		JLabel label = new JLabel("Membres :");
		label.setBounds(10, 67, 75, 14);
		this.add(label);
		
		membreList = new JList();
		membreList.setBounds(10, 112, 166, 144);
		this.add(membreList);
		
		/*liste d�roulante des �quipes*/
		Object[] equipesList = e.toArray();
		equipes= new JComboBox(equipesList);
		this.add(equipes);
		equipes.setBounds(10, 36, 110, 20);
		equipes.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
			/*Affichage des membre de l'�quipe*/
			Equipe e = (Equipe) equipes.getSelectedItem();
			Set<Personne> membres = null;
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					try {
						membres = e.getMembres();
						membreList.setListData(membres.toArray());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		
		
		
		Equipe premierEquipe= (Equipe)equipesList[0];
		try {
			membreList.setListData(premierEquipe.getMembres().toArray());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		/*Ajouter membre*/
		
		JButton btnAjouterMembre = new JButton("Ajouter du membre");
		btnAjouterMembre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipe equipe = (Equipe) equipes.getSelectedItem();
				Set<Personne> candidatsSansEquipe;
				Inscriptions ins = Inscriptions.getInscriptions();
				try {
					candidatsSansEquipe = ins.getPersonnes();
					if(candidatsSansEquipe==null) {
						boxErreur("Aucun candidat.");
					}else {
						JList candidatsSansEquipeList = new JList();
						candidatsSansEquipeList.setListData(candidatsSansEquipe.toArray());
						JPanel myPanel = new JPanel();
						myPanel.add(Box.createHorizontalStrut(15)); // a spacer
						myPanel.add(candidatsSansEquipeList);
						int result = JOptionPane.showConfirmDialog(null, myPanel, "Ajouter un membre", JOptionPane.OK_CANCEL_OPTION);
						if (result == JOptionPane.OK_OPTION) {
							/*Ajout d'un membre*/
							ArrayList<Personne> listPers = new ArrayList();
							for (Personne pers : candidatsSansEquipe){
								listPers.add(pers);
								for(Equipe equipePers : pers.getEquipes()){
									if(equipePers.getIdCandidat()==equipe.getIdCandidat())
										listPers.remove(pers);
								}
							}
							int index = candidatsSansEquipeList.getSelectedIndex();
							Personne personneselect = listPers.get(index);
							Personne p;
							try {
								p = personneselect.getPersonne();
								equipe.add(p);
								candidatsSansEquipeList.remove(index);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							
						
							/*Mise � jour des candidats non inscrit*/
							Set<Personne> candiatsNoTSign = ins.getPersonnes();
							candidatsSansEquipeList.setListData(candiatsNoTSign.toArray());

							/*Mise � jour des candidats de la comp�tition*/
							Set<Personne> personnes;
							try {
								personnes = equipe.getMembres();
								candidatsSansEquipeList.setListData(personnes.toArray());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		btnAjouterMembre.setBounds(10, 84, 166, 23);
		add(btnAjouterMembre);
		
		/*Supprimer membre*/
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipe equipe = (Equipe) equipes.getSelectedItem();
				ArrayList<Personne> listPers= new ArrayList();
				try {
					for (Personne pers : equipe.getMembres()){
						listPers.add(pers);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}				
				int index = membreList.getSelectedIndex();
				Personne membresuppr = listPers.get(index);
				// TODO Candidat ne veut pas se drop tout de suite de la liste
				try {
					equipe.remove(membresuppr);
					membreList.remove(index);
					/*Mise � jour*/
					Set<Personne> membres = equipe.getMembres();
					membreList.setListData(membres.toArray());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSupprimer.setBounds(37, 266, 110, 23);
		add(btnSupprimer);
		
		/*MODIFIER UNE EQUIPE*/
		
		JButton modfierEquipe = new JButton("Modifier");
		modfierEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipe equipe = (Equipe) equipes.getSelectedItem();
				JTextField nomEquipe = new JTextField(equipe.getNom());
			    
			    JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("Nom :"));
			    myPanel.add(nomEquipe);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			   
			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Modifier", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) {
			    	String newName = nomEquipe.getText();
					if(newName.length()==0 ){
						boxErreur("Vous devez remplir tous les champs");
					}else {
						equipe.setNom(newName);
						/*TODO Mise � jour Jcombox*/
					}
			      }

			}
		});
		modfierEquipe.setBounds(132, 35, 90, 23);
		this.add(modfierEquipe);
		
		
		/*SUPPRIMER UNE EQUIPE*/
		JButton supprimerEquipe = new JButton("Supprimer");
		supprimerEquipe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Equipe equipe = (Equipe) equipes.getSelectedItem();
				Inscriptions i = Inscriptions.getInscriptions();
				i.openConnection();
				i.remove(equipe);
				equipes.removeItem(equipe);
				i.closeConnection();
			}
		});
		supprimerEquipe.setBounds(232, 35, 96, 23);
		this.add(supprimerEquipe);
		
		JLabel lblAjouterUnequipe = new JLabel("Ajouter une \u00E9quipe");
		lblAjouterUnequipe.setBounds(232, 88, 123, 14);
		this.add(lblAjouterUnequipe);
		
		/*AJOUTER UNE EQUIPE*/
		JLabel NomEquipe = new JLabel("Nom");
		NomEquipe.setBounds(232, 113, 31, 14);
		this.add(NomEquipe);
		
		nom = new JTextField();
		nom.setColumns(10);
		nom.setBounds(273, 110, 109, 20);
		this.add(nom);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String nomEquipe = nom.getText();
				if(nomEquipe.length()==0 ){
					boxErreur("Vous devez remplir tous les champs");
				}else {
						Inscriptions ins = Inscriptions.getInscriptions();
						try {
							Equipe equipe = ins.createEquipe(0,nomEquipe);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					/*TODO Mise � jour Jcombox*/
				}
			}
			      
		});
		btnAjouter.setBounds(266, 153, 89, 23);
		this.add(btnAjouter);
		
		
		
		
	}
	
	public void boxErreur(String message){
		JPanel myPanel = new JPanel();
		myPanel.add(Box.createHorizontalStrut(1)); // a spacer
		JLabel noadd = new JLabel(message);
	    myPanel.add(noadd);
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Erreur", JOptionPane.OK_CANCEL_OPTION);
	}
}
