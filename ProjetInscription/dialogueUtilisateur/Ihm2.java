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
import javax.swing.JRadioButton;

public class Ihm2 {

	private JFrame frame;
	private JTextField nomCompet;
	private JTextField dateClot;

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
		/*fenêtre*/
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 450, 373);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFefe = new JLabel("Menu ");
		lblFefe.setBounds(190, 11, 46, 14);
		frame.getContentPane().add(lblFefe);
		
		/*onglets*/
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 399, 287);
		frame.getContentPane().add(tabbedPane);
		
		/*competition*/
		JLayeredPane competition = new JLayeredPane();
		tabbedPane.addTab("Competitions", null, competition, null);
		
		JLabel lblComptitions = new JLabel("Comp\u00E9titions");
		lblComptitions.setBounds(10, 11, 99, 14);
		competition.add(lblComptitions);
		
		JComboBox competitions = new JComboBox();
		competitions.setBounds(20, 36, 28, 20);
		competition.add(competitions);
		
		JButton btnModifier= new JButton("Modifier");
		btnModifier.setBounds(127, 35, 89, 23);
		competition.add(btnModifier);
		
		JButton supprimer = new JButton("Supprimer");
		supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		/*form competition*/
		supprimer.setBounds(235, 35, 89, 23);
		competition.add(supprimer);
		
		JLabel lblCrerUneComptition = new JLabel("Cr\u00E9er une comp\u00E9tition");
		lblCrerUneComptition.setBackground(Color.LIGHT_GRAY);
		lblCrerUneComptition.setBounds(226, 79, 138, 14);
		competition.add(lblCrerUneComptition);
		
		JLabel nomCompetlbl = new JLabel("Nom de la comp\u00E9tition");
		nomCompetlbl.setForeground(Color.DARK_GRAY);
		nomCompetlbl.setBounds(226, 104, 111, 14);
		competition.add(nomCompetlbl);
		
		nomCompet = new JTextField();
		nomCompet.setBounds(225, 117, 86, 20);
		competition.add(nomCompet);
		nomCompet.setColumns(10);
		
		JLabel dateClotlbl = new JLabel("Date de cl\u00F4ture");
		dateClotlbl.setForeground(Color.DARK_GRAY);
		dateClotlbl.setBounds(225, 148, 99, 14);
		competition.add(dateClotlbl);
		
		dateClot = new JTextField();
		dateClot.setColumns(10);
		dateClot.setBounds(226, 161, 111, 20);
		competition.add(dateClot);
		
		JLabel enEquipelbl = new JLabel("En \u00E9quipe ?");
		enEquipelbl.setBounds(225, 203, 99, 14);
		competition.add(enEquipelbl);
		
		JRadioButton rdbtnOui = new JRadioButton("Oui");
		rdbtnOui.setForeground(Color.DARK_GRAY);
		rdbtnOui.setBounds(226, 224, 54, 23);
		competition.add(rdbtnOui);
		
		JRadioButton rdbtnNon = new JRadioButton("Non");
		rdbtnNon.setForeground(Color.DARK_GRAY);
		rdbtnNon.setBounds(279, 224, 54, 23);
		competition.add(rdbtnNon);
		
		JLabel lblNewLabel_3 = new JLabel("Candidats :");
		lblNewLabel_3.setBounds(10, 79, 64, 14);
		competition.add(lblNewLabel_3);
		
		JPanel candidats = new JPanel();
		candidats.setBounds(10, 79, 179, 144);
		competition.add(candidats);
		
		
		/*Equipes*/
		JLayeredPane equipes = new JLayeredPane();
		tabbedPane.addTab("Equipes", null, equipes, null);
		
		/*Personnes*/
		JLayeredPane personne = new JLayeredPane();
		tabbedPane.addTab("Personne", null, personne, null);
	}
}
