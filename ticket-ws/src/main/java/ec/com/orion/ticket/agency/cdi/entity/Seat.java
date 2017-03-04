package ec.com.orion.ticket.agency.cdi.entity;

public class Seat {

	private final int id;
	private final String name;
	private final int price;
	private final boolean booked;

	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public boolean isBooked() {
		return booked;
	}

	public Seat(int id, String name, int price) {
		this(id, name, price, false);
	}

	public Seat(int id, String name, int price, boolean booked) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.booked = booked;
	}

	@Override
	public String toString() {
		return "Seat [id=" + id + ", name=" + name + ", price=" + price + ", booked=" + booked + "]";
	}
	
	public Seat getAsientoReservado() {
		return new Seat(getId(), getName(), getPrice(), true);
	}
	
}
