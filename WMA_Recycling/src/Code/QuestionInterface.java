package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface QuestionInterface extends Remote {
	
	public List<Question> PrepareQuestions() throws RemoteException; 
    
    public String[] GetOptions(int qid) throws RemoteException; 
    
    public int GetQuestionNo() throws RemoteException;

}
