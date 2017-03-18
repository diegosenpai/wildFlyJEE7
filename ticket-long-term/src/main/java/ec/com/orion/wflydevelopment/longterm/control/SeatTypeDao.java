package ec.com.orion.wflydevelopment.longterm.control;

import javax.ejb.Stateless;

import ec.com.orion.wflydevelopment.longterm.entity.SeatType;


@Stateless
public class SeatTypeDao extends AbstractDao<SeatType> {

    public SeatTypeDao() {
        super(SeatType.class);
    }
}
