package pojos;

import java.util.Objects;

public class AirportAddress {
	private int address_id;
	private String state;
	private String city;
	private int pincode;
	private String locality;
	
	public AirportAddress() {
		// TODO Auto-generated constructor stub
	}

	public AirportAddress(String state, String city, int pincode, String locality) {
		super();
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.locality = locality;
	}

	public AirportAddress(int address_id, String state, String city, int pincode, String locality) {
		super();
		this.address_id = address_id;
		this.state = state;
		this.city = city;
		this.pincode = pincode;
		this.locality = locality;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AirportAddress [address_id=").append(address_id).append(", state=").append(state)
				.append(", city=").append(city).append(", pincode=").append(pincode).append(", locality=")
				.append(locality).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(city, locality, pincode);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AirportAddress)) {
			return false;
		}
		AirportAddress other = (AirportAddress) obj;
		return Objects.equals(city, other.city) && Objects.equals(locality, other.locality) && pincode == other.pincode;
	}
	
	
	
	
}
