package by.ita.je.restController;

import by.ita.je.dto.SeatDto;
import by.ita.je.model.Seat;
import by.ita.je.service.api.SearcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class SeatController {
    private final ObjectMapper objectMapper;
    private final SearcherService searcherService;

    @GetMapping("/flight/seat/free/{id}")
    public List<SeatDto> findAll(@PathVariable("id") String id){
        List <Seat> list=searcherService.findFreeSeat(Long.valueOf(id));
        List<SeatDto> listSeat=list.stream()
                .map(seat -> objectMapper.convertValue(seat, SeatDto.class))
                .collect(Collectors.toList());
        return listSeat;
    }
}
