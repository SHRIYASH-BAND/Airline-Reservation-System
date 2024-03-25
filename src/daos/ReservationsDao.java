package daos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import enums.EPaymentStatus;
import pojos.Customer;
import pojos.FlightSchedule;
import pojos.Reservations;

public class ReservationsDao extends Dao {

	public ReservationsDao() throws Exception {
		super();
	
	}
	
	public int bookReservation(Reservations reservation) throws SQLException {
		
		String sql = "INSERT INTO reservations VALUES(default, ?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, reservation.getCustomer_id().getCustomer_id());
			stmt.setInt(2, reservation.getFlight_schedule_id().getFlight_schedule_id());
			stmt.setInt(3, reservation.getSeat_nos());
			stmt.setDouble(4, reservation.getFare());
			stmt.setString(5, reservation.getStatus().toString() );
	
			int count = stmt.executeUpdate();
			return count;
		}
	}
	
	public List<Reservations> getByCustomerId(Customer customer) throws SQLException{
		String sql = "SELECT * FROM reservations WHERE customer_id=?";
		ArrayList<Reservations> reservations = new ArrayList<Reservations>();
		
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, customer.getCustomer_id());
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int reservation_id = rs.getInt("reservation_id");
					int flight_schedule_id = rs.getInt("flight_schedule_id");
					int seat_nos = rs.getInt("seat_nos");
					int fare = rs.getInt("fare");
					EPaymentStatus payment_status = EPaymentStatus.valueOf(rs.getString("payment_status"));
					
					FlightSchedule flightSchedule = null;
					try(FlightScheduleDao flightScheduleDao = new FlightScheduleDao()){
						flightSchedule = flightScheduleDao.findById(flight_schedule_id);
						reservations.add( new Reservations(reservation_id, customer, flightSchedule, seat_nos, fare, payment_status));
					}
					catch (Exception e) {
						e.printStackTrace();
						return reservations;
					}
				
				}
			}
		}
		return reservations;
	}

}
