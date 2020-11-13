package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WasteInterface extends Remote {
	
    public String AddWaste(Waste w) throws RemoteException; 

}
