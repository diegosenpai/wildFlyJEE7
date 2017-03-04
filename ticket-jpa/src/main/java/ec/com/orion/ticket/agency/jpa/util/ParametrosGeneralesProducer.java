package ec.com.orion.ticket.agency.jpa.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

public class ParametrosGeneralesProducer {

	@Produces
	@Named
	public String getNombreAplicacion() {
		return "Orion";
	}

}
