import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Scanner;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;
public class MainMenu {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		HotelResource hotelResource = new HotelResource();
		while(true) {
			System.out.println("Welcome to the Hotel Reservation Application");
			System.out.println("----------------------------------------------");
			System.out.println("1. Find and reserve a room");
			System.out.println("2. See my reservations");
			System.out.println("3. Create an account");
			System.out.println("4. Admin");
			System.out.println("5. Exit");
			System.out.println("----------------------------------------------");
			System.out.println("Please select a number for the menu option");
			String choice = scanner.next();
			switch(choice) {
			case "1":
				Date checkInDate = new Date();
				Date checkOutDate = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				System.out.println("Enter CheckIn Date mm/dd/yyyy example 02/01/2020");
				String checkIn = scanner.next();
				try {
					checkInDate = formatter.parse(checkIn);
				}
				catch(ParseException ex) {
					ex.printStackTrace();
				}
				System.out.println("Enter CheckOut Date mm/dd/yyyy example 02/01/2020");
				String checkOut = scanner.next();
				try {
					checkOutDate = formatter.parse(checkOut);
				}
				catch(ParseException ex) {
					ex.printStackTrace();
				}
				ArrayList<IRoom> rooms = (ArrayList<IRoom>) hotelResource.findARoom(checkInDate, checkOutDate);
				for(IRoom room: rooms) {
					System.out.println(room);
				}
				System.out.println("Would you like to book a room? y/n");
				String option = scanner.next();
				while(!(option.equals("y") || option.equals("Y") || option.equals("n") || option.equals("N"))) {
					System.out.println("Please enter Y (Yes) or N (No)");
					option = scanner.next();
				}
				if(option.equals("y") || option.equals("Y")) {
					System.out.println("Do you have an account with us? y/n");
					String optionAccount = scanner.next();
					while(!(optionAccount.equals("y") || optionAccount.equals("Y") || optionAccount.equals("n") || optionAccount.equals("N"))) {
						System.out.println("Please enter Y (Yes) or N (No)");
						optionAccount = scanner.next();
					}
					if(optionAccount.equals("y") || optionAccount.equals("Y")) {
						System.out.println("Enter Email format: name@domain.com");
						String email = scanner.next();
						System.out.println("What room number would you like to reserve");
						String number = scanner.next();
						Customer customer = hotelResource.getCustomer(email);
						IRoom room = hotelResource.getRoom(number);
						if(customer == null) {
							System.out.println("Email not found!");
						}
						else if(room == null) {
							System.out.println("Room number not found!");
						}
						else {
							Reservation reservation = hotelResource.bookARoom(email, room, checkInDate, checkOutDate);
							if(reservation == null) {
								System.out.println("Reservation unsuccessful!");
							}
							else {
								System.out.println(reservation);
							}
						}
					}
					else {
						System.out.println("Create account first!");
					}
				}
				
				break;
			case "2":
				System.out.println("Do you have an account with us? y/n");
				String optionAccount = scanner.next();
				while(!(optionAccount.equals("y") || optionAccount.equals("Y") || optionAccount.equals("n") || optionAccount.equals("N"))) {
					System.out.println("Please enter Y (Yes) or N (No)");
					optionAccount = scanner.next();
				}
				if(optionAccount.equals("y") || optionAccount.equals("Y")) {
					System.out.println("Enter Email format: name@domain.com");
					String email = scanner.next();
					//Customer customer = hotelResource.getCustomer(email);
					ArrayList<Reservation> reservations = (ArrayList<Reservation>) hotelResource.getCustomerReservation(email);
					if(reservations == null) {
						System.out.println("No reservation found!");
					}
					else {
						for(Reservation reservation:reservations)
							System.out.println(reservation + "\n");
					}
				}
				else {
					System.out.println("Create account first!");
				}
				break;
			case "3":
				System.out.println("Enter Email format: name@domain.com");
				String email = scanner.next();
				System.out.println("First name");
				String first = scanner.next();
				System.out.println("Last Name");
				String last = scanner.next();
				hotelResource.createACustomer(email, first, last);
				break;
			case "4":
				AdminMenu.displayMenu();
				break;
			case "5":
				System.exit(0);
			default:
				System.out.println("Invalid Input!");
			}
		}
	}

}
