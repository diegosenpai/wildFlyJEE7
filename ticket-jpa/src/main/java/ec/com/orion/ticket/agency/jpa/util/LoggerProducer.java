package ec.com.orion.ticket.agency.jpa.util;

import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

public class LoggerProducer {

	@Produces
	public Logger generarLogger(InjectionPoint puntoInyeccion){
		return Logger.getLogger(puntoInyeccion.getMember().getDeclaringClass().getName());
	}
	
}
