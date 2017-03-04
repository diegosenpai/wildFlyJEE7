package ec.com.orion.ticket.agency.jpa.control;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import ec.com.orion.ticket.agency.jpa.entity.Seat;
import ec.com.orion.ticket.agency.jpa.entity.SeatType;

@Stateless
public class TicketService {

	@Inject
	private Logger logger;
	
	@Inject
	private SeatDao asientoDao;
	
	@Inject
	private SeatTypeDao tipoAsientoDao;
	
	@Inject 
	private Event<Seat> eventoOrigenAsiento;
	
	@Inject
	private Event<SeatType> eventoOrigenTipoAsiento;
	
	public void reiniciarConfiguracion(){
		asientoDao.borrarTodos();
		tipoAsientoDao.borrarTodos();
	}
	
	public void crearTipoAsiento(SeatType tipoAsiento){
		logger.info("Creando tipo de asiento: "+tipoAsiento.getDescripcion());
		tipoAsientoDao.persistir(tipoAsiento);
		eventoOrigenTipoAsiento.fire(tipoAsiento);
	}
	
	public void crearCine(List<SeatType> tiposAsiento){
		for(SeatType tipoAsiento:tiposAsiento){
			for(int i=0; i<tipoAsiento.getCantidad();i++){
				Seat asiento = new Seat();
				asiento.setBooked(false);
				asiento.setTipoAsiento(tipoAsiento);
				asientoDao.persistir(asiento);
			}
		}
	}
	
	public void comprarAsiento(Long idAsiento){
		final Seat asientoReservado = asientoDao.buscar(idAsiento);
		asientoReservado.setBooked(true);
		asientoDao.actualizar(asientoReservado);
		eventoOrigenAsiento.fire(asientoReservado);
	}
}
