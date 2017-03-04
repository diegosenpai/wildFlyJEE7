package ec.com.orion.ticket.agency.cdi.boundary;

import ec.com.orion.ticket.agency.cdi.entity.Seat;

public class AsientoDto {

	private int id;
	private String nombre;
	private int precio;
	private boolean reservado;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isReservado() {
		return reservado;
	}

	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}

	public AsientoDto(int id, String nombre, int precio, boolean reservado) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
		this.reservado = reservado;
	}

	public AsientoDto() {
		super();
	}

	public static AsientoDto asientoDtoDeAsiento(Seat asiento) {
		return new AsientoDto(asiento.getId(), asiento.getName(), asiento.getPrice(), asiento.isBooked());
	}

	@Override
	public String toString() {
		return "AsientoDto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", reservado=" + reservado + "]";
	}
	
}
