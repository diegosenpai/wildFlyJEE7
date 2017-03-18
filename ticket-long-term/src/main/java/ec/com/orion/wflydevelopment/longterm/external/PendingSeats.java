package ec.com.orion.wflydevelopment.longterm.external;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Path("/external")
@Produces(MediaType.TEXT_PLAIN)
public class PendingSeats {

	private final Queue<Integer> seats = new ConcurrentLinkedQueue<>();

	@PostConstruct
	public void setup() {
		for (int i = 5; i < 10; i++) {
			seats.add(i);
		}
	}

	@GET
	public Integer getNextSeat(){
		return seats.poll();
	}
	
}
