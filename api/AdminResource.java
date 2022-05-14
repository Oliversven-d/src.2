package api;

import java.util.Collection;
import java.util.List;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

public class AdminResource {
	static CustomerService customerService;
	static ReservationService reservationService;
	
	public AdminResource() {
		customerService = CustomerService.getInstance();
		reservationService = ReservationService.getInstance();
	}
	
	public CustomerService getCustomerService() {
		return customerService;
	}
	public ReservationService getReservationService() {
		return reservationService;
	}
	public Customer getCustomer(String email) {
		return customerService.getCustomer(email);
	}
	
	public void addRoom(List<IRoom> rooms) {
		for(IRoom room:rooms) {
			reservationService.addRoom(room);
		}
	}
	
	public Collection<IRoom> getAllRooms() {
		return ReservationService.rooms;
	}
	
	public Collection<Customer> getAllCustomers() {
		return customerService.getAllCustomers();
	}
	
	public void desiplayAllReservations() {
		reservationService.printAllReservation();
	}
}
