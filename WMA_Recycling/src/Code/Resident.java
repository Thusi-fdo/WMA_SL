package Code;

public class Resident {
	
	private String NIC;
	private String ResidentName;
	private String Email;
	private String Password;
	private String City;
	private String Subarea;
	private String Address;
	
	public Resident(String nIC, String residentName, String email, String password, String city, String subarea,
			String address) {
		NIC = nIC;
		ResidentName = residentName;
		Email = email;
		Password = password;
		City = city;
		Subarea = subarea;
		Address = address;
	}


	
	public String getNIC() {
		return NIC;
	}

	public void setNIC(String nIC) {
		NIC = nIC;
	}

	public String getResidentName() {
		return ResidentName;
	}

	public void setResidentName(String residentName) {
		ResidentName = residentName;
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

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getSubarea() {
		return Subarea;
	}

	public void setSubarea(String subarea) {
		Subarea = subarea;
	}

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}
	
	

}
