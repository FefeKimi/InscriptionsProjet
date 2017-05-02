package dialogueUtilisateur;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;
import inscriptions.Personne;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.Set;
import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EquipeOnglet extends JLayeredPane{
	
	private JComboBox equipes;
	private JList membreList;
	private JTextField textField_4;

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
		
		/*liste déroulante des compétitions*/
		Object[] equipesList = e.toArray();
		equipes= new JComboBox(equipesList);
		this.add(equipes);
		equipes.setBounds(10, 36, 90, 20);
		equipes.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
			Equipe e = (Equipe) equipes.getSelectedItem();
				if(arg0.getStateChange() == ItemEvent.SELECTED){
					Set<Personne> membres = null;
					try {
						membres = (Set<Personne>) e.getMembres();
						membreList.setListData(membres.toArray());
						for (Personne p : membres)
							System.out.println(p);
					} catch (SQLException e1) {
						e1.printStackTrace();
					
					}
				}
			}
		});
		
		membreList = new JList();
		membreList.setBounds(10, 102, 166, 144);
		this.add(membreList);
		
		Equipe premierEquipe= (Equipe)equipesList[0];
		try {
			membreList.setListData(premierEquipe.getMembres().toArray());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		JLabel label = new JLabel("Membres :");
		label.setBounds(10, 88, 75, 14);
		this.add(label);
		/*MODIFIER UNE EQUIPE*/
		
		JButton button = new JButton("Modifier");
		button.setBounds(110, 35, 90, 23);
		this.add(button);
		
		JButton button_1 = new JButton("Supprimer");
		button_1.setBounds(202, 35, 96, 23);
		this.add(button_1);
		
		JLabel lblAjouterUnequipe = new JLabel("Ajouter une \u00E9quipe");
		lblAjouterUnequipe.setBounds(232, 88, 123, 14);
		this.add(lblAjouterUnequipe);
		
		JLabel label_1 = new JLabel("Nom");
		label_1.setBounds(232, 113, 31, 14);
		this.add(label_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(273, 110, 109, 20);
		this.add(textField_4);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(266, 153, 89, 23);
		this.add(btnAjouter);
	}
	
}
