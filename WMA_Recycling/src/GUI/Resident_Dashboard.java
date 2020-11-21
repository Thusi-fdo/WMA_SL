package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Resident_Dashboard {

    JFrame frame;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resident_Dashboard window = new Resident_Dashboard();
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
	public Resident_Dashboard() {
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("SansSerif", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Plastic", "Toxic", "E-materials", "Cardboard", "Paper", "Metals", "Wood"}));
		comboBox.setBounds(335, 122, 206, 23);
		panel.add(comboBox);
		
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
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Survey_Questions qUI= new Survey_Questions(); //change Survey_Questions into other ui 
				qUI.frame.setVisible(true);
				
				
			}
		});
		btnNewButton_4.setBounds(7, 94, 89, 23);
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Graphs");
		btnNewButton_5.setBounds(7, 147, 89, 23);
		panel_1.add(btnNewButton_5);
		
		JButton btnNewButton = new JButton("Upload");
		btnNewButton.setBackground(new Color(51, 255, 153));
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnNewButton.setBounds(301, 238, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("Estimated Weight(kg):");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_3.setBounds(118, 169, 191, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_1.setBounds(445, 271, 137, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Image of Waste:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(118, 240, 214, 21);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_4 = new JLabel("Type of Waste:");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_4.setBounds(118, 123, 214, 14);
		panel.add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(344, 169, 197, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Dashboard");
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
