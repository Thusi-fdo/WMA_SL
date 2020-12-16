package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;

import Code.Question;
import Code.QuestionInterface;
import Code.sessionCookie;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JToolBar;



public class Admin_QEdit {

	
	
    JTextField filterText;
    TableRowSorter sorter;
    TableRowSorter sorter2;
    QuestionInterface Qinterface;
    JPanel panel = new JPanel();
    JFrame f;
    JPanel gui;
    JTable table;
    JTable table2;
    JPanel ui;
    List<Question> qArray;
    DefaultTableModel TableModelOptions;
    DefaultTableModel TableModelQuestions;
    

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_QEdit window = new Admin_QEdit();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/
	
	public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Admin_QEdit o1 = new Admin_QEdit(false);
                    o1.f.setVisible(true);
                    
                } catch (Exception useDefault) {
                	useDefault.printStackTrace();
                }
                
            }
        };
        SwingUtilities.invokeLater(r);
    }

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public Admin_QEdit(boolean listLike) {
		try {
			Qinterface = (QuestionInterface) Naming.lookup("rmi://localhost:1968/QuestionServer");
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
		// Admin_QEdit o1 = new Admin_QEdit(true);
        
        //Admin_QEdit o2 = new Admin_QEdit(false);

        f = new JFrame("Question Editor");
        f.setBounds(100,100,1000,600);
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       // f.setLocationByPlatform(true);

        
       // f.getContentPane().add(o2.getUI(), BorderLayout.CENTER); // right column
        //f.pack();
       // f.setMinimumSize(f.getSize());
        
        f.setVisible(true);
		
		//QAnswers = new Question[NoOfQuestions()]; //System.out.println(question_number);
		initialize(listLike);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean listLike) {
		
		
		try {
			displayQuestion();
	        f.getContentPane().add(gui, BorderLayout.LINE_START);
	        {
	        	JPanel panel_Button = new JPanel();
	        	f.getContentPane().add(panel_Button, BorderLayout.SOUTH);
	        	{
	        		JButton btnAdd = new JButton("Add");
	        		btnAdd.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				String AddQuestion = JOptionPane.showInputDialog(f,"Add Question");
	        				if(AddQuestion!=null) {
	        					try {
									Qinterface.AddingQuestion(AddQuestion); // update DB
									
									//update qArray
									qArray=Qinterface.PrepareQuestions(); //--
									
									//update table
									TableModelQuestions.insertRow(TableModelQuestions.getRowCount(),new Object[] {AddQuestion});
								} catch (RemoteException e1) {
									System.out.println(e1);
									e1.printStackTrace();
								}
	        				}
	        				
	        			}
	        		});
	        		btnAdd.setBackground(new Color(0, 128, 0));
	        		panel_Button.add(btnAdd);
	        	}
	        	{
	        		JButton BtnDel = new JButton("Delete");
	        		BtnDel.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				
	        				if(table.getSelectedRow()!=-1) {
		        				try {
		        					
		        					int result = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete this question?");
									if(result==0)
									{
										
											
											
											
											int qid =qArray.get(table.getSelectedRow()).getQID();
											
											Qinterface.DeletingQuestion(qid); //removing from db
											
											//update qArray
											
											qArray=Qinterface.PrepareQuestions();
																
											//update table
											TableModelQuestions.removeRow(table.getSelectedRow());
											
											
											
									}
								} catch (RemoteException e1) {
									System.out.println(e1);
									e1.printStackTrace();
								}
		        				}
	        			}
	        		});
	        		BtnDel.setBackground(new Color(0, 128, 0));
	        		panel_Button.add(BtnDel);
	        	}
	        	
	        	panel_Button.add(Box.createRigidArea(new Dimension(200, 50)));
	        	
	        	{
	        		JButton BtnAddAns = new JButton("Add");
	        		BtnAddAns.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				
	        				f.revalidate();
	        				if(table.getSelectedRow()>=0) {
		        				String AddAnswer = JOptionPane.showInputDialog(f,"Add Answer Choices");
		        				
		        				if(AddAnswer!=null) {
		        					try {
										
		        						Qinterface.AddingChoices(AddAnswer, qArray.get(table.getSelectedRow()).getQID()); //saving to DB
		        						String currentchoices[]=qArray.get(table.getSelectedRow()).getChoices();		        						
		        						String newchoices[]=new String[currentchoices.length+1];
		        						for(int i=0;i<currentchoices.length;i++) {
		        							newchoices[i]=currentchoices[i];
		        						}
		        						newchoices[newchoices.length-1]=AddAnswer;
		        						qArray.get(table.getSelectedRow()).setChoices(newchoices); //updating qArray
		        						TableModelOptions.insertRow(TableModelOptions.getRowCount(), new Object[] {AddAnswer}); //new row to the table
		        						//table2.add(TableModelOptions);
		        				        
									
										
										
									} catch (RemoteException e1) {
										System.out.println(e1);
										e1.printStackTrace();
									}
		        				}
	        				}
	        			}
	        		});
	        		BtnAddAns.setBackground(new Color(0, 128, 0));
	        		panel_Button.add(BtnAddAns);
	        	}
	        	{
	        		JButton BtnDelAns = new JButton("Delete");
	        		
	        		BtnDelAns.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        			
	        				//System.out.println(table.getSelectedRow());
	        				f.revalidate();
	        				if(table2.getSelectedRow()!=-1) {
	        				try {
	        					
	        					int result = JOptionPane.showConfirmDialog(f, "Are you sure you want to delete this option?");
								if(result==0)
								{
									
										int tbl_row =table.getSelectedRow();
										
										String option=(String)table2.getValueAt(table2.getSelectedRow(), table2.getSelectedColumn());
										int qid =qArray.get(table.getSelectedRow()).getQID();
										
										Qinterface.DeletingChoices(option,qid); //removing from db
										
										//update qArray
										String newchoices[]=Qinterface.GetOptions(qid);
										qArray.get(table.getSelectedRow()).setChoices(newchoices);
															
										//update table
										TableModelOptions.removeRow(table2.getSelectedRow());
										
										
										
								}
							} catch (RemoteException e1) {
								System.out.println(e1);
								e1.printStackTrace();
							}
	        				}
	        				
	        				
	        			}
	        			
	        		});
	        		BtnDelAns.setBackground(Color.GREEN);
	        		panel_Button.add(BtnDelAns);
	        	}
	        }
	        {
	        	JPanel navBar = new JPanel();
	        	navBar.setBackground(Color.DARK_GRAY);
	        	f.getContentPane().add(navBar, BorderLayout.NORTH);
	        	navBar.setPreferredSize(new Dimension(1000,50));
	        	navBar.setLayout(null);
	        	{
	        		JButton btnUser = new JButton("Log Out");
	        		btnUser.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				sessionCookie.setCookie(null);
	        				Admin_Login window = new Admin_Login();
	        				window.frame.setVisible(true);
	        				f.dispose();

	        			}
	        		});
	        		btnUser.setFont(new Font("Arial", Font.PLAIN, 13));
	        		btnUser.setBackground(Color.WHITE);
	        		btnUser.setBounds(850, 5, 130, 40);
	        		navBar.add(btnUser);
	        	}
	        	{
	        		JButton btnNewButton_1 = new JButton("Edit Questions");
	        		btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 13));
	        		btnNewButton_1.setBounds(150, 5, 130, 40);
	        		navBar.add(btnNewButton_1);
	        	}
	        	{
	        		JButton btnChart = new JButton("Chart");
	        		btnChart.addActionListener(new ActionListener() {
	        			public void actionPerformed(ActionEvent e) {
	        				Admin_Charts window = new Admin_Charts();
	        				window.frame.setVisible(false);
	        				f.dispose();
	        			}
	        		});
	        		btnChart.setFont(new Font("Arial", Font.PLAIN, 13));
	        		btnChart.setBounds(5, 5, 85, 40);
	        		navBar.add(btnChart);
	        	}
	        }
	        
		} catch (Exception e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
        
       /* String[][] tableData = new String[fonts.length][1];
        for (int i = 0; i < fonts.length; i++) {
            tableData[i][0] = fonts[i];
        }*/
        
        
        
        
	}
	 
	private void newFilter() {
	        RowFilter rf = null;
	        //If current expression doesn't parse, don't update.
	        try {
	            rf = RowFilter.regexFilter(filterText.getText(), 0);
	        } catch (java.util.regex.PatternSyntaxException e) {
	            return;
	        }
	        sorter.setRowFilter(rf);
	    }

	public JComponent getUI() {
	        return ui;
	    }
	    
	public void displayQuestion() {

		
		gui = new JPanel(new BorderLayout(4, 4));
        gui.setBorder(new EmptyBorder(4, 4, 4, 4));
        
      /*  GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();*/
        
        try {
			qArray=Qinterface.PrepareQuestions();
			String[][] tableData = new String[qArray.size()][1];
	        for (int i = 0; i < qArray.size(); i++) {
	            tableData[i][0] = qArray.get(i).getQuestion();
	        }
	        String[] header = {"Questions"};
	        TableModelQuestions= new DefaultTableModel(tableData,header);
	        //table = new JTable(tableData, header);
	        table = new JTable(TableModelQuestions);
	        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	        gui.add(new JScrollPane(table));
	        sorter = new TableRowSorter(table.getModel());
	        table.setRowSorter(sorter);

	        filterText = new JTextField(10);
	        gui.add(filterText, BorderLayout.PAGE_START);
	        Document doc = filterText.getDocument();
	        DocumentListener listener = new DocumentListener() {

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                newFilter();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                newFilter();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                newFilter();
	            }
	        };
	        doc.addDocumentListener(listener);
	        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	            public void valueChanged(ListSelectionEvent event) {
	            	if(table.getSelectedRow()<0) 
	                {
	            		table.addRowSelectionInterval(0, 0);
	                }
		            	if (ui != null) {
		            	f.remove(ui);
		            	}
		            	String choices[] = qArray.get(table.getSelectedRow()).getChoices();
		            	displayAnswers(choices);
		                f.repaint();
		                f.revalidate();
		                System.out.println(table.getValueAt(table.getSelectedRow(), 0).toString());
	                
	            }
	        });
        }
        catch(Exception e) {
        	System.out.println(e);
        }
	}
	 
	public void displayAnswers(String choices[]) {
		
		

        ui = new JPanel(new BorderLayout(4, 4));
        ui.setBorder(new EmptyBorder(4, 4, 4, 4));

      /*  GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();*/
        
        try {
			//List<Question> qArray=Qinterface.PrepareQuestions();
			String[][] tableData = new String[choices.length][1];
	        for (int i = 0; i < choices.length; i++) {
	            tableData[i][0] = choices[i];
	        }
	        String[] header = {"Fonts"};
	        TableModelOptions = new DefaultTableModel(tableData,header);
	        table2 = new JTable(TableModelOptions);
	        //table2 = new JTable(tableData, header);
	        
            Dimension d = table2.getPreferredScrollableViewportSize();
            table2.setPreferredScrollableViewportSize(new Dimension(d.width/2,d.height));
            table2.setShowGrid(false);
            table2.setTableHeader(null);
            table2.setFillsViewportHeight(true);
	        
	        ui.add(new JScrollPane(table2));
	        sorter2 = new TableRowSorter(table2.getModel());
	        table2.setRowSorter(sorter2);

	        filterText = new JTextField(10);
	        ui.add(filterText, BorderLayout.PAGE_START);
	        Document doc = filterText.getDocument();
	        DocumentListener listener = new DocumentListener() {

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                newFilter();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                newFilter();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                newFilter();
	            }
	        };
	        doc.addDocumentListener(listener);
	        f.getContentPane().add(ui, BorderLayout.CENTER);
		} catch (Exception e1) {
			System.out.println(e1);
			e1.printStackTrace();
		}
		
	}
}
