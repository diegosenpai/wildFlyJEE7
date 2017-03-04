package ec.com.orion.ticket.agency.jpa.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.orion.ticket.agency.jpa.entity.Seat;

@RequestScoped
public class SeatProducer {

	@Inject
	private SeatDao asientoDao;

	private List<Seat> asientos;

	@PostConstruct
	public void consultarAsientos() {
		asientos = asientoDao.buscarTodos();
	}

	@Produces
	@Named
	public List<Seat> getAsientos() {
		return asientos;
	}

	public void onMemberListChanged(@Observes(notifyObserver = Reception.IF_EXISTS) final Seat asiento) {
		consultarAsientos();
	}
}
