package ec.com.orion.ticket.agency.cdi.util;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import org.jboss.logging.Logger;

public class LoggerProducer {

	@Produces
	public Logger configurarLogger(InjectionPoint puntoInyeccion) {
		return Logger.getLogger(puntoInyeccion.getMember().getDeclaringClass().getName());
	}

}
