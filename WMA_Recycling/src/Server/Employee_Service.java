package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import Code.Employee;
import Code.EmployeeInterface;




public class Employee_Service extends UnicastRemoteObject implements EmployeeInterface {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2919560986572723893L;
	private String sessionCookie = "abc"+Math.random();
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private final String URL = "jdbc:mysql://localhost:3306/wma_db?user=root&password=";
	
	protected Employee_Service() throws RemoteException {
		super();
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
		}

		catch (ClassNotFoundException ex) {
			Logger.getLogger(Employee_Service.class.getName()).log(Level.SEVERE, null, ex);

		} catch (SQLException ex) {
			Logger.getLogger(Employee_Service.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	

	@Override
	public String login(String username, String password) throws RemoteException {
		try {
			ps = conn.prepareStatement("select * from employee");
			rs = ps.executeQuery();
			while (rs.next()){
				if (rs.getString("Email").equals(username) && rs.getString("Password").equals(password)){					
					Employee.seteID( rs.getInt("Employee_ID"));                 	
					sessionCookie = "xyz"+Math.random(); 
					return sessionCookie; 
				}

			}
			return "wrong";

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public String logout(String cookie) throws RemoteException {
		sessionCookie = "abc"+Math.random(); 
		return "logout successful";    
	}

}
