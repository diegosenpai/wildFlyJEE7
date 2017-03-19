package ec.com.orion.wflydevelopment.longterm.batching;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

import ec.com.orion.wflydevelopment.longterm.control.SeatDao;
import ec.com.orion.wflydevelopment.longterm.entity.Seat;

@Named
public class SeatProcessor implements ItemProcessor{

	@Inject
	private SeatDao seatDao;
	
	@Override
	public Object processItem(Object id) throws Exception {
		Seat seat = seatDao.find(Long.parseLong((String)id));
		if(seat !=null){
			if(seat.getBooked()){
				return null;
			}
			seat.setBooked(true); 
		}
		return seat;
	}

	
	
}
