package ec.com.orion.ticket.agency.cdi.util;

import java.io.Serializable;

import javax.interceptor.*;

import org.jboss.logging.Logger;

@Interceptor
@Logged
public class LoggingInterceptor implements Serializable {

	@AroundInvoke
	public Object log(InvocationContext contexto) throws Exception {
		final Logger logger = Logger.getLogger(contexto.getTarget().getClass());
		logger.infov("Ejecutando metodo: {0}", contexto.getMethod().toString());
		return contexto.proceed();
	}
}
