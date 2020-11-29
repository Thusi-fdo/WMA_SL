package Code;

import java.io.Serializable;

public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2762714855416447173L;
	
	private int questionID;
	private String Question;
	private String[] Choices;
	private String Answer;
	private int optionID;
	
	public Question(int qid, String question, String[] choices) {
		questionID = qid; 
		Question = question;
		Choices = choices;		     
	}
	
	

	public Question(int questionID, String answer,int optionID) {
		this.questionID = questionID;
		Answer = answer;
		this.optionID=optionID;
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



	public int getOptionID() {
		return optionID;
	}



	public void setOptionID(int optionID) {
		this.optionID = optionID;
	}
	
	
}
