package com.packtpub.wflydevelopment.chapter3.control;

import static javax.ejb.LockType.READ;
import static javax.ejb.LockType.WRITE;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.ejb.AccessTimeout;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.jboss.logging.Logger;

import com.packtpub.wflydevelopment.chapter3.entity.Seat;
import com.packtpub.wflydevelopment.chapter3.exception.NoSuchSeatException;
import com.packtpub.wflydevelopment.chapter3.exception.SeatBookedException;

@Singleton
@Startup
@AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
public class TheatreBox {

    private static final Logger logger = Logger.getLogger(TheatreBox.class);

    private Map<Integer, Seat> seats;

    @PostConstruct
    public void setupTheatre() {
        seats = new HashMap<>();
        int id = 0;
        for (int i = 0; i < 5; i++) {
            addSeat(new Seat(++id, "Stalls", 40));
            addSeat(new Seat(++id, "Circle", 20));
            addSeat(new Seat(++id, "Balcony", 10));
        }

        logger.info("Seat Map constructed.");
    }

    private void addSeat(Seat seat) {
        seats.put(seat.getId(), seat);
    }

    
    /**
     * El tipo de bloqueo READ permite
     * que varios a la vez puedan acceder al recurso.
     * Retorna una copia de la lista de asientos.Si el cliente
     * modifica un elemento de la lista no ocurre nada.
     * @return
     */
    @Lock(READ)
    public Collection<Seat> getSeats() {
    	
        return Collections.unmodifiableCollection(seats.values());
    }

    @Lock(READ)
    public int getSeatPrice(int seatId) throws NoSuchSeatException {
        return getSeat(seatId).getPrice();
    }

    /**
     * Este tipo de bloqueo solo permite que un hilo
     * a la vez acceda al recurso.
     * @param seatId
     * @throws SeatBookedException
     * @throws NoSuchSeatException
     * 
     * Notar que agrega un nuevo asiento con el estado reservado. 
     * Los asientos son inmutables no tienen setters.
     * 
     */
    @Lock(WRITE)
    public void buyTicket(int seatId) throws SeatBookedException, NoSuchSeatException {
        final Seat seat = getSeat(seatId);
        if (seat.isBooked()) {
            throw new SeatBookedException("Seat " + seatId + " already booked!");
        }
        addSeat(seat.getBookedSeat());
    }

    @Lock(READ)
    private Seat getSeat(int seatId) throws NoSuchSeatException {
        final Seat seat = seats.get(seatId);
        if (seat == null) {
            throw new NoSuchSeatException("Seat " + seatId + " does not exist!");
        }
        return seat;
    }
}
