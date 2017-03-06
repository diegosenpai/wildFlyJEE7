package com.packtpub.wflydevelopment.chapter3.control;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.ejb.TimerService;

import org.jboss.logging.Logger;

import com.packtpub.wflydevelopment.chapter3.boundary.TheatreBookerRemote;
import com.packtpub.wflydevelopment.chapter3.boundary.TheatreInfoRemote;
import com.packtpub.wflydevelopment.chapter3.entity.Seat;
import com.packtpub.wflydevelopment.chapter3.exception.NoSuchSeatException;
import com.packtpub.wflydevelopment.chapter3.exception.SeatBookedException;

@Stateless
public class AutomaticSellerService {

    private static final Logger logger = Logger.getLogger(AutomaticSellerService.class);

    @EJB
    private TheatreBox theatreBox;
    
    @EJB
    private TheatreInfoRemote infoCine;

    @Resource
    private TimerService timerService;
    
    @EJB
    protected TheatreBookerRemote booker;

    /**
     * El timer se crea de forma automática cuando se despliega el bean.
     * No es necesario el método callback.
     * @throws NoSuchSeatException
     * if this is throw here, then this is a programming error
     */
    @Schedule(hour = "*", minute = "*/1", persistent = false)
    public void automaticCustomer() throws NoSuchSeatException {
        final Optional<Seat> seatOptional = findFreeSeat();

        if (!seatOptional.isPresent()) {
            cancelTimers();
            logger.info("Scheduler gone!");
            return; // No more seats
        }

        final Seat seat = seatOptional.get();

        try {
            theatreBox.buyTicket(seat.getId());            
        } catch (SeatBookedException e) {
            // do nothing, user booked this seat in the meantime
        }

        logger.info("Somebody just booked seat number " + seat.getId());
        logger.info("Estado de los asientos:"+infoCine.printSeatList());
    }

    /**
     * Al tratar de modificar la lista retornada, se lanza la
     * exception UnsupportedOperationException
     * 
     * @return
     */
    private Optional<Seat> findFreeSeat() {
        final Collection<Seat> list = theatreBox.getSeats();       
        return list.stream().filter(seat -> !seat.isBooked()).findFirst();
    }

    /**
     * El método getTimers devuelve los timers asociados al bean.
     */
    private void cancelTimers() {
        for (Timer timer : timerService.getTimers()) {
            timer.cancel();
        }
    }
    
    @PostConstruct
    public void iniciar(){
    	System.out.println(">>contruir timer");
    }
    
    @PreDestroy
    public void terminar(){
    	booker.terminar();
    	System.out.println(">>destruir timer");
    }
}
