package by.ita.je.service.api;

import by.ita.je.module.Flight;

import java.util.List;

public interface FlightService {

    Flight save(Flight flight);

    Flight update(Long id, Flight flight);

    Flight readById(Long id);

    List<Flight> readAll();

    void deleteById(Long id);
}
