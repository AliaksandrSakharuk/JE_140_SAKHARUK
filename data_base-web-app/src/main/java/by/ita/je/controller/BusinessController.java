package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.model.AirCompany;
import by.ita.je.model.Flight;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;
//import by.ita.je.service.UserDetailsServiceImpl;
import by.ita.je.service.UserDetailsServiceImpl;
import by.ita.je.service.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class BusinessController {
    private final ObjectMapper objectMapper;
    private final BusinessService businessService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final TicketService ticketService;
    private final UserService userService;
    SeatSericve seatSericve;

    @PostMapping("/sales/flight")
    public FlightDto createNewFlight(@RequestBody FlightDto flightDto){
        final Flight flightNew = objectMapper.convertValue(flightDto, Flight.class);
        final Flight flight=businessService.createNewFlight(flightNew);
        return objectMapper.convertValue(flight, FlightDto.class);
    }

    @GetMapping("/sales/flight/{id}")
    public FlightDto findById(@PathVariable("id") String id){
        final Flight flight=businessService.findById(Long.valueOf(id));
        return objectMapper.convertValue(flight, FlightDto.class);
    }

    @GetMapping("/sales/flight/list")
    public  List<FlightDto> findAll(){
        List <Flight> list=businessService.findAll();
        List<FlightDto> flights=list.stream()
                .map(flight -> objectMapper.convertValue(flight, FlightDto.class))
                .collect(Collectors.toList());
        return flights;
    }

    @PostMapping("/company")
    public AirCompanyDto createNewAirCompany(@RequestBody AirCompanyDto companyDto){
        final AirCompany companyNew = objectMapper.convertValue(companyDto, AirCompany.class);
        final AirCompany company=businessService.createNewAirCompany(companyNew);
        return objectMapper.convertValue(company, AirCompanyDto.class);
    }

    @GetMapping("/sales/ticket/book")
    public TicketDto bookTicket(@RequestBody TicketDto ticketDto){
        final Ticket ticketNew= objectMapper.convertValue(ticketDto, Ticket.class);
        final Ticket ticket=businessService.bookTicket(ticketNew);
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @DeleteMapping("/sales/ticket/book/{id}")
    public void cancelTicket(@PathVariable("id") String id){
        businessService.cancelBookedTicket(Long.valueOf(id));
    }

    @PostMapping("/password/renewal")
    public boolean renewalPassword(@RequestBody FieldUserDto fieldUserDto){
        return userDetailsServiceImpl.renewalPassword(fieldUserDto);
    }

    @GetMapping("/sales/ticket/{id}")
    public TicketDto findTicket(@PathVariable("id") String id){
        final Ticket ticket=ticketService.readById(Long.valueOf(id));
        return objectMapper.convertValue(ticket, TicketDto.class);
    }
    @GetMapping("/sales/seat/new")
    public Seat createSeat(){
       Seat seat=new Seat();
       seat.setBooked(true);
       seat.setNumberSeat("12W");

        return seatSericve.save(seat);
    }

    @GetMapping("/client/ticket/all")
    public List<Ticket> getTicketClint(){
        return businessService.getAllTicketClient();
    }
}
