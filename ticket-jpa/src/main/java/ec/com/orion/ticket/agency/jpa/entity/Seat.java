package ec.com.orion.ticket.agency.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Seat implements Serializable {


	private static final long serialVersionUID = 2612121811792968321L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private boolean booked;
	
	@ManyToOne
	@JoinColumn
	private SeatType tipoAsiento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public SeatType getTipoAsiento() {
		return tipoAsiento;
	}

	public void setTipoAsiento(SeatType tipoAsiento) {
		this.tipoAsiento = tipoAsiento;
	}
	
	
	
}
