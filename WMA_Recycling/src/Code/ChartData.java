package Code;

import java.io.Serializable;

public class ChartData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1896570197697715635L;
	String Answer;
	int count;
	
	public ChartData(String answer, int count) {
		this.Answer = answer;
		this.count = count;
	}

	public String getAnswer() {
		return this.Answer;
	}

	public void setAnswer(String answer) {
		Answer = answer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
	
}
