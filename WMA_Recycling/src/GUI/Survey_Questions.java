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
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.UIManager;
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
	
	
	//String[] QAnswers;
	Question QAnswers[];

	

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
				//QAnswers = new String[NoOfQuestions()];
				QAnswers = new Question[NoOfQuestions()]; //System.out.println(question_number);
				//panel.setLayout();
				//panel.remove(btnNext);
				//frame.repaint();
				
				int qid = setQuestion(question_number);	
				comboOptions.setBackground(Color.WHITE);
				comboOptions.addActionListener(new ActionListener() {
					int x=0;
					public void actionPerformed(ActionEvent e) {
					
					if(x<1) {
					
					String selected =  (String) comboOptions.getSelectedItem();
					//System.out.println(selected);
					//comboOptions.set
					
	               
					try {
							int optionId= Qinterface.GetOptionID(selected);
							Question question_answers = new Question(qid,selected,optionId);
							QAnswers[index]=question_answers;
							/*System.out.println(index+"-"+QAnswers[index].getQID()+" "
									+QAnswers[index].getAnswer());*/
							System.out.println(QAnswers[index].getOptionID());
							index++;
					}
					catch(IndexOutOfBoundsException ei) {
						System.out.println("End of questions");
					}
					catch(Exception e4) {
						System.out.println(e4);
					}
					x++;
					}
				}
				});
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
		
		UIManager.put("Button.background", Color.white);
		
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 735, 220);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(question_number!=NoOfQuestions()) {
					try
					{	
						int qid = setQuestion(question_number);
						comboOptions.addActionListener(new ActionListener() {
							int x=0;
							public void actionPerformed(ActionEvent e) {
								if(x<1) {
									
									String selected =  (String) comboOptions.getSelectedItem();
									try {
											int optionId= Qinterface.GetOptionID(selected);
											Question question_answers = new Question(qid,selected,optionId);
											QAnswers[index]=question_answers;
											/*System.out.println(index+"-"+QAnswers[index].getQID()+" "
													+QAnswers[index].getAnswer());*/
											System.out.println(QAnswers[index].getOptionID());
											index++;
									}
									catch(IndexOutOfBoundsException ei) {
										System.out.println("End of questions");
									}
									catch(Exception e4) {
										System.out.println(e4);
									}
									x++;
								}
								
							
							}
						});
			
						question_number=question_number+1;
					}
					catch(Exception ex){
						System.out.print(ex);
					}
					}
				
				else {
						frame.remove(comboOptions);
						frame.remove(lbl_Q1);
						frame.repaint();
						try {
								Qinterface.DBAnswers(QAnswers);
							
							for(int i=0;i<QAnswers.length;i++) {
								System.out.println(QAnswers[i].getAnswer());
							}
								int btnokay= JOptionPane.showConfirmDialog(panel,"Questionnaire completed","Success",JOptionPane.DEFAULT_OPTION);
							
						} catch (Exception e1) {
							System.out.println(e1);
							e1.printStackTrace();
						}
					
					}
				
				
			}
		});
		btnNext.setFont(new Font("SansSerif", Font.BOLD, 16));
		btnNext.setBounds(606, 150, 85, 21);
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
		btnBack.setBounds(496, 150, 85, 21);
		panel.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Questions");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
		lblNewLabel.setBounds(319, 0, 150, 31);
		panel.add(lblNewLabel);
		
		lbl_Q1 = new JLabel();
		lbl_Q1.setFont(new Font("SansSerif", Font.BOLD, 16));
		lbl_Q1.setBounds(37, 54, 532, 24);
		panel.add(lbl_Q1);
		
		JPanel panelBg = new JPanel();
		panelBg.setBackground(Color.WHITE);
		panelBg.setBounds(0, 220, 735, 200);
		frame.getContentPane().add(panelBg);
		panelBg.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Survey_Questions.class.getResource("/Images/bgnew2.jpg")));
		lblNewLabel_4.setBounds(-35, -51, 800, 241);
		panelBg.add(lblNewLabel_4);
		
		
	}
	
public int setQuestion(int count) {
		
			
		///Question_Query db = new Question_Query();
	try {
		
		List<Question> quesArray = new ArrayList<Question>();
		
		quesArray=Qinterface.PrepareQuestions();
		
		String s= quesArray.get(count).getQuestion();
		int qid = quesArray.get(count).getQID();
		lbl_Q1.setText(s);
		
		String options[]=quesArray.get(count).getChoices();	
		//Question_Panel= new JPanel();
		//Question_Panel.setBounds(30, 100, 200, 300);
		//Question_Panel.setLayout(null);
		
		comboOptions.setBounds(34, 100,300, 24);		
		comboOptions.setFont(new Font("SansSerif", Font.BOLD, 16));
		comboOptions.setModel(new DefaultComboBoxModel(options));
	
		
		
		
		
		//Question_Panel.add(comboOptions);
		panel.add(comboOptions);
		

		
		comboOptions.setVisible(true);
		return quesArray.get(count).getQID();
		}
		
	catch(Exception ex) {
			
		System.out.println(ex);
		return -1;
			
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
