package ec.com.orion.ticket.agency.cdi.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.FacesContext;

public class FacesContextProducer {

	@Produces
	@RequestScoped
	public FacesContext configurarFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
