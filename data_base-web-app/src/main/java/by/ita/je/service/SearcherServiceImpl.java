package by.ita.je.service;

import by.ita.je.dao.*;
import by.ita.je.dto.DateForFlightDto;
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

    private final SearcherFlightBySeatDao searcherFlightBySeatDao;
    private SearcherFreePlaneDao freePlanDao;
    private final SearcherFreeSeatOnFlightDao seatOnFlightDao;
    private final SearcherFlightByAirCompanyDao searcherFlightByAirCompanyDao;
    private final SearcherFlightByDurationDao searcherFlightByDurationDao;
    private final SearcherFlightWithPlaneChangeDao searcherFlightWithPlaneChangeDao;

    @Override
    public Flight findFlightBySeat(long id) {
        return searcherFlightBySeatDao.findFlightBySeat(id);
    }

    @Override
    public Plane findFreePlane(DateForFlightDto dateForFlightDto) {
        ZonedDateTime fromDateTime=dateForFlightDto.getDateTimeNewFlight().minusHours(4);
        ZonedDateTime toDateTime=dateForFlightDto.getDateTimeNewFlight().plusHours(4);
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
}
