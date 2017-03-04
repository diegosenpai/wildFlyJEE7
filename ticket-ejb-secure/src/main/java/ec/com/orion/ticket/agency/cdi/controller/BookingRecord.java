package ec.com.orion.ticket.agency.cdi.controller;

import java.io.Serializable;

import javax.enterprise.event.Observes;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import ec.com.orion.ticket.agency.cdi.entity.Seat;
import ec.com.orion.ticket.agency.cdi.util.Logged;

@Named
@ViewScoped
@Logged
public class BookingRecord implements Serializable {

	@Inject
	Logger logger;

	private int asientosReservados = 0;

	public int getAsientosReservados() {
		return asientosReservados;
	}

	public void registrarCompra(@Observes Seat asientoReservado) {
		asientosReservados++;
		logger.infov("Total asientos reservados:{0}", asientosReservados);
	}

}
