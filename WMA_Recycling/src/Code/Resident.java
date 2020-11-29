package Code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resident {
	
	private static String NIC;
	private String ResidentName;
	private String Email;
	private String Password;
	private String City;
	private String Subarea;
	private String Address;  // attributes 
	
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


	
	public static String getNIC() {
		return NIC;
	}

	public static void setNIC(String nIC) {
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

	public static boolean valEmail(String email) 
	{
		String emailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\\\.[A-Z]{2,6}$";
		Pattern emailPat = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
		Matcher matcher = emailPat.matcher(email);
		return matcher.find();
	}

	//valid pass. and more
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
