package dialogueUtilisateur;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Ihm2 {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ihm2 window = new Ihm2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ihm2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFefe = new JLabel("Menu ");
		lblFefe.setBounds(190, 11, 46, 14);
		frame.getContentPane().add(lblFefe);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 399, 287);
		frame.getContentPane().add(tabbedPane);
		
		JLayeredPane competition = new JLayeredPane();
		tabbedPane.addTab("Competitions", null, competition, null);
		
		JLabel lblComptitions = new JLabel("Comp\u00E9titions");
		lblComptitions.setBounds(10, 11, 99, 14);
		competition.add(lblComptitions);
		
		JComboBox competitions = new JComboBox();
		competitions.setBounds(20, 36, 28, 20);
		competition.add(competitions);
		
		JButton btnNewButton = new JButton("Modifier");
		btnNewButton.setBounds(131, 35, 89, 23);
		competition.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Supprimer");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(234, 35, 89, 23);
		competition.add(btnNewButton_1);
		
		JLabel lblCrerUneComptition = new JLabel("Cr\u00E9er une comp\u00E9tition");
		lblCrerUneComptition.setBounds(10, 83, 138, 14);
		competition.add(lblCrerUneComptition);
		
		textField = new JTextField();
		textField.setBounds(10, 120, 111, 20);
		competition.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nom de la comp\u00E9tition");
		lblNewLabel.setBounds(10, 107, 111, 14);
		competition.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date de cl\u00F4ture");
		lblNewLabel_1.setBounds(10, 151, 99, 14);
		competition.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("En \u00E9quipe ?");
		lblNewLabel_2.setBounds(10, 195, 99, 14);
		competition.add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(10, 164, 111, 20);
		competition.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(10, 211, 111, 20);
		competition.add(textField_2);
		
		JLayeredPane equipes = new JLayeredPane();
		tabbedPane.addTab("Equipes", null, equipes, null);
		
	
		
		
		JLayeredPane personne = new JLayeredPane();
		tabbedPane.addTab("Personne", null, personne, null);
	}
}
