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

import Code.ChartData;
import Code.Question;
import Code.QuestionInterface;
import Code.Resident;
import Database.Database_Connection;

public class Questionnaire_Service extends UnicastRemoteObject implements QuestionInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5986479382103607704L;
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;	
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

	public boolean QueryExecuter(String QE) {
		try 
		 {
	            Statement st = conn.createStatement();
	            int result = st.executeUpdate(QE);
	            return (result > 0);
	            
	        } catch (SQLException ex) {
	            System.out.println(ex);
	            return false;
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

	@Override
	public boolean DBAnswers(Question alist[]) throws RemoteException {
		boolean success=false;
		for (int i=0;i<alist.length;i++) {
			System.out.println(Resident.getNIC());
	         String query="INSERT INTO `answers`(NIC,Answer, QID) VALUES ('"+Resident.getNIC()+"','"+alist[i].getAnswer()+"',"+alist[i].getQID()+")";
	         try {
	             Statement st = conn.createStatement();
	             int result = st.executeUpdate(query);
	             if(result > 0) {
	            	 
	            	 success=true;
	             }
	         } catch (SQLException ex) {
	             Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
	             success= false;
	         }
	      } 
		return success;
	}

	@Override
	public boolean AddingQuestion(String NewQuestion) throws RemoteException {
		String sql_query = "INSERT INTO `survey_question`(`Question`, `q_type`) VALUES ('"+NewQuestion+"','JComboBox')";
		 return QueryExecuter(sql_query);		
	}

	@Override
	public boolean AddingChoices(String Description, int QID) throws RemoteException {
		String sql_query="INSERT INTO `options`(`Description`, `QID`) VALUES ('"+Description+"',"+QID+")";		
		return QueryExecuter(sql_query);
	}

	@Override
	public boolean DeletingChoices(String choice,int QID) throws RemoteException {
		String sql_query="DELETE FROM `options` WHERE Description='"+choice+"' AND `QID`="+QID;
		return QueryExecuter(sql_query);
	}

	@Override
	public List<ChartData> getChartData(int qid) throws RemoteException {
		List <ChartData> chartData = new ArrayList();
		String sql_query="SELECT *,count(Answer) FROM answers WHERE QID ="+qid+" GROUP BY `Answer`";
		int count =0;
		String Choice;
		try {
		
    		ps = conn.prepareStatement(sql_query);
            rs = ps.executeQuery();
            while(rs.next()){
				Choice=rs.getString("Answer");
				System.out.println(Choice);
				count= rs.getInt("count(Answer)");	
				chartData.add(new ChartData(Choice,count));			 
			  }                  
           
           
            return chartData;
        
        }
    	catch (Exception e) {
            System.out.println(e+"Error");
            e.printStackTrace();
            return null;
            }
	}
	

}
