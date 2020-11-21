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

import Code.Login;
import Code.LoginInterface;
import Code.Resident;
import Database.Database_Connection;

public class LoginService extends UnicastRemoteObject implements LoginInterface{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5279563966281803277L;
	private String sessionCookie = "abc"+Math.random();
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	private final String URL = "jdbc:mysql://localhost:3306/wma_db?user=root&password=";
	
	protected LoginService() throws RemoteException {
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
	public String login(Login login) throws RemoteException {
		try {
			ps = conn.prepareStatement("select * from resident");
			rs = ps.executeQuery();
			while (rs.next()){
				if (rs.getString("NIC").equals(login.getNIC()) && rs.getString("Password").equals(login.getPassword())){					
					Resident.setNIC( rs.getString("NIC"));                 	
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
