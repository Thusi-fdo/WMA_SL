package Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Question;

public class Database_Connection {

	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private static Database_Connection instance;
	private final String URL = "jdbc:mysql://localhost:3306/wma_db?user=root&password=";

	public Database_Connection() {
		
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
	
	
    public static Database_Connection getSingleInstance() {
        try {
            if (instance == null) {
                instance = new Database_Connection();
                return instance;
            } else if (instance.conn.isClosed()) {
                instance = new Database_Connection();
                return instance;
            } else {
                return instance;
            }

        } catch (SQLException ex) {
            Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    
    public boolean ExecutionQuery(String Query) {
        try {
            Statement st = conn.createStatement();
            int result = st.executeUpdate(Query);
            return (result > 0);
        } catch (SQLException ex) {
            Logger.getLogger(Database_Connection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
    public int login(String s, String user, String pass) {
        int log = 1;
        try {
            ps = conn.prepareStatement(s);
            rs = ps.executeQuery();
            // System.out.println(user+" "+pass); o print the UserName and PW in the console
            while (rs.next()){
                if (rs.getString("NIC").equals(user) && rs.getString("Password").equals(pass)){               
                	
                	
                log = 0;
                break;
                }
                
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return log;

    }
    
    public String[] GetArea(String Query,String QueryCount) {
    	try {
    		ps=conn.prepareStatement(QueryCount);
    		rs=ps.executeQuery(); 
    		rs.next();    		
    		int size = rs.getInt("count(*)");
    		String[] AreaList= new String[size];
    		
    		PreparedStatement ps1=conn.prepareStatement(Query);
    		ResultSet rs1=ps1.executeQuery();
    		int i=0;
    		while(rs1.next()) {
    			
    			AreaList[i]=rs1.getString("Area");
    			GetSubArea(rs1.getInt("Area_ID"));
    			i++;
    		}
    		ps1.close();
    		rs1.close();
    		return AreaList;
    		
    	}catch(SQLException ex) {    		
    		
    		System.out.println(ex.getMessage());
    		return null;
    	}
    	
    	
    }
    
    public String[] GetSubArea(int area_ID) {
    	
    	try {
    		ps=conn.prepareStatement("select count(*) from subarea where Area_ID="+area_ID);
    		rs=ps.executeQuery(); 
    		rs.next();    		
    		int size = rs.getInt("count(*)");
    		String[] SubList= new String[size];
    		
    		PreparedStatement ps1=conn.prepareStatement("select * from subarea where Area_ID="+area_ID);
    		ResultSet rs1=ps1.executeQuery();
    		int i=0;
    		while(rs1.next()) {
    			
    			SubList[i]=rs1.getString("Subarea");
    			i++;
    		}
    		ps1.close();
    		rs1.close();
    		return SubList;
    		
    	}catch(SQLException ex) {    		
    		
    		System.out.println(ex.getMessage());
    		return null;
    	}
    	
    	
    }
    
    
    

	public List<Question> GetQuestions(String sqlQuery) {
		List<Question> questionList = new ArrayList<Question>();
		String[] Options;
		Question q;
    	try {
            
    		PreparedStatement psQues = conn.prepareStatement(sqlQuery);
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
	
	
	public String[] GetOptions(int qid) {
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
}
