package ec.com.orion.wildfly.ticket_ws_client;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.jboss.logging.Logger;

import ec.com.orion.ticket.agency.cdi.boundary.AsientoDto;
import ec.com.orion.ticket.agency.cdi.boundary.TicketService;

public class TestServicioWeb {

	private static final Logger logger = Logger.getLogger(TestServicioWeb.class);

	public static void main(String[] args) throws MalformedURLException {

		int seatId = 1;
		logger.info("Probar servicios web SOAP");
		final URL wsdlURL = new URL("http://boundary.cdi.agency.ticket.orion.com.ec/");
		final QName SERVICE_NAME = new QName("http://www.orion.com.ec", "TicketServicioWeb");
		final Service servicio = Service.create(wsdlURL, SERVICE_NAME);
		final TicketService infoService = servicio.getPort(TicketService.class);
		logger.info("Va consultar el servicio " + infoService);
		infoService.reservarAsientos(seatId);
		logger.info("Asiento Reservado con servicio web JAX-WS");

		imprimirListaAsientos(infoService.obtenerAsientos());

	}

	private static void imprimirListaAsientos(Collection<AsientoDto> asientos) {
		logger.info("================================= Lista de Asientos Disponibles =================================");
		asientos.stream().forEach(seat -> logger.info(seat.toString()));
	}
}
