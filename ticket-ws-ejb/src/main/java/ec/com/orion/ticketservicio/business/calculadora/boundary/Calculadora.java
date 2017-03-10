package ec.com.orion.ticketservicio.business.calculadora.boundary;

import javax.jws.WebService;

@WebService
public interface Calculadora {

	double calculatePower(double base, double exponent);

}