package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Question;
import Code.QuestionInterface;
import Database.Database_Connection;

public class Questionnaire_Service extends UnicastRemoteObject implements QuestionInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5986479382103607704L;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private static Database_Connection instance;
	private final String URL = "jdbc:mysql://localhost:3306/wma_db?user=root&password=";

	protected Questionnaire_Service() throws RemoteException {

		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
		}
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
	
	    } catch (SQLException ex) {
	        Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	@Override
	public List<Question> PrepareQuestions() throws RemoteException {
		String Query="SELECT QID,Question,q_type FROM survey_question";
		List<Question> questionList = new ArrayList<Question>();
		String[] Options;
		Question q;
    	try {
            
    		PreparedStatement psQues = conn.prepareStatement(Query);
            ResultSet rsQues = psQues.executeQuery();
            while(rsQues.next()){
				// System.out.println(rs.getString("Question"));	///""+rs.getInt("QID")+"\t"+
				String type=rsQues.getString("q_type");
            	if (type.equalsIgnoreCase("JComboBox")) {
            		
            		Options=GetOptions(rsQues.getInt("QID"));
            		String Q =rsQues.getString("Question");
            		q = new Question(rsQues.getInt("QID"),Q,Options);
            		
            	}
            	else {
            		
            		q = new Question(rsQues.getInt("QID"),rsQues.getString("Question"));
            	}
            	
            	questionList.add(q);
            					 
			  }                  
            rsQues.close();
            psQues.close();
            
            return questionList;
        
        }
    	catch (Exception e) {
            System.out.println(e);
            return null;
            }
       
	}

	@Override
	public String[] GetOptions(int qid)throws RemoteException {
		try {

    		PreparedStatement ps1 = conn.prepareStatement("select * from options where QID="+qid);
    		ResultSet rs1 = ps1.executeQuery();
    		Statement stmt = conn.createStatement();
    		ResultSet Count = stmt.executeQuery("select count(*) from options where QID="+qid);
    		Count.next();
    		
    		int size = Count.getInt("count(*)");
    		String[] options=new String[size];
    		int i=0;
    		while(rs1.next()){
    			
    			
    			
    			options[i]=rs1.getString("Description");
    			i++;
    			
    			
    		 }
        
    		ps1.close();
    		rs1.close();
    		return options;
    		
    	}
    	
    	catch(Exception ex) {
    		
    		System.out.println(ex);
            return null;
    	}
	}
	
	public int GetQuestionNo()throws RemoteException{
		try {
			
		
			ps=conn.prepareStatement("select count(*) from survey_question");
			rs=ps.executeQuery();
			rs.next();
			int count= rs.getInt("count(*)");
			return count;
		}catch(Exception e) {
			System.out.println(e);
			return 0;
		}
		
		
	}

}
