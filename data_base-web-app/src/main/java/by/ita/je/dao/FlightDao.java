package by.ita.je.dao;

import by.ita.je.module.Flight;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDao extends CrudRepository<Flight, Long> {
}
