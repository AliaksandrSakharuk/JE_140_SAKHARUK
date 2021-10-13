package by.ita.je.controller;

import by.ita.je.dto.AirCompanyDto;
import by.ita.je.dto.FlightDto;
import by.ita.je.dto.TicketDto;
import by.ita.je.model.AirCompany;
import by.ita.je.model.Flight;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class BusinessController {
    private final ObjectMapper objectMapper;
    private final BusinessService businessService;
    private final MessageService messageService;

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
        messageService.sendMessage();
        return flights;
    }

    @PostMapping("/company")
    public AirCompanyDto createNewAirCompany(@RequestBody AirCompanyDto companyDto){
        final AirCompany companyNew = objectMapper.convertValue(companyDto, AirCompany.class);
        final AirCompany company=businessService.createNewAirCompany(companyNew);
        return objectMapper.convertValue(company, AirCompanyDto.class);
    }

    @GetMapping("/sales/ticket/book/{id}")
    public TicketDto bookTicket(@PathVariable("id") String id){
        final Ticket ticket=businessService.bookTicket(Long.valueOf(id));
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @DeleteMapping("/sales/ticket/book/{id}")
    public void cancelTicket(@PathVariable("id") String id){
        businessService.cancelBookedTicket(Long.valueOf(id));

    }
}
