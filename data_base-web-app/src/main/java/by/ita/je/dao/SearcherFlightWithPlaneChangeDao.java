package by.ita.je.dao;

import by.ita.je.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SearcherFlightWithPlaneChangeDao extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT connection,  flight.* FROM flight LEFT JOIN (SELECT f1.id AS first_id, f2.id AS second_id, CONCAT(f1.departure_city, '-', f2.arrive_city) AS connection\n" +
            "    FROM flight AS f1 LEFT JOIN flight AS f2 ON f2.departure_city = f1.arrive_city -- AND f2.departure_date_time > f1.arrive_date_time\n" +
            "    WHERE f2.id IS NOT NULL) AS connections ON connections.first_id = flight.id OR connections.second_id = flight.id WHERE connection IS NOT NULL", nativeQuery = true)
    Set<Flight> findFlightByChangePlane(String fromCity, String toCity);
}
