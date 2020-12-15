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

import Code.Resident;
import Code.ResidentInterface;


public class ResidentService extends UnicastRemoteObject implements ResidentInterface {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement ps;
	
	private final String URL = "jdbc:mysql://localhost:3306/wma_db?user=root&password=";	
	
	protected ResidentService() throws RemoteException {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(URL);
			System.out.println("Connection Success");
		}
		
		catch (ClassNotFoundException ex) {
	        Logger.getLogger(ResidentService.class.getName()).log(Level.SEVERE, null, ex);
	
	    } catch (SQLException ex) {
	        Logger.getLogger(ResidentService.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}

	@Override
	public String[] getAreaList() throws RemoteException {
		String Query = "select * from area";
        String QueryCount="select count(*) from area";
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
    			getSubAreaList(rs1.getInt("Area_ID"));
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

	@Override
	public String[] getSubAreaList(int area_ID) throws RemoteException {
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

	@Override
	public boolean CreateResident(Resident r,String nic) throws RemoteException {
		String Query = "INSERT INTO `resident`(`NIC`, `Name`, `Email`, `Password`, `Address`, `City`, `Subarea`) VALUES ('"
    			+nic+"','"+r.getResidentName()+"','"+r.getEmail()+"','"+r.getPassword()+"','"+r.getAddress()+"','"+r.getCity()+"','"+r.getSubarea()+"')";
    	System.out.println(nic);
		try {
            Statement st = conn.createStatement();
            int result = st.executeUpdate(Query);
            return (result > 0);
           
        } catch (Exception ex) {
            Logger.getLogger(ResidentService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
	}

}
