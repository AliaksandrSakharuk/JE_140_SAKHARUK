package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private ZonedDateTime bookedDateTime;
    private String firstNamePassenger;
    private String secondNamePassenger;
    private long phoneNumberPassenger;
    private String passportNumberPassenger;
    private SeatDto seat;
}
