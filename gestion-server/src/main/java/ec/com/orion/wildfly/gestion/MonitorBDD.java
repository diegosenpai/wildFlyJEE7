package ec.com.orion.wildfly.gestion;

import java.io.Closeable;
import java.net.InetAddress;
import java.util.logging.Level;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

import org.jboss.as.controller.client.ModelControllerClient;
import org.jboss.dmr.ModelNode;
import org.jboss.logging.Logger;

@Stateless
public class MonitorBDD {

	public static final Logger logger = Logger.getLogger(MonitorBDD.class);

	@Schedule(hour = "*", minute = "*", year = "*", dayOfWeek = "*", second = "*/30", persistent = false)
	public void monitorearConecciones() {
		ModelControllerClient cliente = null;
		try {
			cliente = ModelControllerClient.Factory.create(InetAddress.getByName("localhost"), 9990);
			final ModelNode operacion = new ModelNode();
			operacion.get("operation").set("read-resource");
			operacion.get("include-runtime").set(true);
			final ModelNode direccion = operacion.get("address");
			direccion.add("subsystem", "datasources");
			direccion.add("data-source", "");
			direccion.add("statistics", "pool");
			final ModelNode valorRetorno = cliente.execute(operacion);
			final ModelNode nodo2 = valorRetorno.get("result");
			final String coneccionesActivas = nodo2.get("ActiveCount").asString();
			if (coneccionesActivas.endsWith("undefined")) {
				return;
			}
			int contadorConecciones = Integer.parseInt(coneccionesActivas);
			if (contadorConecciones > 50) {
				enviarCorreoAlerta();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			cerrarRecursos(cliente);
		}
	}

	private void cerrarRecursos(Closeable cliente) {
		if (cliente != null) {

			try {
				cliente.close();
			} catch (Exception e) {
				logger.error("error al cerrar coneccion con el clinte " + e.getMessage());
			}
		}

	}

	private void enviarCorreoAlerta() {
		logger.info(">>>Se debe comunicar al administrador");

	}

}
