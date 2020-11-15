package Code;

public class Question {
	private int questionID;
	private String Question;
	private String[] Choices;
	private String Answer;
	
	public Question(int qid, String question, String[] choices) {
		questionID = qid; 
		Question = question;
		Choices = choices;		     
	}
	
	

	public Question(int questionID, String question) {
		this.questionID = questionID;
		Question = question;
	}



	public int getQID() {
		return questionID;
	}

	public void setQID(int qid) {
		questionID = qid;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}

	public String[] getChoices() {
		return Choices;
	}

	public void setChoices(String[] choices) {
		Choices = choices;
	}

	public String getAnswer() {
		return Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}
}
