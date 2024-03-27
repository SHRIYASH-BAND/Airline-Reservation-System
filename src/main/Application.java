package main;

import java.util.Scanner;

import daos.CustomerDao;
import pojos.Airport;
import pojos.Customer;
import services.AirportService;
import services.CustomerService;

public class Application {
	public static Scanner sc;
	
	public static int printOptions() {
		
		System.out.println("======================================================================================");
		System.out.println("0. Exit");
		System.out.println("1. Customer Sign Up");
		System.out.println("2. Customer Sign In");
		System.out.println("3. Airport Sign Up");
		System.out.println("4. Airport Sign In");
		System.out.println("======================================================================================");
		System.out.print("Enter choice: ");
		return sc.nextInt();
	}
	
	public static int printCustomerOptions() {
		System.out.println("======================================================================================");
		System.out.println("0. Sign Out");
		System.out.println("1. View flight schedules");
		System.out.println("2. View booked tickets");
		System.out.println("3. Book ticket");
		System.out.println("======================================================================================");
		System.out.print("Enter choice: ");
		return sc.nextInt();
		
	}
	
	public static int printAirportOptions() {
		System.out.println("======================================================================================");
		System.out.println("0. Sign Out");
		System.out.println("1. View schedules");
		System.out.println("2. Schedule flight");
		System.out.println("======================================================================================");
		System.out.print("Enter choice: ");
		return sc.nextInt();
		
	}
	
	public static void customerOperations(CustomerService customerService, Customer customer) {
						
			int choice;
			
			do {
				System.out.println("Welcome "+ customer.getName());
				choice = printCustomerOptions();
				switch (choice) {
				case 0:	
					
					System.out.println("Log Out Successfully.....");
					break;
					
				case 1: 	
					customerService.viewFlightSchedule();
					break;

				case 2: 	
					customerService.viewBookedTickets(customer);
					break;
					
				case 3: 	
					customerService.bookTicket(customer);
					break;
					
				default:
					System.out.println("Wrong Input Entered....");
					break;
				}
			}while(choice != 0);
			
	}
	
	public static void airportOperations(AirportService airportService, Airport airport) {
		
		int choice;
		
		do {
			System.out.println("Welcome "+ airport.getName());
			choice = printAirportOptions();
			switch (choice) {
			case 0:	
				System.out.println("Log Out Successfully.....");
				break;
				
			case 1: 	
				airportService.viewFlightSchedule(airport);
				break;

			case 2: 	
				airportService.scheduleFlights(airport);
				break;
				
			default:
				System.out.println("Wrong Input Entered....");
				break;
			}
		}while(choice != 0);
		
}
	
	public static void Operations() {
		int choice;
		Customer customer = null;
		Airport airport =null;
		CustomerService customerService = null;
		AirportService airportService = null;
		
		try {
			customerService = new CustomerService();
			airportService = new AirportService();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		do {
			choice = printOptions();
			
			switch (choice) {
			case 0: // Exit					
					System.out.println("Application Exited Successfully.....");
				
				break;
				
			case 1: //Customer Sign Up	
				customerService.register();
				break;
				
			case 2: //Customer Sign In	
				customer = customerService.login();
				if(customer != null) {
					customerOperations(customerService,customer);
				}
				else {
					System.out.println("User Not Found.....");
				}
				break;
				
			case 3: //Airport Sign Up	
				airportService.register();
				break;	
				
			case 4: //Airport Sign In	
				airport = airportService.login();
				if(airport != null) {
					airportOperations(airportService,airport);
				}
				else {
					System.out.println("Airport Not Found.....");
				}
				break;	
			default:
				System.out.println("Wrong Input Entered....");
				break;
			}
		}while(choice != 0);
		
		if(customerService!=null || airportService!=null) {
			try {
				if(customerService!=null) customerService.close();
				if(airportService!=null) airportService.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
			
	}
	
	public static void main(String[] args) {
		sc = new Scanner(System.in);
		Operations();
	}

}
