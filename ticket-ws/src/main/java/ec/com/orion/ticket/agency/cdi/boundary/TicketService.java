package ec.com.orion.ticket.agency.cdi.boundary;

import java.util.List;

import javax.jws.WebService;

@WebService
public interface TicketService {

	List<AsientoDto> obtenerAsientos();

	void reservarAsientos(int idAsiento);

}
