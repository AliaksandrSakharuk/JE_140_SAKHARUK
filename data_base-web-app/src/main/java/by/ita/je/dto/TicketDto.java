package by.ita.je.dto;

import by.ita.je.module.Flight;
import by.ita.je.module.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    private ZonedDateTime bookedDateTime;
    private Flight flight;
    private Seat seat;
}
