package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JTextField;

import Code.QuestionInterface;
import Code.Resident;
import Code.ResidentInterface;
import Database.Register_Query;

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
import java.sql.DriverManager;
import java.awt.event.ActionEvent;

public class Resident_Register {

	JFrame frame;
	private JTextField txt_Name;
	private JTextField txt_Email;
	private JTextField txt_NIC;
	private JTextField txt_address;
	private JPasswordField txt_pwdCon;
	private JPasswordField passwordField;
	private JComboBox comboSubarea;
	private JComboBox comboArea;
	String areaList[];
	String subareaList[];
	Register_Query rq = new Register_Query();
	ResidentInterface RI;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Resident_Register window = new Resident_Register();
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
	public Resident_Register() {
		
		try {
			RI = (ResidentInterface) Naming.lookup("rmi://localhost:1968/ResServer");
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
		frame.setBounds(100, 100, 749, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 733, 415);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNewButton_1.setBounds(517, 290, 171, 23);
		panel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try
				{
					
					String NIC= txt_NIC.getText(); ////d[9]v regex
					String ResName= txt_Name.getText();
					String email= txt_Email.getText();
					String pwd= new String (passwordField.getPassword()); //min 8 charaters
					String area= (String)comboArea.getSelectedItem();
					String subArea= (String)comboSubarea.getSelectedItem();
					String Address= txt_address.getText();
					
					//System.out.println(NIC);
					if(!Resident.valEmail(email)) {
						JOptionPane.showMessageDialog(null, "Please insert a valid email address","Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(!Resident.ValidateNIC(NIC)){
						JOptionPane.showMessageDialog(null, "Please insert a valid NIC number","Error", JOptionPane.ERROR_MESSAGE);
					}
					else if(!Resident.ValidatePW(pwd)) {
						JOptionPane.showMessageDialog(null, "Password should contain atleast 8 characters, 1 lower case, 1 upper case, 1 special charater and no white-spaces","Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
					
					Resident res= new Resident(ResName, email, pwd,area, subArea, Address);
					//res.setNIC(NIC);
					//System.out.println(Resident.getNIC());
					
					boolean created= RI.CreateResident(res,NIC);
					if(created) {
						JOptionPane.showMessageDialog(null, "Hello!"+ResName+"Account has been successfully created","Success", JOptionPane.DEFAULT_OPTION);
						Resident_Dashboard window = new Resident_Dashboard();
						window.frame.setVisible(true);
						frame.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Sorry!"+ResName+"There was an unexpected error, try again","Error", JOptionPane.ERROR_MESSAGE);
					}
					}
				}
				catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
			}
		});
		
		comboSubarea = new JComboBox();
		comboSubarea.setBounds(497, 99, 191, 23);
		panel.add(comboSubarea);
		
		comboArea = new JComboBox();
		comboArea.setBounds(497, 55, 191, 23);
		panel.add(comboArea);
		comboArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AreaActionPerformed(evt);
            }
        });
		
		JLabel lblNewLabel_8 = new JLabel("SubArea:");
		lblNewLabel_8.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_8.setBounds(379, 100, 86, 13);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_5 = new JLabel("Area:");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_5.setBounds(379, 56, 86, 13);
		panel.add(lblNewLabel_5);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(146, 195, 191, 21);
		panel.add(passwordField);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_2.setBounds(23, 49, 125, 21);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("NIC:");
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_3.setBounds(23, 142, 125, 14);
		panel.add(lblNewLabel_3);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Password:");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(23, 198, 214, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Address:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_1.setBounds(23, 239, 214, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_4 = new JLabel("Email:");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_4.setBounds(23, 96, 214, 14);
		panel.add(lblNewLabel_4);
		
		txt_Name = new JTextField();
		txt_Name.setBounds(146, 49, 191, 20);
		panel.add(txt_Name);
		txt_Name.setColumns(10);
		
		txt_Email = new JTextField();
		txt_Email.setBounds(146, 92, 191, 20);
		panel.add(txt_Email);
		txt_Email.setColumns(10);
		
		txt_NIC = new JTextField();
		txt_NIC.setBounds(146, 139, 191, 20);
		panel.add(txt_NIC);
		txt_NIC.setColumns(10);
		
		txt_address = new JTextField();
		txt_address.setBounds(138, 236, 199, 20);
		panel.add(txt_address);
		txt_address.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Register");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel_6.setBounds(297, 0, 436, 23);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("");
		lblNewLabel_7.setIcon(new ImageIcon("C:\\Users\\asus\\Downloads\\bgnew.jpg"));
		lblNewLabel_7.setBounds(0, 0, 733, 415);
		panel.add(lblNewLabel_7);
		
		getAreaData();
	}
	
	
	public void getAreaData() {
	
				
		try {
			areaList = RI.getAreaList();
			comboArea.setModel(new DefaultComboBoxModel(areaList));
		} catch (RemoteException e) {
			System.out.println(e);
			e.printStackTrace();
		}		
		
		
	}
	
	private void AreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceitemActionPerformed
        //serviceprice.setText(serviceitem.getSelectedItem().toString());

        int subarea_index=comboArea.getSelectedIndex();
        try {
			subareaList=RI.getSubAreaList(subarea_index);
			comboSubarea.setModel(new DefaultComboBoxModel(subareaList));
		} catch (RemoteException e) {
			System.out.println(e);
			e.printStackTrace();
		}
        
        
    }
	/////
}
