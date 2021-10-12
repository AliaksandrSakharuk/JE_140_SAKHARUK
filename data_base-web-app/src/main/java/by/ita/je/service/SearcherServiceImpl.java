package by.ita.je.service;

import by.ita.je.dao.FindFlightBySeatDao;
import by.ita.je.dao.FindFreePlaneDao;
import by.ita.je.dao.FindFreeSeatOnFlightDao;
import by.ita.je.dto.DateForFlightDto;
import by.ita.je.model.Flight;
import by.ita.je.model.Plane;
import by.ita.je.model.Seat;
import by.ita.je.service.api.SearcherService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class SearcherServiceImpl implements SearcherService {

    @Autowired
    FindFlightBySeatDao findFlightBySeatDao;
    @Autowired
    private FindFreePlaneDao freePlanDao;
    @Autowired
    private final FindFreeSeatOnFlightDao seatOnFlightDao;

    @Override
    public Flight findFlightBySeat(long id) {
        return findFlightBySeatDao.findFlightBySeat(id);
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
}
