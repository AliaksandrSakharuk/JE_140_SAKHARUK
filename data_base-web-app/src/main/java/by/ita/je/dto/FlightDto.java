package by.ita.je.dto;

import by.ita.je.module.Plane;
import by.ita.je.module.Seat;
import by.ita.je.module.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long id;
    private String numberFlight;
    private String departureCity;
    private ZonedDateTime departureDateTime;
    private String arriveCity;
    private ZonedDateTime arriveDateTime;
    private PlaneDto plane;
    private Set<SeatDto> seats;
}
