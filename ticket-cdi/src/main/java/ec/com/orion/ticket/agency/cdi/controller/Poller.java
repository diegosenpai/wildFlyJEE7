package ec.com.orion.ticket.agency.cdi.controller;

import java.util.Collection;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import ec.com.orion.ticket.agency.cdi.boundary.TheatreBox;
import ec.com.orion.ticket.agency.cdi.entity.Seat;

@Model
public class Poller {

	@Inject
	private TheatreBox box;

	public boolean isRefrescamientoActivo() {
		return existenAsientosDisponibles();
	}

	private boolean existenAsientosDisponibles(){
		Collection<Seat> asientos = box.getAsientos();
		for (Seat asiento : asientos) {
			if (!asiento.isBooked()) {
				return true;
			}
		}
		return false;
	}

}
