package Code;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Resident implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5655719722214019601L;
	static String NIC;
	private String ResidentName;
	private String Email;
	private String Password;
	private String City;
	private String Subarea;
	private String Address;  // attributes 
	
	public Resident( String residentName, String email, String password, String city, String subarea,
			String address) {
		
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
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

		 
         Matcher matcher = pattern.matcher(email);
         return matcher.find();
	}
	public static boolean ValidateNIC(String nic) {
		//String NICRegex ="^(?=.*[0-9]){12}$" ; //| "^[0-9]{9}[vVxX]$"
		//.trim().matches("^[0-9]{9}[vVxX]$")
		
		return nic.trim().matches("^[0-9]{9}[vVxX]|[0-9]{12}$");
	}
	public static boolean ValidatePW(String pw) {
		String PWRegex = "^(?=.*[0-9])"
						+ "(?=.*[a-z])(?=.*[A-Z])"
						+ "(?=.*[@#$%^&+=])"
						+ "(?=\\S+$).{8,20}$";  
		
		return pw.trim().matches(PWRegex);
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
