package by.ita.je.dao;

import by.ita.je.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface FindFreeSeatOnFlightDao extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT * FROM SEAT seat INNER JOIN FLIGHT ON seat.flight_id=:id WHERE " +
            "seat.booked=false"
            , nativeQuery = true)
    List<Seat> findFreeSeatOnFlight(@Param("id") long id);
}
