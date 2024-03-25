package pojos;

import java.util.Objects;

public class Airport {
	private int airport_id;
	private String name;
	private String email;
	private String password;
	private AirportAddress address;
	
	public Airport() {
		// TODO Auto-generated constructor stub
	}

	public Airport(String name, String email, String password, AirportAddress address) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public Airport(int airport_id, String name, String email, String password, AirportAddress address) {
		super();
		this.airport_id = airport_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.address = address;
	}

	public int getAirport_id() {
		return airport_id;
	}

	public void setAirport_id(int airport_id) {
		this.airport_id = airport_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AirportAddress getAddress() {
		return address;
	}

	public void setAddress(AirportAddress address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, airport_id, email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Airport)) {
			return false;
		}
		Airport other = (Airport) obj;
		return Objects.equals(address, other.address) && airport_id == other.airport_id
				&& Objects.equals(email, other.email) && Objects.equals(name, other.name)
				&& Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Airport [airport_id=").append(airport_id).append(", name=").append(name).append(", email=")
				.append(email).append(", password=").append(password).append(", address=").append(address).append("]");
		return builder.toString();
	}
	
}
