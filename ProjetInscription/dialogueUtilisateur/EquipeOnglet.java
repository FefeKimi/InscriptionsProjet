package dialogueUtilisateur;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EquipeOnglet extends JLayeredPane{
	
	private JComboBox equipes;
	private JTextField textField_4;

	public EquipeOnglet(){
		super();
		JLabel lblEquipes = new JLabel("Equipes");
		lblEquipes.setBounds(10, 11, 46, 14);
		this.add(lblEquipes);
		
		equipes= new JComboBox();
		equipes.setBounds(20, 36, 65, 20);
		this.add(equipes);
		
		JButton button = new JButton("Modifier");
		button.setBounds(110, 35, 72, 23);
		this.add(button);
		
		JButton button_1 = new JButton("Supprimer");
		button_1.setBounds(185, 35, 89, 23);
		this.add(button_1);
		
		JLabel label = new JLabel("Participe \u00E0 :");
		label.setBounds(10, 88, 75, 14);
		this.add(label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 113, 205, 182);
		this.add(panel_1);
		
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
		btnAjouter.setBounds(258, 150, 89, 23);
		this.add(btnAjouter);
	}
	
}
