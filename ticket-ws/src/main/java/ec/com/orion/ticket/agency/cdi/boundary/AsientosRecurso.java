package ec.com.orion.ticket.agency.cdi.boundary;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Path("/asiento")
public class AsientosRecurso {

	protected TheatreBox configuracion;
	
	@GET
	public List<AsientoDto> obtenerAsientos(){
		return configuracion.getAsientos().stream().map(AsientoDto::asientoDtoDeAsiento).collect(Collectors.toList());
	}
	
	@POST
	@Path("{id}")
	public Response reservarAsiento(@PathParam("id") int asientoId){
		configuracion.comprarTicket(asientoId);
		return Response.ok(AsientoDto.asientoDtoDeAsiento(configuracion.getAsiento(asientoId))).build();
	}
	
}
