package GUI;

import java.awt.Color;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Code.ChartData;
import Code.Question;
import Code.QuestionInterface;
import Server.Questionnaire_Service;

public class Admin_Charts {

	private JFrame frame;
	JList list = new JList();
	List<Question> Questions;
	QuestionInterface Qinterface;
	JPanel panel;
	JSONArray Labellist;
	 JSONArray vallist;
	 JLabel lblChart;
	 

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
		frame.setBounds(100, 100, 900, 550);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		panel = new JPanel(new FlowLayout());
		panel.setBounds(0, 0, 850, 550);
		frame.getContentPane().add(panel);
		
		list.setBorder(null);
		list.setVisibleRowCount(9);
		
		list.setFont(new Font("Arial", Font.PLAIN, 12));
		list.setFixedCellHeight(40);
		list.setFixedCellWidth(300);
		list.setSelectionBackground(new Color(162, 217, 206 ));
		
		

		
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
	                		System.out.println(qid);
	                		List <ChartData> chartData= new ArrayList();
	                		chartData=Qinterface.getChartData(qid);
		                	for(int i=0;i<chartData.size();i++) {
		                		System.out.println(chartData.get(i).getAnswer()+"  here");
		                		Labellist.add(chartData.get(i).getAnswer());
		                		vallist.add(chartData.get(i).getCount());
		                		
		                		
		                	}
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
		
		  
		  panel.add(list);
		  panel.add(new JScrollPane(list));
		  
		  lblChart = new JLabel();		
			panel.add(lblChart);
	
	}

	void createChart(){
	
		
		
	
		 JSONObject label=new JSONObject();
		 label.put("label", "Users");
		 
		 JSONObject data1=new JSONObject();
		 data1.put("data", vallist);
		 
					
		JSONArray datasets=new JSONArray();
		datasets.add(label);
		datasets.add(data1); //new JSONObject().put("datasets",datasets)
		
		JSONObject data=new JSONObject();	
		data.put("labels", Labellist);
		data.put("datasets",datasets); //new JSONObject().put("datasets",datasets)
		
		
		
		JSONObject main=new JSONObject();
		
		main.put("data",data );//
		main.put("type", "bar");
		
		
		System.out.println(main); 
		
		
		
		/*{
			  type: 'bar',                                // Show a bar chart
			  data: {
			  --  labels: [2012, 2013, 2014, 2015, 2016],   // Set X-axis labels
			    datasets: [{
			     -- label: 'Users',                         // Create the 'Users' dataset
			     -- data: [120, 60, 50, 180, 120]           // Add data to the chart
			    }]
			  }
			}
		
		*/
		
		String chartUrl =
			    "https://quickchart.io/chart?bkg=white&width=250&height=200&chart=" +
			    URLEncoder.encode(main.toJSONString(), StandardCharsets.UTF_8);
		URL chartimg;
		try {
			chartimg = new URL(chartUrl);
			Image img = ImageIO.read(chartimg);//.getScaledInstance(700, 300,  java.awt.Image.SCALE_SMOOTH);
			
			ImageIcon newIcon = new ImageIcon(img);
			lblChart = new JLabel();
			lblChart.setBounds(0,0,736,413);
			lblChart.setIcon(newIcon);
			
			panel.add(lblChart);
		} catch (Exception e) {
			System.out.print(e);
			e.printStackTrace();
		}
	}
}
