package by.ita.je.service;

import by.ita.je.dao.SeatDao;
import by.ita.je.dao.TicketDao;
import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.SearcherService;
import by.ita.je.service.api.SeatSericve;
import by.ita.je.service.api.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private final TicketDao ticketDao;
    @Autowired
    private final SeatSericve seatSericve;
    @Autowired
    SearcherService searcherService;

    @Override
    public Ticket bookTicket(long id) throws  NotCorrectData{
        Seat seat=seatSericve.readById(id);
        if(seat.isBooked()){
            throw new NotCorrectData("This seat is BOOKED!!!!!!");
        }
        Ticket ticket=getTicket();
        seat.setBooked(true);
        ticket.setSeat(seat);
        ticket.setBookedDateTime(ZonedDateTime.now());
        Flight flight=searcherService.findFlightBySeat(id);
        ticket.setFlight(flight);
        return ticketDao.save(ticket);
    }

    @Override
    public Ticket readById(Long id) throws NotFoundData{
        final Ticket ticket= ticketDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Ticket"));
        return ticket;

    }

    @Override
    public void cancelTicket(Long id) throws NotFoundData{
        Ticket ticket= ticketDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Ticket"));
        seatSericve.cancelBooked(ticket.getSeat().getId());
        try {
            ticketDao.deleteById(id);
        }catch (Exception e){
            throw new NotFoundData("Ticket");
        }

    }

    private Ticket getTicket(){
        Ticket ticket=new Ticket();
        ticket.setBooked(true);
        return ticket;
    }
}
