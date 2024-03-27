package services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import daos.AirportDao;
import daos.CustomerDao;
import daos.FlightDao;
import daos.FlightScheduleDao;
import enums.EFlightScheduleStatus;
import pojos.Airport;
import pojos.AirportAddress;
import pojos.Customer;
import pojos.Flight;
import pojos.FlightSchedule;

public class AirportService implements AutoCloseable{
	// login 
	// register
	// view current schedules
	// schedule flight
	
	Scanner sc = null;
	
	public AirportService() {
		sc = new Scanner(System.in);
		
	}
	
	@Override
	public void close()  {
		if(sc!=null)
		{
			try {
				sc.close();
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private LocalDateTime getTime() {
		System.out.print("Enter Year: ");
		int year = sc.nextInt();
		System.out.print("Enter Month: ");
		int month = sc.nextInt();
		System.out.print("Enter Day: ");
		int day = sc.nextInt();
		System.out.print("Enter Hours: ");
		int hours = sc.nextInt();
		System.out.print("Enter Minutes: ");
		int minutes = sc.nextInt();
		System.out.print("Enter Seconds: ");
		int seconds = sc.nextInt();					
		LocalDateTime time = LocalDateTime.of(year, month, day, hours, minutes, seconds);
		
		return time;
	}
	
	public Airport login() {
		
		
		String email;
		String password;
		System.out.print("Enter email: ");
		email = sc.next();
		System.out.print("Enter password: ");
		password = sc.next();
		
		try(AirportDao airportDao = new AirportDao()) {
			Airport airport = airportDao.findByEmail(email);
			if(airport != null && password.equals(airport.getPassword())) {
				System.out.println("Airport Logged in.....");
				return airport;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void register() {
		System.out.print("Name: ");
		String name = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Password: ");
		String password = sc.next();
		System.out.print("State: ");
		String state = sc.next();
		System.out.print("City: ");
		String city = sc.next();
		System.out.print("Pincode: ");
		int pincode = sc.nextInt();
		System.out.print("Locality: ");
		String locality = sc.next();
		
		AirportAddress airportAddress = new AirportAddress(state, city, pincode, locality);
		Airport airport = new Airport(name, email, password, airportAddress);
		
		
		try(AirportDao airportDao = new AirportDao()) {
			int count = airportDao.register(airport);
			if(count>0) System.out.println("Airport registered: " + count);
			else System.out.println("Failed to register....");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public void viewFlightSchedule(Airport airport) {
		
		List<FlightSchedule> schedules = null;
		try(FlightScheduleDao flightScheduleDao = new FlightScheduleDao()) {
			schedules = flightScheduleDao.listScheduledFlightsByAirport(airport);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		if(schedules==null || schedules.size()==0) {
			System.out.println("\n\n\n======================================================================================");
			System.out.println("No Flight Schedules.....");
			System.out.println("======================================================================================\n\n\n");
		}
		else {
			for (FlightSchedule flightSchedule : schedules) {
				System.out.println("\n\n========================== Airport Schedule =================================");
				System.out.println("Schedule No : "+ flightSchedule.getFlight_schedule_id());
				System.out.println("Source : "+ flightSchedule.getSource_airport().getName());
				System.out.println("Destinaton : "+ flightSchedule.getDestination_airport().getName());
				System.out.println("Departure Time : "+ flightSchedule.getDeparture_date_time());
				System.out.println("Arrival Time : "+ flightSchedule.getArrival_date_time());
				System.out.println("Available Seats : "+ flightSchedule.getAvailable_seats());
				System.out.println("===== Flight Details =====");
				System.out.println("Flight : "+ flightSchedule.getFlight_id().getName());
				System.out.println("Fare : "+ flightSchedule.getFlight_id().getFare());
				System.out.println("======================================================================================\n\n");
			}
		}
		
	}
	
	public void scheduleFlights(Airport sourceAirport) {
		
		try(FlightDao flightDao = new FlightDao()){
			// list all flights
			System.out.println("Choose Flight from below flights: ");
			List<Flight> flights = flightDao.getAllFlights();
			for (Flight flight : flights) {
				System.out.println("===== Flight Details =====");
				System.out.println("Flight ID : "+ flight.getFlight_id());
				System.out.println("Flight : "+ flight.getName());
				System.out.println("Owner Company : "+ flight.getCompany());
				System.out.println("Number : "+ flight.getFlight_number());
				System.out.println("Total Seats : "+ flight.getSeats());
				System.out.println("Fare : "+ flight.getFare());
				System.out.println("===========================\n");
			}
			System.out.println("Enter Flight ID to be scheduled : ");
			int flight_id = sc.nextInt();
			
				// list all airports
				try(AirportDao airportDao = new AirportDao()){
					System.out.println("Choose Destination Airport from below airports: ");
					List<Airport> airports = airportDao.getAllAirports();
					for (Airport airport : airports) {
						System.out.println("===== Airport Details =====");
						System.out.println("Airport ID : "+ airport.getAirport_id());
						System.out.println("Name : "+ airport.getName());
						System.out.println("State : "+ airport.getAddress().getState());
						System.out.println("City : "+ airport.getAddress().getCity());
						System.out.println("Pincode : "+ airport.getAddress().getPincode());
						System.out.println("Locality : "+ airport.getAddress().getLocality());
						System.out.println("===========================\n");
					}
					System.out.println("Enter Airport ID to be scheduled : ");
					int airport_id = sc.nextInt();
					
					// schedule flight
					Flight flight = flights.get(flight_id - 1);
					Airport destinationAirport = airports.get(airport_id - 1);
					
					System.out.println("Enter Departure Time : ");
					LocalDateTime departure = getTime();
					System.out.println("Enter Arrival Time : ");
					LocalDateTime arrival = getTime();
					FlightSchedule flightSchedule = new FlightSchedule(departure, arrival, sourceAirport, destinationAirport, flight, flight.getSeats(), EFlightScheduleStatus.S);
					
					try(FlightScheduleDao flightScheduleDao = new FlightScheduleDao()){
						int count = flightScheduleDao.scheduleFlight(flightSchedule);
						if(count>0)
							System.out.println("Flight Scheduled....");
					}
					
				}catch (Exception e) {
				e.printStackTrace();
				}
			
			
		}catch (Exception e) {
		e.printStackTrace();
		}
	}

	
	
}
