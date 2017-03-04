package ec.com.orion.ticket.agency.jpa.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "seat_type")
public class SeatType implements Serializable {

	private static final long serialVersionUID = -2793941026748893232L;

	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	@Size(min = 1, max = 25, message = "Ingrese una descripcion (maximo 25 car)")
	@Pattern(regexp = "[A-Za-z ]*", message = "La descripcion solo puede contener letras y espacios.")
	private String descripcion;
	
	@NotNull
	private SeatPosition posicion;
	
	@NotNull
	private Integer precio;
	
	@NotNull
	private Integer cantidad;
	
	@OneToMany(mappedBy = "tipoAsiento", fetch = FetchType.EAGER)
	private List<Seat> asientos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public SeatPosition getPosicion() {
		return posicion;
	}

	public void setPosicion(SeatPosition posicion) {
		this.posicion = posicion;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public List<Seat> getAsientos() {
		return asientos;
	}

	public void setAsientos(List<Seat> asientos) {
		this.asientos = asientos;
	}

}
