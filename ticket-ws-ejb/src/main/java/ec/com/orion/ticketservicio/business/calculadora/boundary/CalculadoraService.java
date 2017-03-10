package ec.com.orion.ticketservicio.business.calculadora.boundary;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@Stateless
@WebService(targetNamespace = "http://www.packtpub.com/",
serviceName = "ServicioCalculadora", endpointInterface="ec.com.orion.ticketservicio.business.calculadora.boundary.Calculadora")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CalculadoraService implements Calculadora {

	/* (non-Javadoc)
	 * @see ec.com.orion.ticketservicio.business.calculadora.boundary.Calculadora#calculatePower(double, double)
	 */
	@WebMethod
    @WebResult(name = "result")
    public double calculatePower(@WebParam(name = "base") double base,
                                 @WebParam(name = "exponent") double exponent) {
        return Math.pow(base, exponent);
    }
	
}
