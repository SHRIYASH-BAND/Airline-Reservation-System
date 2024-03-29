package pojos;

import java.util.Objects;

public class Customer {
	
	private int customer_id;
	private String name;
	private String email;
	private String password;
	private int age;
	private String address;
	private char gender;
	private int mobile;
	
	public Customer() {
	}
		
	
	
	public Customer(int customer_id, String name, String email, String password, int age, String address, char gender,
			int mobile) {
		super();
		this.customer_id = customer_id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.age = age;
		this.address = address;
		this.gender = gender;
		this.mobile = mobile;
	}



	public Customer(String name, String email, String password, int age, String address, char gender, int mobile) {
			super();
			this.name = name;
			this.email = email;
			this.password = password;
			this.age = age;
			this.address = address;
			this.gender = gender;
			this.mobile = mobile;
	}

	

	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public int getMobile() {
		return mobile;
	}
	public void setMobile(int mobile) {
		this.mobile = mobile;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Customer [customer_id=").append(customer_id).append(", name=").append(name).append(", email=")
				.append(email).append(", password=").append(password).append(", age=").append(age).append(", address=")
				.append(address).append(", gender=").append(gender).append(", mobile=").append(mobile).append("]");
		return builder.toString();
	}


	@Override
	public int hashCode() {
		return Objects.hash(customer_id, email);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Customer)) {
			return false;
		}
		Customer other = (Customer) obj;
		return customer_id == other.customer_id && Objects.equals(email, other.email);
	}
	
	
}
