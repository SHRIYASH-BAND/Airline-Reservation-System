package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import pojos.Airport;
import pojos.AirportAddress;
import pojos.Flight;

public class FlightDao extends Dao {

	public FlightDao() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Flight findById(int flight_id)throws Exception {
		String sql = "SELECT * FROM flight WHERE flight_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, flight_id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					String flight_number = rs.getString("flight_number");
					int seats = rs.getInt("seats");
					double fare = rs.getDouble("fare");
					String name = rs.getString("name");
					String company = rs.getString("company");
					
					
					return new Flight(flight_id,flight_number,seats,fare, name,  company);
				}
			}
		}
		return null;
		
	}

	public List<Flight> getAllFlights() throws Exception{
		List<Flight> flights = new ArrayList<Flight>();
		
		String sql = "SELECT * FROM flight";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int flight_id = rs.getInt("flight_id"); 
					String flight_number = rs.getString("flight_number");
					int seats = rs.getInt("seats");
					double fare = rs.getInt("fare");
					String name = rs.getString("name");
					String company = rs.getString("company");
					
					flights.add( new Flight(flight_id, flight_number, seats, fare, name, company));
				}
				
				return flights;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			return flights;
		}
		
	}
}
