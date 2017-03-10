package com.packtpub.wflydevelopment.chapter7.boundary;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://www.packtpub.com/",
        serviceName = "CalculatePowerWebService", endpointInterface="com.packtpub.wflydevelopment.chapter7.boundary.CalculatePower")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class CalculatePowerWebService implements CalculatePower {

    /* (non-Javadoc)
	 * @see com.packtpub.wflydevelopment.chapter7.boundary.CalculatePower#calculatePower(double, double)
	 */
    @Override
	@WebMethod
    @WebResult(name = "result")
    public double calculatePower(@WebParam(name = "base") double base,
                                 @WebParam(name = "exponent") double exponent) {
        return Math.pow(base, exponent);
    }
}
