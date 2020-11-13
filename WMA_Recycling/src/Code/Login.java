package Code;

public class Login {
	
	private String NIC;
	private String Password;
	public Login(String nIC, String password) {
		NIC = nIC;
		Password = password;
	}
	public String getNIC() {
		return NIC;
	}
	public void setNIC(String nIC) {
		NIC = nIC;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

}
