package ec.com.orion.ticket.agency.cdi.controller;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import com.google.common.collect.Lists;

import ec.com.orion.ticket.agency.cdi.boundary.TheatreBox;
import ec.com.orion.ticket.agency.cdi.entity.Seat;

@Model
public class TheatreInfo {

	private Collection<Seat> asientos;

	@Inject
	private TheatreBox theatreBox;

	@Inject
	private Logger logger;

	@PostConstruct
	public void consultarAsientosOrdenadosPorNombre() {
		asientos = theatreBox.getAsientos();
	}

	@Produces
	@Named
	public Collection<Seat> getAsientos() {
		return Lists.newArrayList(asientos);
	}

	public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) Seat asiento) {
		consultarAsientosOrdenadosPorNombre();
	}
}
