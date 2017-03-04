package ec.com.orion.wildfly.gestion;

import java.io.IOException;
import java.net.InetAddress;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
import org.jboss.logging.Logger;

@Singleton
@Startup
public class ConfiguracionJndi {

	private static final Logger logger = Logger.getLogger(ConfiguracionJndi.class);

	@PostConstruct
	public void imprimirJndi() throws IOException {
		final ModelControllerClient cliente = ModelControllerClient.Factory.create(InetAddress.getByName("localhost"),
				9990);
		final ModelNode operacion = new ModelNode();
		operacion.get("operation").set("jndi-view");
		final ModelNode direccion = operacion.get("address");
		direccion.add("subsystem","naming");
		operacion.get("recursive").set("true");
		operacion.get("operations").set("true");
		final ModelNode valorRetorno = cliente.execute(operacion);
		logger.info(valorRetorno.get("result").toString());
		
	}

}
