package com.packtpub.wflydevelopment.chapter3.control;



import javax.ejb.Stateless;

@Stateless
public class EncerarConfiguracion {

//@Schedule(minute="*/1", hour="*" )
	public void configurarTimer(){
		System.out.println(">>>> primer timer");
	}
	
}
