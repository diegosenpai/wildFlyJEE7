package com.packtpub.wflydevelopment.chapter7.boundary;

import javax.jws.WebService;

@WebService
public interface CalculatePower {

	double calculatePower(double base, double exponent);

}