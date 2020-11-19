package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;

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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import Code.Question;
import Code.QuestionInterface;
import Database.Question_Query;

import java.awt.event.ActionListener;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class Survey_Questions {

	JFrame frame;
	int question_number=0;
	JComboBox comboOptions = new JComboBox();
	JPanel panel = new JPanel();
	JLabel lbl_Q1;
	JPanel Question_Panel;
	private QuestionInterface Qinterface;
	int index=0;
	
	String[] QAnswers;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Survey_Questions window = new Survey_Questions();
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
	public Survey_Questions() {
		initialize();
		
		try
		{
				
				Qinterface = (QuestionInterface) Naming.lookup("rmi://localhost:1968/QuestionServer");
				QAnswers = new String[NoOfQuestions()];
				panel.setLayout(null);
				//panel.remove(btnNext);
				//frame.repaint();
				setQuestion(question_number);				
				question_number=question_number+1;
											
		}		
		catch(Exception ex){
			
			System.out.print(ex);
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 749, 454);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 800, 450);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				try
				{		
					
						setQuestion(question_number);
						question_number=question_number+1;
				}
				catch(IndexOutOfBoundsException ex) {
					int result= JOptionPane.showConfirmDialog(null, "You Have Suceesfully Completed the Survey", "Success",JOptionPane.DEFAULT_OPTION);
					
				    
					if (result == 0) 
						panel.remove(comboOptions);
						frame.repaint();
				}
				catch(Exception ex){
					
					System.out.print(ex);
				}
				
			}
		});
		btnNext.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNext.setBounds(606, 311, 85, 21);
		panel.add(btnNext);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(question_number!=1) {
				try
				{		
						
						
						question_number=question_number-1;
						setQuestion(question_number-1);
						
				}				
				catch(Exception ex){
					
					System.out.print(ex);
				}
				
				}
				
				
			}
		});
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnBack.setBounds(496, 311, 85, 21);
		panel.add(btnBack);
		
		JLabel lblNewLabel_5 = new JLabel("How does your household divert or disposes hazardous waste and electronic waste?");
		lblNewLabel_5.setEnabled(false);
		lblNewLabel_5.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_5.setBounds(37, 251, 686, 24);
		panel.add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEnabled(false);
		comboBox.setFont(new Font("SansSerif", Font.BOLD, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"In the public bin", "By the valley/river/lakeside", "In an itinerant waste van", "By the road or side street", "On an open space", "In a hole in own compartment"}));
		comboBox.setBounds(461, 210, 230, 24);
		panel.add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("How do you dispose the collected household waste?");
		lblNewLabel_1.setEnabled(false);
		lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_1.setBounds(37, 207, 415, 21);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Questions");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(319, 0, 150, 31);
		panel.add(lblNewLabel);
		
		lbl_Q1 = new JLabel();
		lbl_Q1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lbl_Q1.setBounds(37, 54, 532, 24);
		panel.add(lbl_Q1);
		
		JLabel lblNewLabel_3 = new JLabel("Have you ever been educated on proper waste management by the council?");
		lblNewLabel_3.setEnabled(false);
		lblNewLabel_3.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel_3.setBounds(37, 157, 585, 21);
		panel.add(lblNewLabel_3);
		
		JComboBox comboBox_1 = new JComboBox();
		
		comboBox_1.setEnabled(true);
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Select", "Yes", "No"}));
		comboBox_1.setFont(new Font("SansSerif", Font.BOLD, 16));
		comboBox_1.setBounds(632, 155, 91, 24);
		panel.add(comboBox_1);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\asus\\Downloads\\bgnew.jpg"));
		lblNewLabel_4.setBounds(0, 0, 734, 419);
		panel.add(lblNewLabel_4);
	}
	
public void setQuestion(int count) {
		
			
		///Question_Query db = new Question_Query();
	try {
				
		List<Question> quesArray = new ArrayList<Question>();
		
		quesArray=Qinterface.PrepareQuestions();
		
		String s= quesArray.get(count).getQuestion();
		lbl_Q1.setText(s);
		
		String options[]=quesArray.get(count).getChoices();	
		//Question_Panel= new JPanel();
		//Question_Panel.setBounds(30, 100, 200, 300);
		//Question_Panel.setLayout(null);
		frame.repaint();
		comboOptions.setBounds(34, 100,146, 24);		
		comboOptions.setFont(new Font("SansSerif", Font.BOLD, 16));
		comboOptions.setModel(new DefaultComboBoxModel(options));
		comboOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected =  (String) comboOptions.getSelectedItem();
               
                QAnswers[index++]=selected;
                System.out.println(QAnswers[index-1]);
			}
		});
		
		//Question_Panel.add(comboOptions);
		panel.add(comboOptions);
		comboOptions.setVisible(true);
		}
		
	catch(Exception ex) {
			
		System.out.println(ex);
			
		}
		
		
		
	}

	public int NoOfQuestions() {
		try {
			return Qinterface.GetQuestionNo();
		} catch (RemoteException e) {
			System.out.println(e);
			e.printStackTrace();
			return 0;
		}		
	}
}
