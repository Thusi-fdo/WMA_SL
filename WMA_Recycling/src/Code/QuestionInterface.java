package Code;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface QuestionInterface extends Remote {
	
	public List<Question> PrepareQuestions() throws RemoteException; 
    
    public String[] GetOptions(int qid) throws RemoteException; 
    
    public int GetQuestionNo() throws RemoteException;
    
    public boolean DBAnswers(Question alist[]) throws RemoteException; 
    
    public int GetOptionID(String Option) throws RemoteException;
    
    public boolean AddingQuestion(String NewQuestion) throws RemoteException;
    
    public boolean AddingChoices(String Description, int QID) throws RemoteException;
    
    public boolean DeletingChoices(String choice,int QID)throws RemoteException;
    
    public List<ChartData> getChartData(int qid)throws RemoteException;

}
