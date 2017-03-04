package ec.com.orion.ticket.agency.jpa.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;


public class FacesContextProducer {

	@Produces
	@RequestScoped
	public FacesContext generarFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
}
