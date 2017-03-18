package ec.com.orion.wflydevelopment.longterm.control;

import javax.ejb.Stateless;

import ec.com.orion.wflydevelopment.longterm.entity.Seat;


@Stateless
public class SeatDao extends AbstractDao<Seat> {

    public SeatDao() {
        super(Seat.class);
    }
}
