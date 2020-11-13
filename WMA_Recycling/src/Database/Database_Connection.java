package Database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database_Connection {

	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private static Database_Connection instance;
	private final String URL = "jdbc:mysql://localhost:3306/wma_db?user=root&password=";
	//&useUnicode=true&characterEncoding=UTF-8
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
}
