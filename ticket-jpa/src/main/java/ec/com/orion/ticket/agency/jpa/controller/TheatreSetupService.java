package ec.com.orion.ticket.agency.jpa.controller;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.orion.ticket.agency.jpa.control.TicketService;
import ec.com.orion.ticket.agency.jpa.entity.SeatPosition;
import ec.com.orion.ticket.agency.jpa.entity.SeatType;

@Model
public class TheatreSetupService {

	@Inject
	private TicketService servicioTicket;

	@Inject
	private Logger logger;

	@Inject
	private FacesContext contextoFaces;

	@Inject
	private List<SeatType> tiposAsiento;

	@Named
	@Produces
	private SeatType tipoAsientoNuevo;

	@PostConstruct
	public void configurarTipoAsiento() {
		tipoAsientoNuevo = new SeatType();
	}

	public String aplicarConfiguracion() {
		servicioTicket.crearCine(tiposAsiento);
		return "comprar";
	}

	public String reiniciarAplicacion() {
		servicioTicket.reiniciarConfiguracion();
		return "/index";
	}

	public List<SeatPosition> getSeatPositions() {
		return Arrays.asList(SeatPosition.values());
	}

	public void agregarNuevosAsientos() throws Exception {
		try {

			servicioTicket.crearTipoAsiento(tipoAsientoNuevo);
			final FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Finalizado",
					"Asientos Creados!");
			contextoFaces.addMessage(null, mensaje);
		} catch (Exception e) {
			final String mensajeError = getMensajeErrorRaiz(e);
			final FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, mensajeError,
					"Error al grabar Datos!");
			contextoFaces.addMessage(null, mensaje);

		}

	}

	private String getMensajeErrorRaiz(Exception e) {
		String mensajeError = "Registro Fallido. Revisar el log del servidor para mas detalles";
		if (e == null) {
			return mensajeError;
		}
		Throwable excepcion = e;
		while (excepcion != null) {
			mensajeError = excepcion.getLocalizedMessage();
			excepcion = excepcion.getCause();
		}
		return mensajeError;
	}
}
