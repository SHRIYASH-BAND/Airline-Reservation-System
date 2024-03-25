package pojos;

import java.util.Objects;

import enums.EPaymentStatus;

public class Reservations {
	private int reservation_id;
	private Customer customer_id;
	private FlightSchedule flight_schedule_id;
	private int seat_nos;
	private double fare;
	private EPaymentStatus status;
	
	public Reservations() {
		// TODO Auto-generated constructor stub
	}

	public Reservations(Customer customer_id, FlightSchedule flight_schedule_id, int seat_nos, double fare,
			EPaymentStatus status) {
		super();
		this.customer_id = customer_id;
		this.flight_schedule_id = flight_schedule_id;
		this.seat_nos = seat_nos;
		this.fare = fare;
		this.status = status;
	}

	public Reservations(int reservation_id, Customer customer_id, FlightSchedule flight_schedule_id, int seat_nos,
			double fare, EPaymentStatus status) {
		super();
		this.reservation_id = reservation_id;
		this.customer_id = customer_id;
		this.flight_schedule_id = flight_schedule_id;
		this.seat_nos = seat_nos;
		this.fare = fare;
		this.status = status;
	}

	public int getReservation_id() {
		return reservation_id;
	}

	public void setReservation_id(int reservation_id) {
		this.reservation_id = reservation_id;
	}

	public Customer getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Customer customer_id) {
		this.customer_id = customer_id;
	}

	public FlightSchedule getFlight_schedule_id() {
		return flight_schedule_id;
	}

	public void setFlight_schedule_id(FlightSchedule flight_schedule_id) {
		this.flight_schedule_id = flight_schedule_id;
	}

	public int getSeat_nos() {
		return seat_nos;
	}

	public void setSeat_nos(int seat_nos) {
		this.seat_nos = seat_nos;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public EPaymentStatus getStatus() {
		return status;
	}

	public void setStatus(EPaymentStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reservations [reservation_id=").append(reservation_id).append(", customer_id=")
				.append(customer_id).append(", flight_schedule_id=").append(flight_schedule_id).append(", seat_nos=")
				.append(seat_nos).append(", fare=").append(fare).append(", status=").append(status).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(customer_id, flight_schedule_id, reservation_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Reservations)) {
			return false;
		}
		Reservations other = (Reservations) obj;
		return Objects.equals(customer_id, other.customer_id)
				&& Objects.equals(flight_schedule_id, other.flight_schedule_id)
				&& reservation_id == other.reservation_id;
	}
	
}
