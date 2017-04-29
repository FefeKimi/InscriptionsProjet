package dialogueUtilisateur;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Inscriptions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
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
	private JTextField textField_3;
	private JList candidatsList;


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
						/*for (Candidat cand : candidats) {
							System.out.println(cand.getNom());
						}*/
					} catch (SQLException e) {
						e.printStackTrace();
					
					}
				}
			}
		});
		
		competitions.setBounds(10, 36, 76, 20);
		this.add(competitions);
		
		candidatsList = new JList();

		Competition premierComp = (Competition)competitionsList[0];
		try {
			candidatsList.setListData(premierComp.getCandidats().toArray());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		/*MODIFIER*/
		JButton btnModifier= new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Competition c = (Competition)competitions.getSelectedItem();
				
				JTextField nomField = new JTextField(c.getNom());
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String formattedString = c.getDateCloture().format(formatter);
			      JTextField dateField = new JTextField(formattedString);
			      JPanel myPanel = new JPanel();
			      myPanel.add(new JLabel("Nom:"));
			      myPanel.add(nomField);
			      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			      myPanel.add(new JLabel("Date de cloture:"));
			      myPanel.add(dateField);

			      int result = JOptionPane.showConfirmDialog(null, myPanel, 
			               "Please Enter X and Y Values", JOptionPane.OK_CANCEL_OPTION);
			      if (result == JOptionPane.OK_OPTION) {
			         System.out.println("x value: " + nomField.getText());
			         System.out.println("y value: " + dateField.getText());
			      }

			}
		});
		btnModifier.setBounds(127, 35, 89, 23);
		this.add(btnModifier);
		btnModifier.setSize(100,20);
		
		/*SUPPRIMER*/
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
		
		/*FORMULAIRE COMPETITION*/
		JLabel lblCrerUneComptition = new JLabel("Nouvelle comp\u00E9tition");
		lblCrerUneComptition.setBackground(Color.LIGHT_GRAY);
		lblCrerUneComptition.setBounds(199, 79, 166, 14);
		this.add(lblCrerUneComptition);
		
		JLabel nomCompetlbl = new JLabel("Nom ");
		nomCompetlbl.setForeground(Color.DARK_GRAY);
		nomCompetlbl.setBounds(199, 107, 24, 14);
		this.add(nomCompetlbl);
		
		JLabel dateClotlbl = new JLabel("Date de cl\u00F4ture");
		dateClotlbl.setForeground(Color.DARK_GRAY);
		dateClotlbl.setBounds(199, 151, 99, 14);
		this.add(dateClotlbl);
		
		candidatsList.setBounds(10, 102, 166, 144);
		this.add(candidatsList);
		
		JLabel enEquipelbl = new JLabel("En \u00E9quipe ?");
		enEquipelbl.setForeground(Color.DARK_GRAY);
		enEquipelbl.setBounds(199, 253, 64, 14);
		this.add(enEquipelbl);
		
		JRadioButton rdbtnOui = new JRadioButton("Oui");
		rdbtnOui.setBackground(Color.WHITE);
		rdbtnOui.setForeground(Color.BLACK);
		rdbtnOui.setBounds(269, 249, 41, 23);
		this.add(rdbtnOui);
		
		JRadioButton rdbtnNon = new JRadioButton("Non");
		rdbtnNon.setBackground(Color.WHITE);
		rdbtnNon.setForeground(Color.BLACK);
		rdbtnNon.setBounds(312, 249, 45, 23);
		this.add(rdbtnNon);
		
		JLabel lblNewLabel_3 = new JLabel("Candidats :");
		lblNewLabel_3.setBounds(10, 79, 64, 14);
		this.add(lblNewLabel_3);
		
		JButton btnNewButton_3 = new JButton("Ajouter");
		btnNewButton_3.setBounds(199, 278, 89, 23);
		this.add(btnNewButton_3);
		
		textField_3 = new JTextField();
		textField_3.setBounds(235, 104, 86, 20);
		this.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(209, 172, 46, 14);
		this.add(lblNewLabel_6);
	}
	
	
	

}
