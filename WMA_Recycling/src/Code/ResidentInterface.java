package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ResidentInterface extends Remote {

    public String Login(String NIC, String Password) throws RemoteException; 
    
    public String LogOut(String NIC) throws RemoteException; 
}
