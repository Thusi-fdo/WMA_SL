package Code;

public class Question {
	private String questionID;
	private String Question;
	private String[] Choices;
	private String Answer;
	
	public Question(String qid, String question, String[] choices, String answer) {
		questionID = qid; 
		Question = question;
		Choices = choices;
		Answer = answer;     
	}

	public String getQID() {
		return questionID;
	}

	public void setQID(String qid) {
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
