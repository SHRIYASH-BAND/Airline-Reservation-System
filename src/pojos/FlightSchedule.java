package pojos;

import java.time.LocalDateTime;
import java.util.Objects;

import enums.EFlightScheduleStatus;

public class FlightSchedule {
	
	private int flight_schedule_id;
	private LocalDateTime departure_date_time;
	private LocalDateTime arrival_date_time ;
	private Airport source_airport ;
	private Airport destination_airport ;
	private Flight flight_id;
	private int available_seats;
	private EFlightScheduleStatus status;
	
	public FlightSchedule() {
		// TODO Auto-generated constructor stub
	}

	public FlightSchedule(LocalDateTime departure_date_time, LocalDateTime arrival_date_time, Airport source_airport,
			Airport destination_airport, Flight flight_id, int available_seats, EFlightScheduleStatus status) {
		super();
		this.departure_date_time = departure_date_time;
		this.arrival_date_time = arrival_date_time;
		this.source_airport = source_airport;
		this.destination_airport = destination_airport;
		this.flight_id = flight_id;
		this.available_seats = available_seats;
		this.status = status;
	}

	public FlightSchedule(int flight_schedule_id, LocalDateTime departure_date_time, LocalDateTime arrival_date_time,
			Airport source_airport, Airport destination_airport, Flight flight_id, int available_seats,
			EFlightScheduleStatus status) {
		super();
		this.flight_schedule_id = flight_schedule_id;
		this.departure_date_time = departure_date_time;
		this.arrival_date_time = arrival_date_time;
		this.source_airport = source_airport;
		this.destination_airport = destination_airport;
		this.flight_id = flight_id;
		this.available_seats = available_seats;
		this.status = status;
	}

	public int getFlight_schedule_id() {
		return flight_schedule_id;
	}

	public void setFlight_schedule_id(int flight_schedule_id) {
		this.flight_schedule_id = flight_schedule_id;
	}

	public LocalDateTime getDeparture_date_time() {
		return departure_date_time;
	}

	public void setDeparture_date_time(LocalDateTime departure_date_time) {
		this.departure_date_time = departure_date_time;
	}

	public LocalDateTime getArrival_date_time() {
		return arrival_date_time;
	}

	public void setArrival_date_time(LocalDateTime arrival_date_time) {
		this.arrival_date_time = arrival_date_time;
	}

	public Airport getSource_airport() {
		return source_airport;
	}

	public void setSource_airport(Airport source_airport) {
		this.source_airport = source_airport;
	}

	public Airport getDestination_airport() {
		return destination_airport;
	}

	public void setDestination_airport(Airport destination_airport) {
		this.destination_airport = destination_airport;
	}

	public Flight getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(Flight flight_id) {
		this.flight_id = flight_id;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public EFlightScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(EFlightScheduleStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FlightSchedule [flight_schedule_id=").append(flight_schedule_id)
				.append(", departure_date_time=").append(departure_date_time).append(", arrival_date_time=")
				.append(arrival_date_time).append(", source_airport=").append(source_airport)
				.append(", destination_airport=").append(destination_airport).append(", flight_id=").append(flight_id)
				.append(", available_seats=").append(available_seats).append(", status=").append(status).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(flight_id, flight_schedule_id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof FlightSchedule)) {
			return false;
		}
		FlightSchedule other = (FlightSchedule) obj;
		return flight_id == other.flight_id && flight_schedule_id == other.flight_schedule_id;
	}
	
	

}
