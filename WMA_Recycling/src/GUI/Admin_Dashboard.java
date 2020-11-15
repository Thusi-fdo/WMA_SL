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

public class Admin_Dashboard {

    JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Dashboard window = new Admin_Dashboard();
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
	public Admin_Dashboard() {
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
		
		JButton btnNewButton_8 = new JButton("..");
		btnNewButton_8.setBackground(new Color(255, 160, 122));
		btnNewButton_8.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_8.setBounds(331, 183, 149, 51);
		panel.add(btnNewButton_8);
		
		JButton btnNewButton_7 = new JButton("..");
		btnNewButton_7.setBackground(new Color(255, 160, 122));
		btnNewButton_7.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_7.setBounds(150, 183, 149, 51);
		panel.add(btnNewButton_7);
		
		JButton btnNewButton_9 = new JButton("..");
		btnNewButton_9.setBackground(new Color(255, 160, 122));
		btnNewButton_9.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_9.setBounds(525, 183, 149, 51);
		panel.add(btnNewButton_9);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setBackground(new Color(255, 160, 122));
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_1.setBounds(331, 106, 149, 51);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_6 = new JButton("Survey");
		btnNewButton_6.setBackground(new Color(255, 160, 122));
		btnNewButton_6.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_6.setBounds(525, 106, 149, 51);
		panel.add(btnNewButton_6);
		
		JButton btnNewButton = new JButton("View Graphs");
		btnNewButton.setBackground(new Color(255, 160, 122));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton.setBounds(150, 106, 149, 51);
		panel.add(btnNewButton);
		
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
		
		JLabel lblNewLabel_6 = new JLabel("Dashboard");
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
