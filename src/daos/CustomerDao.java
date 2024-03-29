package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pojos.Customer;

public class CustomerDao extends Dao {

	public CustomerDao() throws Exception {
		super();
	}
	
	public int save(Customer c) throws Exception {
		String sql = "INSERT INTO customer VALUES(default, ?, ?, ?, ?, ?, ?, ?)";
		// ? named/ optional prameters; IN OUT INOUT DBMS
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, c.getName());
			stmt.setString(2, c.getEmail());
			stmt.setString(3, c.getPassword());
			stmt.setInt(4, c.getAge());
			stmt.setString(5, c.getAddress());
			stmt.setString(6, String.valueOf(c.getGender()));
			stmt.setInt(7, c.getMobile());
			
			int count = stmt.executeUpdate();
			return count;
		}
	}
	
	public Customer findByEmail(String email) throws Exception {
		String sql = "SELECT * FROM customer WHERE email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					int id = rs.getInt("customer_id");
					String name = rs.getString("name");
					String password = rs.getString("password");
					int age = rs.getInt("age");
					String address = rs.getString("address");
					char gender = rs.getString("gender").charAt(0);
					int mobile = rs.getInt("mobile");
					
					return new Customer(id, name,  email, password,age,address,gender,mobile);
				}
			}
		}
		return null;
	}

}
