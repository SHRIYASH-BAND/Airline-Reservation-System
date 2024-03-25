package pojos;

import java.util.Objects;

public class Flight {
	private int flight_id;
	private String flight_number;
	private int seats;
	private double fare;
	private String name;
	private String company;
	
	public Flight() {
		// TODO Auto-generated constructor stub
	}

	public Flight(String flight_number, int seats, double fare, String name, String company) {
		super();
		this.flight_number = flight_number;
		this.seats = seats;
		this.fare = fare;
		this.name = name;
		this.company = company;
	}

	public Flight(int flight_id, String flight_number, int seats, double fare, String name, String company) {
		super();
		this.flight_id = flight_id;
		this.flight_number = flight_number;
		this.seats = seats;
		this.fare = fare;
		this.name = name;
		this.company = company;
	}

	public int getFlight_id() {
		return flight_id;
	}

	public void setFlight_id(int flight_id) {
		this.flight_id = flight_id;
	}

	public String getFlight_number() {
		return flight_number;
	}

	public void setFlight_number(String flight_number) {
		this.flight_number = flight_number;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Flight [flight_id=").append(flight_id).append(", flight_number=").append(flight_number)
				.append(", seats=").append(seats).append(", fare=").append(fare).append(", name=").append(name)
				.append(", company=").append(company).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(flight_id, flight_number);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Flight)) {
			return false;
		}
		Flight other = (Flight) obj;
		return flight_id == other.flight_id && Objects.equals(flight_number, other.flight_number);
	}
	
	
}
