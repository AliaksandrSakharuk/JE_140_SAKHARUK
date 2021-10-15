package by.ita.je.service;

import by.ita.je.dao.*;
import by.ita.je.dto.FieldSearcherDto;
import by.ita.je.model.Flight;
import by.ita.je.model.Plane;
import by.ita.je.model.Seat;
import by.ita.je.service.api.SearcherService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class SearcherServiceImpl implements SearcherService {
//
//    private final SearcherFlightBySeat searcherFlightBySeat;
    private SearcherFreePlaneDao freePlanDao;
    private final SearcherFreeSeatOnFlightDao seatOnFlightDao;
    private final SearcherFlightByAirCompanyDao searcherFlightByAirCompanyDao;
    private final SearcherFlightByDurationDao searcherFlightByDurationDao;
    private final SearcherFlightWithPlaneChangeDao searcherFlightWithPlaneChangeDao;
//    private final SearcherSeatForCancelBookedTicket searcherSeatForCancelBookedTicket;
//
//    @Override
//    public Flight findFlightBySeat(long id) {
//        return searcherFlightBySeat.findFlightBySeat(id);
//    }

//    @Override
//    public Seat findSeatForCancelBookedTicket(String numberFlight, String numberSeat) {
//        return searcherSeatForCancelBookedTicket.findSeatForCancelBookedTicket(numberFlight, numberSeat);
//    }

    @Override
    public Plane findFreePlane(ZonedDateTime dateForFlightDto) {
        ZonedDateTime fromDateTime=dateForFlightDto.minusHours(8);
        ZonedDateTime toDateTime=dateForFlightDto.plusHours(8);
        return freePlanDao.findFreePlane(fromDateTime, toDateTime);
    }

    @Override
    public List<Seat> findFreeSeat(long id) {
        return seatOnFlightDao.findFreeSeatOnFlight(id);
    }

    @Override
    public List<Flight> findFlightByAirCompany(String nameCompany) {
        return searcherFlightByAirCompanyDao.findFlightByAirCompany(nameCompany);
    }

    @Override
    public List<Flight> findFlightByDuration(int duration) {

        return searcherFlightByDurationDao.findFlightByDuration(duration);
    }

    @Override
    public Set<Flight> findFlightWithPlaneChange() {

        return searcherFlightWithPlaneChangeDao.findFlightByChangePlane("BREST", "MOSCOW");
    }

    @Override
    public List<Flight> findFlightByCondition(FieldSearcherDto fieldSearcherDto) {

        return null;
    }
}
