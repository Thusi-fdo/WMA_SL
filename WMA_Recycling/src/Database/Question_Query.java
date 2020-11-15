package Database;

import java.util.List;

import Code.Question;

public class Question_Query {
	

		
		private Database_Connection db;
	    
	    public Question_Query(){
	        db=Database_Connection.getSingleInstance();
	    }
	    
	    public List<Question> PrepareQuestions(){
	        
	        String QueryA="SELECT * FROM survey_question";
	        
	        return db.fetchQuestions(QueryA);    
	    }
}
