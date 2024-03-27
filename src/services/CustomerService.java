package services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import daos.AirportAddressDao;
import daos.AirportDao;
import daos.CustomerDao;
import daos.FlightScheduleDao;
import daos.ReservationsDao;
import enums.EPaymentStatus;
import pojos.Airport;
import pojos.AirportAddress;
import pojos.Customer;
import pojos.FlightSchedule;
import pojos.Reservations;

	//register 
	// login
	// book ticket
	// view flight schedules
	// view booked tickets
public class CustomerService implements AutoCloseable {
	
	private Scanner sc;
	
	public CustomerService() throws Exception {
		sc = new Scanner(System.in);
	}
	
	@Override
	public void close() throws Exception {
		try {
			if (sc != null)
				sc.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void register() {
		System.out.print("Name: ");
		String name = sc.next();
		System.out.print("Email: ");
		String email = sc.next();
		System.out.print("Password: ");
		String passwd = sc.next();
		System.out.print("Age: ");
		int age = sc.nextInt();
		System.out.print("Address: ");
		String address = sc.next();
		System.out.print("Gender [M/F/O]: ");
		char gender = sc.next().charAt(0);
		System.out.print("Mobile: ");
		int mobile = sc.nextInt();
		
		
		Customer customer = new Customer(name, email, passwd,age, address,gender ,mobile);
		
		try(CustomerDao customerDao = new CustomerDao()) {
			int count = customerDao.save(customer);
			System.out.println("User registered: " + count);
		
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	public Customer login() {
		String email;
		String password;
		System.out.print("Enter email: ");
		email = sc.next();
		System.out.print("Enter password: ");
		password = sc.next();
		
		try(CustomerDao customerDao = new CustomerDao()) {
			Customer customer = customerDao.findByEmail(email);
			if(customer != null && password.equals(customer.getPassword())) {
				System.out.println("User Logged in.....");
				return customer;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int viewFlightSchedule() {
		String sourceCity;
		String destinationCity;
		
		System.out.print("Enter source city: ");
		sourceCity = sc.next();
		System.out.print("Enter destination city: ");
		destinationCity = sc.next();
		
		List<FlightSchedule> schedules = null;
		
		try(
				FlightScheduleDao flightScheduleDao = new FlightScheduleDao();
				AirportDao airportDao = new AirportDao();
				AirportAddressDao airportAddressDao = new AirportAddressDao();
		) {
			
			AirportAddress sourceAirportAddress = airportAddressDao.findByCity(sourceCity); 
			
			if(sourceAirportAddress==null) {
				System.out.println("City not Available");
				return -1; 
			}
			
			Airport sourceAirport = airportDao.findByAddress(sourceAirportAddress );
			
			AirportAddress destinationAirportAddress =airportAddressDao.findByCity(destinationCity) ; 
			
			if(destinationAirportAddress==null) {
				System.out.println("City not Available");
				return -1; 
			}
			
			Airport destinationAirport = airportDao.findByAddress(destinationAirportAddress);
			
			schedules = flightScheduleDao.listScheduledFlightsByAirport(sourceAirport,destinationAirport);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		if(schedules==null || schedules.size()==0) {
			System.out.println("\n\n\n======================================================================================");
			System.out.println("No Flight Schedules.....");
			System.out.println("======================================================================================\n\n\n");
			return -1;
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
		
		return 0;
	}
	
	public void bookTicket(Customer customer) {
		
		if(this.viewFlightSchedule()==0) {
	
		System.out.print("Enter Schedule No to book the ticket: ");
		int schedule_id = sc.nextInt();
		System.out.print("Enter no of tickets you want to book : ");
		int seat_nos = sc.nextInt();
		
		try(FlightScheduleDao flightScheduleDao = new FlightScheduleDao(); ReservationsDao reservationsDao = new ReservationsDao() ){
			FlightSchedule flightSchedule = flightScheduleDao.findById(schedule_id);
			
			double fare = flightSchedule.getFlight_id().getFare() * seat_nos;
			Reservations reservation = new Reservations(customer, flightSchedule, seat_nos,fare, EPaymentStatus.D); 
			int affected = reservationsDao.bookReservation(reservation);			
			int count = flightScheduleDao.updateAvailableSeats(flightSchedule.getFlight_schedule_id(), flightSchedule.getAvailable_seats() - seat_nos);
			System.out.println("Booking Confirmed........");
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		}
	}
	
	public void viewBookedTickets(Customer customer) {
		List<Reservations> reservations = null;
		
		try(ReservationsDao reservationsDao = new ReservationsDao()){
			reservations = reservationsDao.getByCustomerId(customer);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(reservations==null || reservations.size()==0) {
			System.out.println("\n\n\n======================================================================================");
			System.out.println("No Reservations Yet.....");
			System.out.println("======================================================================================\n\n\n");
			
		}
		else {
			for (Reservations reservation : reservations) {
				System.out.println("\n\n========================== Booking Schedule =================================");
				System.out.println("Source : "+ reservation.getFlight_schedule_id().getSource_airport().getName());
				System.out.println("Destinaton : "+  reservation.getFlight_schedule_id().getDestination_airport().getName());
				System.out.println("Departure Time : "+ reservation.getFlight_schedule_id().getDeparture_date_time());
				System.out.println("Arrival Time : "+ reservation.getFlight_schedule_id().getArrival_date_time());
				System.out.println("===== Flight Details =====");
				System.out.println("Flight : "+ reservation.getFlight_schedule_id().getFlight_id().getName());
				System.out.println("No of seat : "+ reservation.getSeat_nos());
				System.out.println("Fare per Seat : "+ reservation.getFlight_schedule_id().getFlight_id().getFare());
				System.out.println("Total Fare : "+ reservation.getFare());
				System.out.println("======================================================================================\n\n");
			}
		}
	}

	

}
