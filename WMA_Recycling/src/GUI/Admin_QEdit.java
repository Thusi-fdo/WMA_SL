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
import javax.swing.table.TableRowSorter;
import javax.swing.text.Document;

import Code.Question;
import Code.QuestionInterface;



public class Admin_QEdit {

	//private JFrame frame;
	private JComponent ui = null;
    JTextField filterText;
    TableRowSorter sorter;
    QuestionInterface Qinterface;
    JPanel panel = new JPanel();
    JFrame f;
   

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
        f.setBounds(100,100,850,450);
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
		//frame = new JFrame();
		//frame.setBounds(100, 100, 450, 300);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		

        JPanel gui = new JPanel(new BorderLayout(4, 4));
        gui.setBorder(new EmptyBorder(4, 4, 4, 4));

      /*  GraphicsEnvironment ge
                = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fonts = ge.getAvailableFontFamilyNames();*/
        
        try {
			List<Question> qArray=Qinterface.PrepareQuestions();
			String[][] tableData = new String[qArray.size()][1];
	        for (int i = 0; i < qArray.size(); i++) {
	            tableData[i][0] = qArray.get(i).getQuestion();
	        }
	        String[] header = {"Questions"};
	        JTable table = new JTable(tableData, header);
	        if (listLike) {
	            Dimension d = table.getPreferredScrollableViewportSize();
	            table.setPreferredScrollableViewportSize(new Dimension(d.width/2,d.height));
	            table.setShowGrid(false);
	            table.setTableHeader(null);
	            table.setFillsViewportHeight(true);
	        }
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
	        f.getContentPane().add(gui, BorderLayout.LINE_START); //left column
	        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	            public void valueChanged(ListSelectionEvent event) {
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
	    
	
	 
	public void displayAnswers(String choices[]) {
		
		//if (ui != null) {
          //  return;
        //}

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
	        JTable table = new JTable(tableData, header);
	        
            Dimension d = table.getPreferredScrollableViewportSize();
            table.setPreferredScrollableViewportSize(new Dimension(d.width/2,d.height));
            table.setShowGrid(false);
            table.setTableHeader(null);
            table.setFillsViewportHeight(true);
	        
	        ui.add(new JScrollPane(table));
	        sorter = new TableRowSorter(table.getModel());
	        table.setRowSorter(sorter);

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
