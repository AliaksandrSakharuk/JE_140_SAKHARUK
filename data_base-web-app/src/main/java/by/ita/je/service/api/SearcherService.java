package by.ita.je.service.api;

import by.ita.je.dto.DateForFlightDto;
import by.ita.je.model.Flight;
import by.ita.je.model.Plane;
import by.ita.je.model.Seat;

import java.util.List;
import java.util.Set;

public interface SearcherService {
    Flight findFlightBySeat(long id);

    Plane findFreePlane(DateForFlightDto dateForFlightDto);

    List<Seat> findFreeSeat(long id);

    List<Flight> findFlightByAirCompany(String nameCompany);

    List<Flight> findFlightByDuration(int duration);

    Set<Flight> findFlightWithPlaneChange();

}
