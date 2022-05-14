import java.util.Scanner;

import api.AdminResource;
import model.Customer;
import model.FreeRoom;
import model.IRoom;
import model.Reservation;
import model.Room;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

public class AdminMenu {
	public static void displayMenu() {
		Scanner scanner = new Scanner(System.in);
		AdminResource admin = new AdminResource();
		while(true) {
			System.out.println("\nAdmin Menu");
			System.out.println("----------------------------------------------");
			System.out.println("1. See all Customers");
			System.out.println("2. See all Rooms");
			System.out.println("3. See all Reservations");
			System.out.println("4. Add a Room");
			System.out.println("5. Back to Main Menu");
			System.out.println("----------------------------------------------");
			System.out.println("Please select a number for the menu option");
			String choice = scanner.next();
			switch(choice) {
			case "1":
				for(Customer customer:admin.getAllCustomers()) {
					System.out.println(customer);
					System.out.println();
				}
				break;
			case "2":
				for(IRoom room:admin.getAllRooms()) {
					System.out.println(room);
					System.out.println();
				}
				break;
			case "3":
				admin.desiplayAllReservations();
				break;
			case "4":
			{
				String option = "y";
				while(option.equals("Y") || option.equals("y")) {
					System.out.println("Enter room number");
					String number = scanner.next();
					System.out.println("Enter price per night");
					String price = scanner.next();
					System.out.println("Enter room type: 1 for single bed, 2 for double bed");
					String type = scanner.next();
					IRoom room = null;
					if(price == "0") {
						if(type.equals("1")) {
							room = new FreeRoom(number, RoomType.SINGLE);
						}
						else {
							room = new FreeRoom(number, RoomType.DOUBLE);
						}
					}
					else  {
						if(type.equals("1")) {
							room = new Room(number, Double.parseDouble(price), RoomType.SINGLE);
						}
						else {
							room = new Room(number, Double.parseDouble(price), RoomType.DOUBLE);
						}
					}
					admin.getReservationService().addRoom(room);
					System.out.println("Would you like to add another room y/n");
					option = scanner.next();
					while(!(option.equals("y") || option.equals("y") || option.equals("n") || option.equals("N"))) {
						System.out.println("Please enter Y (Yes) or N (No)");
						option = scanner.next();
					}
				}
			}
				break;
			case "5":
				return;
			default:
				System.out.println("Invalid Input!");
			}
		}
	}
}
