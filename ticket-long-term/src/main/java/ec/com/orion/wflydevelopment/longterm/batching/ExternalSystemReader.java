package ec.com.orion.wflydevelopment.longterm.batching;

import java.io.Serializable;

import javax.batch.api.chunk.AbstractItemReader;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class ExternalSystemReader extends AbstractItemReader {

	private WebTarget target;
	
	@Override
	public void open(Serializable checkpoint){
		final Client restClient = ClientBuilder.newClient();
		this.target = restClient.target("http://localhost:8080/ticket-long-term/rest/external");
		
	}
	
	@Override
	public Object readItem() throws Exception {
		return target.request().get(String.class);
	}

}
