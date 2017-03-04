package ec.com.orion.ticket.agency.jpa.control;

import javax.ejb.Stateless;

import ec.com.orion.ticket.agency.jpa.entity.Seat;

@Stateless
public class SeatDao extends AbstractDao<Seat> {

	public SeatDao(){
		super(Seat.class);
	}
	
	
}
