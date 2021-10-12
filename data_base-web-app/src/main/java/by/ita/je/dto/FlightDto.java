package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightDto {
    private Long id;
    private String numberFlight;
    private String departureCity;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime departureDateTime;
    private String arriveCity;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime arriveDateTime;
    private PlaneDto plane;
    private List<SeatDto> seats;

}
