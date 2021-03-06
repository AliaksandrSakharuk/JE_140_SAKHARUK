package by.ita.je.service;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.exception.NotFoundFreePlane;
import by.ita.je.model.*;
import by.ita.je.service.api.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BusinessServiceImpl implements BusinessService {

    private final SearcherService searcherService;
    private final FlightService flightService;
    private final AirCompanyService companyService;
    private final SeatSericve seatSericve;
    private final TicketService ticketService;
    private final UserService userService;
    private final ClientService clientService;

    @Override
    public Flight createNewFlight(Flight flight) {
        if(searcherService.findFreePlane(flight.getDepartureDateTime())==null)
            throw new NotFoundFreePlane(flight.getDepartureDateTime());
        Plane freePlane=searcherService.findFreePlane(flight.getDepartureDateTime());
        flight.setPlane(freePlane);
        createSeat(flight);
        return flightService.save(flight);
    }

    @Override
    public Flight findById(long id) {
        return flightService.readById(id);
    }

    @Override
    public List<Flight> findAll() {
        return flightService.readAll();
    }

    @Override
    public AirCompany createNewAirCompany(AirCompany company){
        createIfNotRelationshipAirCompanyToPlanes(company);
        return companyService.save(company);
    }

    @Override
    public Ticket bookTicket(Ticket ticket) throws NotCorrectData {
        Client client=userService.getCurrentUser().getClient();
        ticket.getSeat().setBooked(true);
        ticket.setClient(client);
        seatSericve.update(ticket.getSeat().getId(),ticket.getSeat());
        return ticketService.save(ticket);
    }

    @Override
    public void cancelBookedTicket(Long idTicket) throws NotFoundData{
        Ticket ticket= ticketService.readById(idTicket);
        Seat seat=ticket.getSeat();
        seat.setBooked(false);
        seatSericve.update(seat.getId(),seat);
        ticketService.deleteById(idTicket);
    }

    @Override
    public List<Ticket> getAllTicketClient() {
        Client client=userService.getCurrentUser().getClient();
        return client.getTickets();
    }

    @Override
    public List<Passenger> getPassengersOfClient() {
        Client client=userService.getCurrentUser().getClient();
        return client.getPassengers();
    }


    private void createSeat(Flight flight){
        List<Seat> list=new ArrayList<Seat>();
        for(int numberLine=1; numberLine<=flight.getPlane().getQuantityLines();numberLine++){
            char num='A';
            for(int numSeatInLine=0; numSeatInLine<flight.getPlane().getSeatsInLine();numSeatInLine++){
                String numSeat=numberLine + Character.toString(num);
                list.add(Seat.builder().numberSeat(numSeat).build());
                num++;
            }
        }
        flight.setSeats(list);
    }

    private void createIfNotRelationshipAirCompanyToPlanes(AirCompany company){
        if (Objects.isNull(company.getPlanes()) || company.getPlanes().isEmpty()){
            Plane boeing=cteateBoeing737_500();
            Plane embrare=createEmbraer195();
            company.setPlanes(List.of(boeing, embrare));
        }
    }

    private Plane cteateBoeing737_500(){
        return Plane.builder()
                .namePlane("Boeing 737-500")
                .namePilot("James Bond")
                .quantitySeats(144)
                .seatsInLine(6)
                .quantityLines(24)
                .invertorNumber(123456432L)
                .build();
    }

    private Plane createEmbraer195(){
        return Plane.builder()
                .namePlane("Embraer 195")
                .namePilot("SCALA")
                .quantitySeats(114)
                .seatsInLine(6)
                .quantityLines(19)
                .invertorNumber(7777777L)
                .build();
    }

}
