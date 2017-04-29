package dialogueUtilisateur;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PersonnesOnglet extends JLayeredPane{
	
	private JTextField textField_1;
	private JTextField textField_2;

	public PersonnesOnglet() {
		super();
		JLabel lblNewLabel = new JLabel("Personne");
		lblNewLabel.setBounds(10, 11, 46, 14);
		this.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(20, 35, 65, 20);
		this.add(comboBox);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBounds(121, 34, 72, 23);
		this.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.setBounds(203, 34, 89, 23);
		this.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Ajouter une personne");
		lblNewLabel_1.setBounds(225, 80, 123, 14);
		this.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(277, 101, 109, 20);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Participe \u00E0 :");
		lblNewLabel_2.setBounds(10, 80, 75, 14);
		this.add(lblNewLabel_2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 101, 205, 182);
		this.add(panel);
		
		JLabel lblNewLabel_4 = new JLabel("Nom");
		lblNewLabel_4.setBounds(225, 105, 31, 14);
		this.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Pr\u00E9nom");
		lblNewLabel_5.setBounds(225, 143, 36, 14);
		this.add(lblNewLabel_5);
		
		textField_1 = new JTextField();
		textField_1.setBounds(277, 140, 109, 20);
		this.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(225, 189, 31, 14);
		this.add(lblEmail);
		
		textField_2 = new JTextField();
		textField_2.setBounds(277, 186, 109, 20);
		this.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Ajouter");
		btnNewButton_2.setBounds(259, 240, 89, 23);
		this.add(btnNewButton_2);
	}
	
	

}
