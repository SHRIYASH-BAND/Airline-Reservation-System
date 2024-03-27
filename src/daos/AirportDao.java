package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pojos.Airport;
import pojos.AirportAddress;
import pojos.Flight;

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
	
	public Airport findByEmail(String email)throws Exception {
		String sql = "SELECT * FROM airport WHERE email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					int airport_id = rs.getInt("airport_id"); 
					String name = rs.getString("name");
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

	public int register(Airport airport) {
		
		try(AirportAddressDao airportAddressDao = new AirportAddressDao()){
			if(airportAddressDao.register(airport.getAddress())!=0) {
				String sql = "INSERT INTO airport VALUES(default, ?, ?, ?, ?)";
				try(PreparedStatement stmt = con.prepareStatement(sql)) {
					stmt.setString(1, airport.getName());
					stmt.setString(2, airport.getEmail());
					stmt.setString(3, airport.getPassword());
					AirportAddress airportAddress = airportAddressDao.findByCity(airport.getAddress().getCity());
					stmt.setInt(4, airportAddress.getAddress_id());					
					
					int count = stmt.executeUpdate();
					return count;
				}
			}
			else {
				System.out.println("Enter correct address...");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return 0;
	}

	public List<Airport> getAllAirports(){
		
		List<Airport> airports = new ArrayList<Airport>();
		
		String sql = "SELECT * FROM airport";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int airport_id = rs.getInt("airport_id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					int address_id = rs.getInt("address_id");
					AirportAddress airportAddress = null;
					
					try(AirportAddressDao airportAddressDao = new AirportAddressDao()){
						airportAddress = airportAddressDao.findById(address_id);
						airports.add( new Airport(airport_id, name, email, password, airportAddress));
					}
					catch (Exception e) {
						e.printStackTrace();
						return null;
					}
					
				}
				
				return airports;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return airports;
		}
		
	}

}
