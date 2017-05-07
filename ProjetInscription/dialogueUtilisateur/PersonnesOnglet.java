package dialogueUtilisateur;

import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

public class PersonnesOnglet extends JLayeredPane{
	private JComboBox personnes;


	public PersonnesOnglet() {
		super();
		JLabel lblNewLabel = new JLabel("Personne");
		lblNewLabel.setBounds(10, 11, 46, 14);
		this.add(lblNewLabel);
		
		Inscriptions i = Inscriptions.getInscriptions();

		i.openConnection();
		SortedSet<Personne> p = null;
		try {
			p = i.getPersonnes();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		i.closeConnection();
		

		/*liste déroulante des personnes*/
		Object[] personnesList = p.toArray();
		personnes = new JComboBox(personnesList);
		
		personnes.setBounds(10, 35, 101, 20);
		this.add(personnes);
		
		/*Modifier Personne*/
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Personne personne = (Personne) personnes.getSelectedItem();
				JTextField nomPers= new JTextField(personne.getNom());
				JTextField prenomPers;
				try {
					prenomPers = new JTextField(personne.getPrenom());
					JTextField mail= new JTextField(personne.getMail());
					JPanel myPanel = new JPanel();
				    myPanel.add(new JLabel("Nom :"));
				    myPanel.add(nomPers);
				    myPanel.add(new JLabel("Prénom :"));
				    myPanel.add(prenomPers);
				    myPanel.add(new JLabel("Mail :"));
				    myPanel.add(mail);
				    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
				   
				    int result = JOptionPane.showConfirmDialog(null, myPanel, "Modifier", JOptionPane.OK_CANCEL_OPTION);
				      if (result == JOptionPane.OK_OPTION) {
				    	String newName = nomPers.getText();
				    	String newFirtsName = prenomPers.getText();
				    	String newMail = mail.getText(); 
				    	
						if(newName.length()==0 || newFirtsName.length()==0 || newMail.length()==0){
							boxErreur("Vous devez remplir tous les champs");
						}else {
							personne.setPersonne(newName,newFirtsName,newMail);
							/*TODO Mise à jour Jcombox*/
						}
				      }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			    

			}
		});
		btnModifier.setBounds(134, 34, 109, 23);
		this.add(btnModifier);
		
		/*Modifier Personne*/
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(253, 34, 109, 23);
		this.add(btnSupprimer);
		
		/*Ajouter une personne*/
		JLabel lblAdd = new JLabel("Ajouter une personne");
		lblAdd.setBounds(225, 80, 123, 14);
		this.add(lblAdd);
		
		JLabel lblNom = new JLabel("Nom");
		lblNom.setBounds(225, 105, 60, 14);
		this.add(lblNom);
		
		final JTextField nom = new JTextField();
		nom.setBounds(295, 102, 109, 20);
		this.add(nom);
		nom.setColumns(10);
		
		JLabel lblPrenom = new JLabel("Pr\u00E9nom");
		lblPrenom.setBounds(225, 143, 60, 14);
		this.add(lblPrenom);
		
		final JTextField prenom = new JTextField();
		prenom.setBounds(295, 140, 109, 20);
		this.add(prenom);
		prenom.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(225, 189, 60, 14);
		this.add(lblEmail);
		
		final JTextField email = new JTextField();
		email.setBounds(295, 186, 109, 20);
		this.add(email);
		email.setColumns(10);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    String nomPers = nom.getText();
			    String prenomPers = prenom.getText();
			    String mail= email.getText();
				if(nomPers.length()==0 || prenomPers.length()==0 | mail.length()==0 ){
					boxErreur("Vous devez remplir tous les champs");
				}else {
						Inscriptions ins = Inscriptions.getInscriptions();
						try {
							Personne personne = ins.createPersonne(0,nomPers,nomPers,mail);
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					/*TODO Mise à jour Jcombox*/
				}
			}
			      
		});
		btnAjouter.setBounds(259, 240, 89, 23);
		this.add(btnAjouter);
		
		JLabel lblNewLabel_2 = new JLabel("Participe \u00E0 :");
		lblNewLabel_2.setBounds(10, 80, 75, 14);
		this.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 101, 205, 182);
		this.add(panel);
	}
	
	public void boxErreur(String message){
		JPanel myPanel = new JPanel();
		myPanel.add(Box.createHorizontalStrut(1)); // a spacer
		JLabel noadd = new JLabel(message);
	    myPanel.add(noadd);
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "Erreur", JOptionPane.OK_CANCEL_OPTION);
	}
}
