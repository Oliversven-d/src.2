package service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import model.Customer;
import model.IRoom;
import model.Reservation;
public class ReservationService {
	public static List<IRoom> rooms = new ArrayList<IRoom>();
	static List<Reservation> reservations = new ArrayList<Reservation>();
	private ReservationService() {
	}
	public static ReservationService getInstance() {
		return new ReservationService();
	}
	public void addRoom(IRoom room) {
		rooms.add(room);
	}
	public IRoom getARoom(String roomId) {
		for (IRoom room : rooms) {
			if (room.getRoomNumber().equals(roomId)) {
				return room;
			}
		}
		return null;
	}
	public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
		Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
		reservations.add(reservation);
		return reservation;
	}
	public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
		Collection<IRoom> availableRooms = new ArrayList<>();
		//Base case
		if (reservations.isEmpty())
			return rooms;
		else{
			for (IRoom room : rooms) {
				if (!isRoomReserved(room, checkInDate, checkOutDate)) {
					availableRooms.add(room);
				}
			}
		}
		return availableRooms;
	}
	public boolean isRoomReserved(IRoom room, Date checkInDate, Date checkOutDate) {
		for (Reservation reservation : reservations) {
			IRoom reservedRoom = reservation.getRoom();
			if (reservedRoom.getRoomNumber().equals(room.getRoomNumber())) {
				if (isDateWithinRange(checkInDate, checkOutDate, reservation)) {
					return true;
				}
			}
		}
		return false;
	}
	boolean isDateWithinRange(Date checkInDate, Date checkOutDate, Reservation reservation) {
		return !(checkOutDate.before(reservation.getCheckInDate()) || checkInDate.after(reservation.getCheckOutDate()));
	}
	public Collection<Reservation> getCustomerReservation(Customer customer) {
		ArrayList<Reservation> reservation = new ArrayList<Reservation>();
		for (Reservation reserve : reservations) {
			if (reserve.getCustomer().equals(customer)) {
				reservation.add(reserve);
			}
		}
		return reservation;
	}
	public void printAllReservation() {
		for (Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}
}

