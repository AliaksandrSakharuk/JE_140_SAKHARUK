package by.ita.je.service;

import by.ita.je.dao.FlightDao;
import by.ita.je.module.Flight;
import by.ita.je.service.api.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDao flightDao;

    @Override
    public Flight save(Flight flight) {
        return null;
    }

    @Override
    public Flight update(Long id, Flight flight) {
        return null;
    }

    @Override
    public Flight readById(Long id) {
        return null;
    }

    @Override
    public List<Flight> readAll() {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
