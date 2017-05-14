package dialogueUtilisateur;

import inscriptions.Candidat;
import inscriptions.Competition;
import inscriptions.Equipe;
import inscriptions.Inscriptions;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.SortedSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class Ihm2 {

	private JFrame frame;
	private JTextField dateClot;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JComboBox competitions;
	private JComboBox equipes;

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
	 * @throws SQLException 
	 */
	public Ihm2() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		/*fenêtre*/
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 482, 426);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFefe = new JLabel("Menu ");
		lblFefe.setBounds(190, 11, 46, 14);
		frame.getContentPane().add(lblFefe);
		
		/*onglets*/
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 36, 425, 340);
		frame.getContentPane().add(tabbedPane);
		
		/*competition*/
		
		/*Equipes*/
//		JLayeredPane equipe = new JLayeredPane();
		JLayeredPane equipe = new EquipeOnglet();

		tabbedPane.addTab("Equipes", null, equipe, null);
		
		/*Personnes*/
		JLayeredPane personne = new PersonnesOnglet();
		tabbedPane.addTab("Personnes", null, personne, null);
		
		/*COMPETITION*/
		JLayeredPane competition = new CompetitionsOnglet();
		tabbedPane.addTab("Competitions", null, competition, null);
	}
}
