package by.ita.je.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DateForFlightDto {
    @JsonFormat
    private ZonedDateTime dateTimeNewFlight;
}
