package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDto {
    private Long id;
    private String firstName;
    private String secondName;
    private long phoneNumber;
    private String passportNumber;
    private List<TicketDto> tickets;
    private AddressDto address;
}
