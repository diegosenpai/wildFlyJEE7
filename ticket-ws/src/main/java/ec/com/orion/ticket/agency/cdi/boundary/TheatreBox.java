package ec.com.orion.ticket.agency.cdi.boundary;

import static javax.ejb.LockType.READ;

import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import ec.com.orion.ticket.agency.cdi.entity.Seat;

@Singleton
@Startup
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class TheatreBox {

	private Map<Integer, Seat> asientos;

	@Inject
	public Logger logger;

	@Inject
	private Event<Seat> seatEvent;

	@PostConstruct
	public void setupTheatre() {

		asientos = new HashMap<Integer, Seat>();
		int id = 0;
		for (int i = 0; i < 5; i++) {
			final Seat seat = new Seat(++id, "Stalls", 40);
			addSeat(seat);
		}

		for (int i = 0; i < 5; i++) {
			final Seat asiento = new Seat(++id, "Circle", 20);
			addSeat(asiento);
		}

		for (int i = 0; i < 5; i++) {
			final Seat asiento = new Seat(++id, "Balcony", 10);
			addSeat(asiento);
		}
		logger.info("Mapa de Asientos construido...");
		logger.info("Asientos construidos: " + asientos.size());

	}

	private void addSeat(Seat asiento) {
		asientos.put(asiento.getId(), asiento);
	}

	@Lock(READ)
	public Collection<Seat> getAsientos() {
		return Collections.unmodifiableCollection(asientos.values());
	}

	@Lock(READ)
	public int getPrecioAsiento(int idAsiento) {
		return getAsiento(idAsiento).getPrice();
	}

	@Lock(READ)
	public Seat getAsiento(int idAsiento) {
		return asientos.get(idAsiento);
	}

	public void comprarTicket(int idAsiento) {
		final Seat asiento = getAsiento(idAsiento);
		final Seat asientoReservado = asiento.getAsientoReservado();
		addSeat(asiento.getAsientoReservado());
		seatEvent.fire(asientoReservado);
	}

}
