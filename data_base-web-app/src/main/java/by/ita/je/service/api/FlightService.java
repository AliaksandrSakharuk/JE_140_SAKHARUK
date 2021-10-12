package by.ita.je.service.api;

import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Flight;

import java.util.List;

public interface FlightService {

    Flight save(Flight flight);

    Flight update(Long id, Flight flight);

    Flight readById(Long id) throws NotFoundData;

    List<Flight> readAll();

    void deleteById(Long id) throws NotFoundData;
}
