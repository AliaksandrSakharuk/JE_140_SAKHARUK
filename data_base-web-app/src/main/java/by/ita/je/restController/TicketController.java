package by.ita.je.restController;

import by.ita.je.dto.TicketDto;
import by.ita.je.model.Ticket;
import by.ita.je.service.api.TicketService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TicketController {

    private final ObjectMapper objectMapper;
    private final TicketService ticketService;

    @GetMapping("/ticket/{id}")
    public TicketDto createTicketByIdSeat(@PathVariable("id") String id){
        final Ticket ticket=ticketService.bookTicket(Long.valueOf(id));
        return objectMapper.convertValue(ticket, TicketDto.class);
    }

    @DeleteMapping("/ticket/{id}")
    public void cancelTicket(@PathVariable("id") String id){
        ticketService.cancelTicket(Long.valueOf(id));

    }
}
