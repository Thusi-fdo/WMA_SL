package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ResidentInterface extends Remote {

	public String[] getAreaList() throws RemoteException;
    
	public String[] getSubAreaList(int Subarea_Index) throws RemoteException;
    
    public boolean CreateResident(Resident r) throws RemoteException;
}
