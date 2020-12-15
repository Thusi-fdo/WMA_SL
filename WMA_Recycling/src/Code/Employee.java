package Code;

import java.io.Serializable;

public class Employee implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6544663515947837286L;
	private static int eID;
	private String EmployeeName;
	private String Position;
	private String Email;
	private String Password;
	
	public Employee(int eID, String employeeName, String position, String email, String password) {
		this.eID = eID;
		EmployeeName = employeeName;
		Position = position;
		Email = email;
		Password = password;
	}

	public int geteID() {
		return eID;
	}

	public static void seteID(int eID) {
		eID = eID;
	}

	public String getEmployeeName() {
		return EmployeeName;
	}

	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}

	public String getPosition() {
		return Position;
	}

	public void setPosition(String position) {
		Position = position;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
	
	
}
