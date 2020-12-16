package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import Code.ChartData;
import Code.Question;
import Code.QuestionInterface;
import Code.sessionCookie;
import Server.Questionnaire_Service;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_Charts {

	JFrame frame;
	JList list = new JList();
	List<Question> Questions;
	QuestionInterface Qinterface;
	JPanel panel;
	JSONArray Labellist;
	 JSONArray vallist;
	 JLabel lblChart;
	 private JTabbedPane tabbedPane;
	 private JPanel panel_1;
	 private JPanel panelTable;
	 private JTable table;
	 

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Charts window = new Admin_Charts();
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
	public Admin_Charts() {
		try {
			Qinterface = (QuestionInterface) Naming.lookup("rmi://localhost:1968/QuestionServer");
		} catch (Exception e) {
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
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.WHITE);
		frame.setBounds(100, 100, 1000, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(30, 95, 330, 450);
		frame.getContentPane().add(panel);
		list.setBackground(Color.WHITE);
		list.setBounds(1, 1, 318, 508);
		//list.setBounds(-226, 6, 300, 360);
		
		list.setBorder(null);
		list.setVisibleRowCount(9);
		
		list.setFont(new Font("Arial", Font.PLAIN, 12));
		list.setFixedCellHeight(40);
		list.setFixedCellWidth(450);
		list.setSelectionBackground(new Color(162, 217, 206 ));
		list.setBorder(new EmptyBorder(10,10, 10, 10));
		

		
		 UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());
		 
		 DefaultListModel data = new DefaultListModel();
			
		 Questions = new ArrayList<Question>();
		 
		 try {
			
			Questions=Qinterface.PrepareQuestions();
			for(int i=0; i<Questions.size();i++) {
				 data.addElement(Questions.get(i).getQuestion());
			} 
			
		 } catch (Exception e) {
			 data.addElement("Could not retrieve data");
			e.printStackTrace();
		}	
		
		 list.setModel(data);
		 
		
		
	        list.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent arg0) {
	                if (!arg0.getValueIsAdjusting()) {
	                	
	                if(list.getSelectedIndex()>-1) 
	                {
	                try{
	                	vallist = new JSONArray();	
	            		
	                	Labellist = new JSONArray();
	                		//panel.remove(lblChart);
	                		
	                		int qid =Questions.get(list.getSelectedIndex()).getQID();
	                		
	                		List <ChartData> chartData= new ArrayList();
	                		chartData=Qinterface.getChartData(qid);
		                	for(int i=0;i<chartData.size();i++) {
		                		
		                		Labellist.add(chartData.get(i).getAnswer());
		                		vallist.add(chartData.get(i).getCount());
		                		
		                		
		                		
		                	}
		                	TableData(chartData);
		                	createChart();
		                	frame.revalidate();
		                	panel.revalidate();
	                	}
	                	catch(Exception e) {
	                		System.out.print(e);
	                		e.printStackTrace();
	                	}
	                 
	                	
	                }
	                }
	            }
	        });
		  panel.setLayout(null);
		
		  
		  panel.add(list);
		  JScrollPane scrollPane = new JScrollPane(list);
		  scrollPane.setBounds(0, 0, 320, 450);
		  panel.add(scrollPane);
		  
		  
		  
		  JPanel panelChart = new JPanel();
		  panelChart.setBorder(null);
		  panelChart.setBackground(Color.WHITE);
		  panelChart.setBounds(380, 30, 535, 510);
		  //frame.getContentPane().add(panelChart);
		  panelChart.setLayout(new FlowLayout());
		  
		  lblChart = new JLabel("\r\n");		
		  lblChart.setBounds(0, 0, 535, 510);
		  panelChart.add(lblChart);
		  
		  tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		  tabbedPane.setBorder(null);
		  tabbedPane.setBackground(Color.WHITE);
		  tabbedPane.setBounds(369, 75, 570, 475);
		  frame.getContentPane().add(tabbedPane);
		  
		  panel_1 = new JPanel();
		  tabbedPane.addTab("Chart", null, panelChart, null);
		  tabbedPane.setEnabledAt(0, true);
		  tabbedPane.setBackgroundAt(0, Color.WHITE);
		  
		  panelTable = new JPanel();
		  panelTable.setBorder(null);
		  panelTable.setBackground(Color.WHITE);
		  tabbedPane.addTab("Table", null, panelTable, null);
		  panelTable.setLayout(null);
		  
		  table = new JTable();
		  table.setBackground(Color.WHITE);
		  table.setBounds(30, 30, 500, 300);
		  JScrollPane scrollPane1 = new JScrollPane(table);
		  tabbedPane.setEnabledAt(1, true);
		  tabbedPane.setBackgroundAt(1, Color.WHITE);
		  
		  scrollPane1.setBounds(30, 30, 500, 200);
		  scrollPane1.setBorder(BorderFactory.createLineBorder(Color.lightGray)); 
		  //scrollPane1.setPreferredSize(new Dimension(500, 400));
		  scrollPane1.getViewport().setBackground(Color.white);
		  panelTable.add(scrollPane1);
		  
		  JPanel navBar = new JPanel();
		  navBar.setForeground(Color.WHITE);
		  navBar.setBackground(Color.DARK_GRAY);
		  navBar.setBounds(0, 0, 1000, 50);
		  frame.getContentPane().add(navBar);
		  navBar.setLayout(null);
		  
		  JButton btnUser = new JButton("Log Out");
		  btnUser.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		sessionCookie.setCookie(null);
				Admin_Login window = new Admin_Login();
				window.frame.setVisible(true);
				frame.dispose();
		  	}
		  });
		  btnUser.setBackground(Color.WHITE);
		  //btnUser.setContentAreaFilled(false);
		  btnUser.setIcon(new ImageIcon(Admin_Charts.class.getResource("/Images/user1.png")));
		  btnUser.setFont(new Font("Arial", Font.PLAIN, 13));
		  btnUser.setBounds(850, 5, 130, 40);
		  navBar.add(btnUser);
		  
		  JButton btnChart = new JButton("Chart");
		  btnChart.setFont(new Font("Arial", Font.PLAIN, 13));
		  btnChart.setBounds(36, 5, 85, 40);
		  navBar.add(btnChart);
		  
		  JButton btnNewButton_1 = new JButton("Edit Questions");
		  btnNewButton_1.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent e) {
		  		Admin_QEdit window = new Admin_QEdit(false);
		  		window.f.setVisible(true);
		  		frame.dispose();
		  		
		  	}
		  });
		  btnNewButton_1.setFont(new Font("Arial", Font.PLAIN, 13));
		  btnNewButton_1.setBounds(145, 5, 130, 40);
		  navBar.add(btnNewButton_1);
		  
		
	
	}

	void createChart(){
	
		JSONArray bg=new JSONArray();
		bg.add("rgb(255, 99, 132)");
		bg.add("rgb(255, 159, 64)");
		bg.add("rgb(255, 205, 86)");
		bg.add("rgb(75, 192, 192)");
		bg.add("rgb(54, 162, 235)");
		
		
		
		String message;
		JSONObject json = new JSONObject();
		json.put("type", "donut");



		JSONArray datasets = new JSONArray();
		JSONObject item = new JSONObject();
		item.put("data", vallist);
		item.put("backgroundColor",bg);
		item.put("label", "Answers");
		datasets.add(item);

		JSONObject data = new JSONObject();
		data.put("datasets",datasets);
		data.put("labels",Labellist);


		json.put("data", data);

		message = json.toString();
		System.out.println(json);
		
		/*JSONObject backgroundcolor=new JSONObject();
		backgroundcolor.put("backgroundColor", bg);
		
		
		 JSONObject label=new JSONObject();
		 label.put("label", "Answers");
		 
		 JSONObject data1=new JSONObject();
		 data1.put("data", vallist);
		 
					
		JSONArray datasets=new JSONArray();
		
		datasets.add(data1); //new JSONObject().put("datasets",datasets)
		datasets.add(label);
		datasets.add(backgroundcolor);
		
		JSONObject data=new JSONObject();	
		data.put("labels", Labellist);
		data.put("datasets",datasets); //new JSONObject().put("datasets",datasets)
		
		
		
		JSONObject main=new JSONObject();
		
		main.put("data",data );//
		main.put("type", "bar");
		
		
		System.out.println(main);*/ 
		
		
		
		/*{
				  type: 'doughnut',
				  data: {
				    datasets: [
				      {
				        data: [94, 25, 72, 70, 14],
				        backgroundColor: [
				          'rgb(255, 99, 132)',
				          'rgb(255, 159, 64)',
				          'rgb(255, 205, 86)',
				          'rgb(75, 192, 192)',
				          'rgb(54, 162, 235)',
				        ],
				        label: 'Dataset 1',
				      },
				    ],
				    labels: ['Red', 'Orange', 'Yellow', 'Green', 'Blue'],
				  },
				  options: {
				    title: {
				      display: true,
				      text: 'Chart.js Doughnut Chart',
				    },
				  },
				}
				
						
						*/
	
		
		String chartUrl =
			    "https://quickchart.io/chart?bkg=white&width=250&height=200&chart=" +
			    URLEncoder.encode(json.toJSONString(), StandardCharsets.UTF_8);
		URL chartimg;
		try {
			chartimg = new URL(chartUrl);
			Image img = ImageIO.read(chartimg);//.getScaledInstance(700, 300,  java.awt.Image.SCALE_SMOOTH);
			
			ImageIcon newIcon = new ImageIcon(img);
			
			
			lblChart.setIcon(newIcon);
			
					} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
	}
	
	void TableData(List<ChartData> cd) {
		Object tbl_Data[][]= new Object[cd.size()][2];
		
		for(int i=0;i<cd.size();i++) {
			
			 tbl_Data[i][0]=cd.get(i).getAnswer();
			 tbl_Data[i][1]=cd.get(i).getCount();
				
		}
		
		
		DefaultTableModel dTableModel = new DefaultTableModel(tbl_Data,new String[]{"Answer","Count"});
		table.setModel(dTableModel);
	}
}
