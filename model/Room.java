package model;

public class Room implements IRoom{
	private String roomNumber;
	private Double price;
	private RoomType enumeration;
	
	
	public Room(String roomNumber, Double price, RoomType enumeration) {
		this.roomNumber = roomNumber;
		this.price = price;
		this.enumeration = enumeration;
	}
	
	public String toString() {
		if(this.enumeration.equals(RoomType.SINGLE)) {
			return "Room number: " + this.roomNumber + " Single bed Room Price: $" + this.price;
		}
		else {
			return "Room number: " + this.roomNumber + " Double bed Room Price: $" + this.price;
		}
		
	}
	@Override
	public String getRoomNumber() {
		return this.roomNumber;
	}
	@Override
	public Double getRoomPrice() {
		return this.price;
	}
	@Override
	public RoomType getRoomType() {
		return this.enumeration;
	}
	@Override
	public boolean isFree() {
		if(this.getRoomPrice() == 0) {
			return true;
		}
		return false;
	}
}
