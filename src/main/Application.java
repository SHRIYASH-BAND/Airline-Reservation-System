package main;

import java.util.Scanner;

import daos.CustomerDao;
import pojos.Customer;
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
	
	
	public static void Operations() throws Exception {
		int choice;
		Customer customer = null;
		CustomerService customerService = null;
		
		try {
			customerService = new CustomerService();			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		do {
			
			choice = printOptions();
			switch (choice) {
			case 0: // Exit
					if(customerService!=null)
						customerService.close();
					
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
			default:
				System.out.println("Wrong Input Entered....");
				break;
			}
		}while(choice != 0);
	}
	

	public static void main(String[] args) throws Exception  {
		sc = new Scanner(System.in);
		Operations();
	}

}
