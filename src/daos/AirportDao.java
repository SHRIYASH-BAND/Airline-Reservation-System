package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import pojos.Airport;
import pojos.AirportAddress;

public class AirportDao extends Dao {

	public AirportDao() throws Exception {
		super();	
	}
	
	public Airport findByAddress(AirportAddress address)throws Exception {
		String sql = "SELECT * FROM airport WHERE address_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, address.getAddress_id());
			try(ResultSet rs = stmt.executeQuery()) {
			
				if(rs.next()) {
					int airport_id = rs.getInt("airport_id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					
					return new Airport(airport_id, name,  email, password,address);
				}
			}
		}
		return null;
		
	}
	
	public Airport findById(int airport_id)throws Exception {
		String sql = "SELECT * FROM airport WHERE airport_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, airport_id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					int address_id = rs.getInt("address_id");
					AirportAddress airportAddress = null;
					
					try(AirportAddressDao airportAddressDao = new AirportAddressDao()){
						airportAddress = airportAddressDao.findById(address_id);
					}
					catch (Exception e) {
						e.printStackTrace();
						return null;
					}
					
					return new Airport(airport_id, name,  email, password,airportAddress);
				}
			}
		}
		return null;
		
	}

}
