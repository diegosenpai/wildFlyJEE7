package ec.com.orion.ticket.agency.cdi.boundary;

import javax.jws.*;
import javax.jws.soap.SOAPBinding;

@WebService(serviceName = "CalcularPotencia")
public class CalcularPotenciaServicio {

	@WebMethod
	@WebResult(name = "resultado")
	public double calcularPotencia(@WebParam(name = "base") double base,
			@WebParam(name = "exponencial") double exponencial) {
		return Math.pow(base, exponencial);
	}

}
