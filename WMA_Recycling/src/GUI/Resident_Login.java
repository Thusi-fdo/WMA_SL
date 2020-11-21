package GUI;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;

import Code.Login;
import Code.LoginInterface;
import Code.QuestionInterface;
import Code.Resident;
import Database.Login_Query;
import java.sql.*;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class Resident_Login {

	private JFrame frame;
	private JTextField txt_NIC;
	private JPasswordField pwd_login;
	LoginInterface LI;
	String mySessionCookie = "not set";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resident_Login window = new Resident_Login();
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
	public Resident_Login() {
		try {
			LI = (LoginInterface) Naming.lookup("rmi://localhost:1968/LoginServer");
		} catch (MalformedURLException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (RemoteException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (NotBoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 747, 453);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 733, 415);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Admin\r\n");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//call Employee login
			}
		});
		btnNewButton_2.setIcon(null);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(638, 384, 85, 21);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBackground(new Color(51, 255, 153));
		btnNewButton.setFont(new Font("SansSerif", Font.PLAIN, 11));
		btnNewButton.setBounds(219, 274, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_3 = new JLabel("NIC:");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_3.setBounds(119, 134, 125, 14);
		panel.add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Login");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String NIC = txt_NIC.getText();
				String Password = new String(pwd_login.getPassword());				
				Login login = new Login(NIC, Password);
					            
	           // Login_Query odb=new Login_Query();//Making Object of the class OrderDB
	            
	            //int logg=odb.loginMatch(login);
				String capResults;
				try {
					capResults = LI.login(login);
					 if (capResults.equals("wrong")){
						 JOptionPane.showMessageDialog(null,"Username or Password Incorrect",
								 "Error",JOptionPane.ERROR_MESSAGE);
							pwd_login.setText(null);
							txt_NIC.setText(null);
							
			                txt_NIC.grabFocus();
					 } else { 
							mySessionCookie = capResults; 
							System.out.println("Your login was successful.");

			            	Resident_Dashboard window = new Resident_Dashboard();
							window.frame.setVisible(true);
						}
			            	
			            	
							
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
	           
					
	            
	                
	          
			}
		});
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_1.setBounds(445, 271, 137, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(119, 178, 214, 14);
		panel.add(lblNewLabel);
		
		txt_NIC = new JTextField();
		txt_NIC.setBounds(345, 134, 197, 20);
		panel.add(txt_NIC);
		txt_NIC.setColumns(10);
		
		pwd_login = new JPasswordField();
		pwd_login.setBounds(345, 178, 197, 20);
		panel.add(pwd_login);
		
		JLabel lblNewLabel_6 = new JLabel("Login");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_6.setBounds(313, 21, 161, 23);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\asus\\Downloads\\bgnew.jpg"));
		lblNewLabel_7.setBounds(0, 0, 733, 415);
		panel.add(lblNewLabel_7);
	}
}
