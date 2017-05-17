import java.io.Serializable;


public class Address implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String addressLine01;
	private String county;
	private String country;
	private String postalCode;
	private String phoneNumber;
	private String email;
	
	public Address(String addressLine01, String county, String country,
			String postalCode, String phoneNumber, String email) {
		super();
		this.addressLine01 = addressLine01;
		this.county = county;
		this.country = country;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getAddressLine01() {
		return addressLine01;
	}

	public String getCounty() {
		return county;
	}

	public String getCountry() {
		return country;
	}

	public String getPostalCode() {
		return postalCode;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEmail() {
		return email;
	}
	
}
