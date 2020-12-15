package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Code.EmployeeInterface;
import Code.Login;
import Code.LoginInterface;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Admin_Login {

	JFrame frame;
	private JTextField txtEmail;
	private JPasswordField txtPw;
	EmployeeInterface EI;
	String mySessionCookie = "not set";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Login window = new Admin_Login();
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
	public Admin_Login() {
		try {
			EI = (EmployeeInterface) Naming.lookup("rmi://localhost:1968/EmployeeServer");
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
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
		frame.setBounds(100, 100, 812, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(342, 30, 201, 34);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Email: ");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_1.setBounds(225, 149, 173, 34);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2.setBounds(225, 230, 173, 34);
		frame.getContentPane().add(lblNewLabel_2);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("SansSerif", Font.BOLD, 16));
		txtEmail.setBounds(408, 149, 236, 34);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		txtPw = new JPasswordField();
		txtPw.setFont(new Font("SansSerif", Font.BOLD, 16));
		txtPw.setBounds(408, 230, 236, 34);
		frame.getContentPane().add(txtPw);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(new Color(122,175,23));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = txtEmail.getText();
				String Password = new String(txtPw.getPassword());				
				
					            
	           // Login_Query odb=new Login_Query();//Making Object of the class OrderDB
	            
	            //int logg=odb.loginMatch(login);
				String capResults;
				try {
					capResults = EI.login(email,Password);
					 if (capResults.equals("wrong")){
						 JOptionPane.showMessageDialog(null,"Username or Password Incorrect",
								 "Error",JOptionPane.ERROR_MESSAGE);
						 txtPw.setText(null);
							txtEmail.setText(null);
							
			                txtEmail.grabFocus();
					 } else { 
							mySessionCookie = capResults; 
							System.out.println("Your login was successful.");

			            	Admin_Dashboard window = new Admin_Dashboard();
							window.frame.setVisible(true);
							frame.dispose();
						}
			            	
			            	
							
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            
				
			}
		});
		btnLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnLogin.setBounds(498, 308, 146, 34);
		frame.getContentPane().add(btnLogin);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(Admin_Login.class.getResource("/Images/bgnew.jpg")));
		lblNewLabel_3.setBounds(0, 0, 798, 473);
		frame.getContentPane().add(lblNewLabel_3);
	}
}
