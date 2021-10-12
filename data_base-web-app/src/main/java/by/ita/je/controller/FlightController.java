package by.ita.je.controller;


import by.ita.je.dto.FlightDto;
import by.ita.je.model.Flight;
import by.ita.je.service.api.FlightService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class FlightController {

    private final ObjectMapper objectMapper;
    private final FlightService flightService;

    @PostMapping("/flight")
    public FlightDto create(@RequestBody FlightDto flightDto){
        final Flight flightNew = objectMapper.convertValue(flightDto, Flight.class);
        final Flight flight=flightService.save(flightNew);
        return objectMapper.convertValue(flight, FlightDto.class);
    }

    @GetMapping("/flight/{id}")
    public FlightDto findById(@PathVariable("id") String id){
        final Flight flight=flightService.readById(Long.valueOf(id));
        return objectMapper.convertValue(flight, FlightDto.class);
    }

    @DeleteMapping("/flight/{id}")
    public void delleteById(@PathVariable("id") String id){
        flightService.deleteById(Long.valueOf(id));
    }

}
