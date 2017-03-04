package ec.com.orion.ticket.agency.cdi.boundary;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.jws.*;

@WebService(targetNamespace = "http://www.orion.com.ec", serviceName = "TicketServicioWeb")
public class DefaultTicketServicioWeb implements Serializable, TicketService {

	private static final long serialVersionUID = -7040472393459693705L;
	@Inject
	protected TheatreBox configuracion;

	@WebMethod
	@Override
	@WebResult(name = "asientos")
	public List<AsientoDto> obtenerAsientos() {
		return configuracion.getAsientos().stream().map(AsientoDto::asientoDtoDeAsiento).collect(Collectors.toList());
	}

	@Override
	@WebMethod
	public void reservarAsientos(@WebParam(name = "asiento") int idAsiento) {
		configuracion.comprarTicket(idAsiento);

	}

}
