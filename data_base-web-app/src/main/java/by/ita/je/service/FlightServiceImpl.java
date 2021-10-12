package by.ita.je.service;

import by.ita.je.dao.FlightDao;
import by.ita.je.dto.DateForFlightDto;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;
import by.ita.je.model.Plane;
import by.ita.je.model.Seat;
import by.ita.je.service.api.FlightService;
import by.ita.je.service.api.SearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;
    @Autowired
    private final SearcherService searcherService;

    @Override
    public Flight save(Flight flight) {
        Plane freePlane=searcherService.findFreePlane(getDateForFlightDto(flight.getDepartureDateTime()));
        flight.setPlane(freePlane);
        createSeat(flight);
        flight.setDurationFlight((int) ChronoUnit.MINUTES.between(flight.getDepartureDateTime(), flight.getArriveDateTime()));
        return flightDao.save(flight);
    }

    @Override
    public Flight update(Long id, Flight flight) {
        return null;
    }

    @Override
    public Flight readById(Long id) throws NotFoundData{
        final Flight flight=flightDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Flight"));
        return flight;
    }

    @Override
    public List<Flight> readAll() {
        final Spliterator<Flight> result = flightDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) throws NotFoundData{
        try {
            flightDao.deleteById(id);
        }catch (Exception e){
            throw new NotFoundData("Flight");
        }
    }

    private void createSeat(Flight flight){
        List<Seat> list=new ArrayList<Seat>();
        for(int numberLine=1; numberLine<=flight.getPlane().getQuantityLines();numberLine++){
            char num='A';
            for(int numSeatInLine=0; numSeatInLine<flight.getPlane().getSeatsInLine();numSeatInLine++){
                String numSeat=numberLine + Character.toString(num);
                list.add(Seat.builder().numberSeat(numSeat).build());
                num++;
            }
        }
        flight.setSeats(list);
    }
    private DateForFlightDto getDateForFlightDto(ZonedDateTime dateTime){
        DateForFlightDto fieldDto = new DateForFlightDto();
        fieldDto.setDateTimeNewFlight(dateTime);
        return fieldDto;

    }
}
