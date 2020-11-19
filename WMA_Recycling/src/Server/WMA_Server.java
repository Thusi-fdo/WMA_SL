package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class WMA_Server {

	public static void main(String[] args)
	{
		try {
			Registry reg = LocateRegistry.createRegistry(1968); //Server port 1069
			Questionnaire_Service QS = new Questionnaire_Service();
			reg.rebind("QuestionServer", QS);
			}
		catch (RemoteException e) {
            System.out.println("A problem encountered: "+e.toString());
            e.printStackTrace();
            
        }
	}

}
