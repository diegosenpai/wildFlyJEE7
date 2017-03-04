package ec.com.orion.ticket.agency.jpa.control;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.event.Reception;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.orion.ticket.agency.jpa.entity.SeatType;

@RequestScoped
public class SeatTypeProducer {

	@Inject
	private SeatTypeDao tipoAsientoDao;
	
	private List<SeatType> tiposAsiento;
	
	@PostConstruct
	public void consultarTiposAsiento(){
		tiposAsiento = tipoAsientoDao.buscarTodos();
	}
	
	@Produces
	@Named
	public List<SeatType> getTiposAsiento(){
		return tiposAsiento;
	}
	
	public void onListChanged(@Observes(notifyObserver=Reception.IF_EXISTS) SeatType tipoAsiento){
		consultarTiposAsiento();
	}
}
