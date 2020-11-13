package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface QuestionInterface extends Remote {
	
    public Question[] getQuestionnaire() throws RemoteException; 
    
    public String submitData(String qid, String answer) throws RemoteException; 

}
