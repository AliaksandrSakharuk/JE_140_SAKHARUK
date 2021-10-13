package by.ita.je.dao;

import by.ita.je.model.Flight;
import by.ita.je.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SearcherFlightBySeatDao extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT DISTINCT flight.* FROM flight RIGHT JOIN seat ON flight.id=seat.flight_id WHERE seat.id=:id"
            , nativeQuery = true)
    Flight findFlightBySeat(long id);
}
