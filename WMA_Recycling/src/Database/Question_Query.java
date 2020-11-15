package Database;

import java.util.List;

import Code.Question;

public class Question_Query {
	

		
		private Database_Connection db;
	    
	    public Question_Query(){
	        db=Database_Connection.getSingleInstance();
	    }
	    
	    public List<Question> PrepareQuestions(){
	        
	        String Query="SELECT QID,Question,q_type FROM survey_question";
	        
	        return db.GetQuestions(Query);    
	    }
}
