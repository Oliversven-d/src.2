package api;

import java.util.Collection;
import java.util.Date;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

public class HotelResource {
	
	static CustomerService customerService;
	static ReservationService reservationService;
	
	public HotelResource() {
		customerService = CustomerService.getInstance();
		reservationService = ReservationService.getInstance();
	}
	public Customer getCustomer(String email) {
		return customerService.getCustomer(email);
	}
	
	public void createACustomer(String email, String firstName, String lastName) {
		customerService.addcustomer(email, firstName, lastName);
	}
	
	public IRoom getRoom(String roomNumber) {
		return reservationService.getARoom(roomNumber);
	}
	
	public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
		for(Customer customer:customerService.getAllCustomers()) {
			if(customer.getEmail().equals(customerEmail)) {
				return reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
			}
		}
		return null;
	}
	
	public Collection<Reservation> getCustomerReservation(String customerEmail) {
		for(Customer customer:customerService.getAllCustomers()) {
			if(customer.getEmail().equals(customerEmail)) {
				return reservationService.getCustomerReservation(customer);
			}
		}
		return null;
		
	}
	
	public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
		return reservationService.findRooms(checkIn, checkOut);
	}
}
