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

public class Resident_Feedback {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resident_Feedback window = new Resident_Feedback();
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
	public Resident_Feedback() {
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
		
		JLabel lblNewLabel_5 = new JLabel("Feedback has been submitted");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(216, 113, 192, 21);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Sheena Villawarayen\\Desktop\\Dayan\\tick.png"));
		lblNewLabel_3.setBounds(430, 70, 256, 256);
		panel.add(lblNewLabel_3);
		
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
		lblNewLabel_1.setBounds(25, 193, 52, 42);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Sheena Villawarayen\\Desktop\\Dayan\\wmalogo.png"));
		lblNewLabel_2.setBounds(7, 273, 64, 42);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton_6 = new JButton("Logout");
		btnNewButton_6.setBounds(7, 381, 89, 23);
		panel_1.add(btnNewButton_6);
		
		JLabel lblNewLabel = new JLabel("Did you know:");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(110, 112, 214, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Thank you for your Submission");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(248, 56, 214, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel("Resident_Feedback");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_6.setBounds(301, 22, 161, 23);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setBackground(new Color(102, 153, 153));
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\asus\\Downloads\\bgnew.jpg"));
		lblNewLabel_7.setBounds(0, 0, 733, 415);
		panel.add(lblNewLabel_7);
	}
}
