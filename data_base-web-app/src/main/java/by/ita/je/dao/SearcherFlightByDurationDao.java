package by.ita.je.dao;

import by.ita.je.model.Flight;

import java.util.List;

public interface SearcherFlightByDurationDao {
    List<Flight> findFlightByDuration(int duration);
}
