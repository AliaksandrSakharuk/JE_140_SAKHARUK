package by.ita.je.dto;

import by.ita.je.module.Address;
import by.ita.je.module.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private Long id;
    private String firstName;
    private String secondName;
    private long phoneNumber;
    private String passportNumber;
    private Set<TicketDto> tickets;
    private Address address;
}
