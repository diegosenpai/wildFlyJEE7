package ec.com.orion.ticketservicio.business.calculadora.boundary;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.jboss.ejb3.annotation.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

@Stateless
@WebService(targetNamespace = "http://www.packtpub.com/",
serviceName = "ServicioCalculadora", endpointInterface="ec.com.orion.ticketservicio.business.calculadora.boundary.Calculadora")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@WebContext(authMethod="BASIC", secureWSDLAccess=false)
@SecurityDomain(value="dbdomain")
@RolesAllowed("Manager")
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
