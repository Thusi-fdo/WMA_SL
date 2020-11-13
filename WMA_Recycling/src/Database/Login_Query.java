package Database;

import Code.Login;

public class Login_Query {
	
	private Database_Connection db;
    
    public Login_Query(){
        db=Database_Connection.getSingleInstance();
    }
    
    public int loginMatch(Login l){
        
        String Query = "select * from Resident";
        
        return db.login(Query,l.getNIC(),l.getPassword());    
    }

}
