package ec.com.orion.ticket.agency.jpa.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.orion.ticket.agency.jpa.control.TicketService;

@ViewScoped
@Named
public class BookerService implements Serializable {


	private static final long serialVersionUID = -2474979442597158648L;

	@Inject
	private TicketService servicioBoletos;
	
	@Inject
	private Logger logger;
	
	@Inject
	private FacesContext contextoFaces;
	
	private int saldoDisponible;
	
	@PostConstruct
	public void cargarConfiguracionCliente(){
		this.saldoDisponible = 100;
	}

	public int getSaldoDisponible() {
		return saldoDisponible;
	}
	
	public void comprarAsiento(Long idAsiento, Integer precioAsiento){
		logger.info("Registrando compra asiento: "+idAsiento);
		if(precioAsiento > saldoDisponible){
			final FacesMessage mensajeError = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Saldo Insuficiente","Compra Incompleta");
			contextoFaces.addMessage(null, mensajeError);
			return;
		}
		servicioBoletos.comprarAsiento(idAsiento);
		final FacesMessage mensajeExito = new FacesMessage(FacesMessage.SEVERITY_INFO,"Registro Exitoso","Compra finalizada con Exito");
		contextoFaces.addMessage(null, mensajeExito);
		logger.info("Asiento registrado");
		saldoDisponible = saldoDisponible-precioAsiento;
	}
}
