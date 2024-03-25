package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pojos.AirportAddress;

public class AirportAddressDao extends Dao {

	public AirportAddressDao() throws Exception {
		super();
		
	}
	
	public AirportAddress findByCity(String city)throws Exception {
		String sql = "SELECT * FROM airport_address WHERE city=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, city);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					int address_id = rs.getInt("address_id");
					String state = rs.getString("state");
					int pincode = rs.getInt("pincode");
					String locality = rs.getString("locality");
					
					return new AirportAddress(address_id, state,  city, pincode,locality);
				}
			}
		}
		return null;
		
	}
	
	public AirportAddress findById(int address_id)throws Exception {
		String sql = "SELECT * FROM airport_address WHERE address_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, address_id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					String state = rs.getString("state");
					String city = rs.getString("city");
					int pincode = rs.getInt("pincode");
					String locality = rs.getString("locality");
					
					return new AirportAddress(address_id, state,  city, pincode,locality);
				}
			}
		}
		return null;
		
	}

}
