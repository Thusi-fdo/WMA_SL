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
import javax.swing.JPasswordField;

public class Admin_Graph {

    JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Graph window = new Admin_Graph();
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
	public Admin_Graph() {
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
		panel.setBounds(0, 0, 733, 415);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.setBounds(446, 284, 85, 21);
		panel.add(btnNewButton);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(343, 230, 149, 29);
		panel.add(comboBox_2);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(343, 184, 149, 23);
		panel.add(comboBox_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(343, 138, 149, 23);
		panel.add(comboBox);
		
		JLabel lblNewLabel_3 = new JLabel("Time Frame");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_3.setBounds(184, 175, 149, 37);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel = new JLabel("District:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(184, 128, 149, 37);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Type of Waste");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_4.setBounds(184, 222, 149, 37);
		panel.add(lblNewLabel_4);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(102, 153, 102));
		panel_1.setBackground(new Color(153, 153, 153));
		panel_1.setBounds(0, 0, 103, 415);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Home");
		btnNewButton_2.setBounds(7, 5, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Profile");
		btnNewButton_3.setBounds(7, 44, 89, 23);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Survey");
		btnNewButton_4.setBounds(7, 94, 89, 23);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Graphs");
		btnNewButton_5.setBounds(7, 147, 89, 23);
		panel_1.add(btnNewButton_5);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Sheena Villawarayen\\Desktop\\Dayan\\wplogo.png"));
		lblNewLabel_1.setBounds(7, 193, 70, 100);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Sheena Villawarayen\\Desktop\\Dayan\\wmalogo.png"));
		lblNewLabel_2.setBounds(7, 304, 100, 100);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel_6 = new JLabel("Graph");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_6.setBounds(353, 25, 149, 23);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBackground(new Color(102, 153, 153));
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\asus\\Downloads\\bgnew.jpg"));
		lblNewLabel_7.setBounds(0, 0, 733, 415);
		panel.add(lblNewLabel_7);
	}
}
