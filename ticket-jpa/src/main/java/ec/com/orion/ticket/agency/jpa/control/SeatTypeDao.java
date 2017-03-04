package ec.com.orion.ticket.agency.jpa.control;

import javax.ejb.Stateless;

import ec.com.orion.ticket.agency.jpa.entity.SeatType;

@Stateless
public class SeatTypeDao extends AbstractDao<SeatType> {

	public SeatTypeDao(){
		super(SeatType.class);
	}
	
}
