package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import enums.EFlightScheduleStatus;
import pojos.Airport;
import pojos.AirportAddress;
import pojos.Flight;
import pojos.FlightSchedule;

public class FlightScheduleDao extends Dao {

	public FlightScheduleDao() throws Exception {
		super();
		
	}
	
	public List<FlightSchedule> listScheduledFlightsByAddress(Airport sourceAirport,Airport destinationAirport)throws Exception {
		String sql = "SELECT * FROM flight_schedule WHERE source_airport=? and destination_airport=?";
		ArrayList<FlightSchedule> schedules = new ArrayList<FlightSchedule>();
		
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, sourceAirport.getAirport_id());
			stmt.setInt(2, destinationAirport.getAirport_id());
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int flight_schedule_id = rs.getInt("flight_schedule_id");
					LocalDateTime departure_date_time = rs.getTimestamp("departure_date_time").toLocalDateTime();
					LocalDateTime arrival_date_time = rs.getTimestamp("arrival_date_time").toLocalDateTime();
					int flight_id = rs.getInt("flight_id");
					int available_seats = rs.getInt("available_seats");
					EFlightScheduleStatus status = EFlightScheduleStatus.valueOf(rs.getString("status"));
					
					Flight flight = null;
					try(FlightDao flightDao = new FlightDao()){
						flight = flightDao.findById(flight_id);
					}
					catch (Exception e) {
						e.printStackTrace();
						return schedules;
					}
					
					
					schedules.add( new FlightSchedule(flight_schedule_id, departure_date_time,arrival_date_time,sourceAirport, destinationAirport, flight, available_seats,status) );
				}
			}
		}
		return schedules;
		
	}
	
	public FlightSchedule findById(int flight_schedule_id)throws Exception {
		String sql = "SELECT * FROM flight_schedule WHERE flight_schedule_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, flight_schedule_id);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
	
					LocalDateTime departure_date_time = rs.getTimestamp("departure_date_time").toLocalDateTime();
					LocalDateTime arrival_date_time = rs.getTimestamp("arrival_date_time").toLocalDateTime();
					int source_airport_id = rs.getInt("source_airport");
					int destination_airport_id = rs.getInt("destination_airport");
					int flight_id = rs.getInt("flight_id");
					int available_seats = rs.getInt("available_seats");
					EFlightScheduleStatus status = EFlightScheduleStatus.valueOf(rs.getString("status"));
					
				
					try(
							FlightDao flightDao = new FlightDao();
							AirportDao airportDao = new AirportDao();
	
						){
						Airport sourceAirport = airportDao.findById(source_airport_id) ;
						Airport destinationAirport = airportDao.findById(destination_airport_id);
						Flight flight = flightDao.findById(flight_id);
						
						return new FlightSchedule(flight_schedule_id, departure_date_time,arrival_date_time,sourceAirport, destinationAirport, flight, available_seats,status);
					}
					catch (Exception e) {
						e.printStackTrace();
						return null;
					}
				}
			}
		}
		return null;
		
	}
	
	public int updateAvailableSeats(int flight_schedule_id, int available_seats) throws SQLException {
		
		String sql = "UPDATE flight_schedule SET available_seats=? WHERE flight_schedule_id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {

			stmt.setInt(1, available_seats);
			stmt.setInt(2, flight_schedule_id);
			
			int count = stmt.executeUpdate();
			return count;
		}
	}
	

}
