package ec.com.orion.ticket.agency.cdi.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import ec.com.orion.ticket.agency.cdi.boundary.TheatreBox;
import ec.com.orion.ticket.agency.cdi.util.Logged;

@SessionScoped
@Named
@Logged
public class TheatreBooker implements Serializable {

	private static final long serialVersionUID = 2232591402844875973L;

	@Inject
	private Logger logger;

	@Inject
	private TheatreBox box;

	private int saldoDisponible;

	@Inject
	private FacesContext contexto;

	@PostConstruct
	public void configurarSesion() {
		saldoDisponible = 100;
	}

	public void comprarAsiento(int idAsiento) {
		logger.info("Reservar asiento " + idAsiento);
		final int precio = box.getPrecioAsiento(idAsiento);
		if(precio > saldoDisponible){
			final FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "No dispone saldo suficiente.",
					"Compra incompleta");

			contexto.addMessage(null, mensaje);
			return;
		}
		box.comprarTicket(idAsiento);
		final FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Asiento Reservado!",
				"Proceso completado con éxito.");
		contexto.addMessage(null, mensaje);
		saldoDisponible = saldoDisponible - precio;
	}

	public int getSaldoDisponible() {
		return saldoDisponible;
	}

}
