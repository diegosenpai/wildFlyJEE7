package com.packtpub.wflydevelopment.chapter7.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.packtpub.wflydevelopment.chapter7.boundary.CalculatePower;
import com.packtpub.wflydevelopment.chapter7.boundary.SeatDto;
import com.packtpub.wflydevelopment.chapter7.boundary.TicketWebService;


public class TicketWebServiceTestApplication {

    private static final Logger logger = Logger.getLogger(TicketWebServiceTestApplication.class.getName());

    public static void main(String[] args) throws MalformedURLException {
        ejecutarServicioWebSeguro();
    }

	private static void invocarServicioAsientos() throws MalformedURLException {
		final int seatId = 1;
        logger.info("TEST SOAP WS Service");
        final URL wsdlURL = new URL("http://localhost:8080/ticket-agency-ws/TicketWebService?wsdl");
        final QName SERVICE_NAME = new QName("http://www.packtpub.com/", "TicketWebService");
        final Service service = Service.create(wsdlURL, SERVICE_NAME);
        final TicketWebService infoService = service.getPort(TicketWebService.class);

        logger.info("Got the Service: " + infoService);

        infoService.bookSeat(seatId);
        logger.info("Ticket Booked with JAX-WS Service");

        final List<SeatDto> list = infoService.getSeats();

        dumpSeatList(list);
	}

    private static void dumpSeatList(Collection<SeatDto> list) {
        logger.info("================= Available Ticket List ================");
        list.stream().forEach(seat -> logger.info(seat.toString()));
    }
    
    private static void ejecutarServicioWebSeguro(){
    	JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
    	factory.getInInterceptors().add(new LoggingInInterceptor());
    	factory.getOutInterceptors().add(new LoggingOutInterceptor());
    	factory.setServiceClass(CalculatePower.class);
    	factory.setAddress("http://localhost:8080/ticket-agency-ws/CalculatePowerWebService");
    	factory.setUsername("admin");
    	factory.setPassword("diego");
    	CalculatePower client = (CalculatePower) factory.create();
    	System.out.println(">>>prueba "+client.calculatePower(3, 3));    	
    }
    
}
