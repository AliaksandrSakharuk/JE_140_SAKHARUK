package by.ita.je.dao;

import by.ita.je.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SearcherFlightWithPlaneChangeDao extends JpaRepository<Flight, Long> {
    @Query(value = "SELECT f.*, connection, TIMESTAMPDIFF(HOUR, f.departure_date_time, f.arrive_date_time) AS duration, full_duration, ac.name_company\n" +
            "FROM flight AS f INNER JOIN plane AS p ON p.id = f.plane_id INNER JOIN air_company AS ac ON ac.id = p.company_id\n" +
            "LEFT JOIN (SELECT f1.id AS first_id, f2.id AS second_id, CONCAT(f1.departure_city, '-', f2.arrive_city) AS connection, TIMESTAMPDIFF(HOUR, f1.departure_date_time, f2.arrive_date_time) AS full_duration\n" +
            " FROM flight AS f1 INNER JOIN flight AS f2 ON f2.departure_city = f1.arrive_city) AS connections ON connections.first_id = f.id OR connections.second_id = f.id\n" +
            "WHERE  connection IS  NULL AND ac.name_company LIKE '%BELAVIA%'\n" +
            "HAVING duration < 744", nativeQuery = true)
    Set<Flight> findFlightByChangePlane(String fromCity, String toCity);
}
