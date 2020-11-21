package Code;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInterface extends Remote{
	
	 public String login(Login login) throws RemoteException; 
	    
	    /**
	     * Logout of the system.
	     * @param cookie
	     * @return
	     * @throws RemoteException
	     */
	public String logout(String cookie) throws RemoteException; 

}
