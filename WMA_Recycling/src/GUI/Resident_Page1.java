package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;

public class Resident_Page1 {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resident_Page1 window = new Resident_Page1();
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
	public Resident_Page1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 749, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(61, 249, 45, 20);
		panel.add(spinner);
		
		JLabel lblNewLabel_2 = new JLabel("City or Region:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2.setBounds(118, 127, 125, 21);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sub Area:");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_3.setBounds(118, 188, 125, 14);
		panel.add(lblNewLabel_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBackground(new Color(153, 204, 51));
		comboBox.setFont(new Font("SansSerif", Font.BOLD, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Colombo", "Negombo", "Galle", "Jaffna", "Puttalam", "Gampaha", "Moratuwa", "Anuradapura"}));
		comboBox.setBounds(279, 126, 280, 22);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Kotahena", "Katuwapitiya"}));
		comboBox_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		comboBox_1.setBounds(279, 188, 280, 21);
		panel.add(comboBox_1);
		
		JButton btnNewButton = new JButton("Employee");
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton.setBounds(279, 278, 122, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_1.setBounds(422, 278, 137, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\asus\\Downloads\\bgnew.jpg"));
		lblNewLabel_4.setBounds(0, 0, 734, 419);
		panel.add(lblNewLabel_4);
	}
}
