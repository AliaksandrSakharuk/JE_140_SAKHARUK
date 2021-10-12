package by.ita.je.dao;

import by.ita.je.model.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;

public interface FindFreePlaneDao extends JpaRepository<Plane, Long> {
    @Query(value="SELECT DISTINCT plane.* FROM plane LEFT JOIN flight ON flight.plane_id = plane.id " +
            "WHERE flight.plane_id IS NULL OR (flight.departure_date_time NOT BETWEEN  :from_date_time AND :to_date_time) " +
            "LIMIT 0,1", nativeQuery = true)
    Plane findFreePlane(@Param("from_date_time") ZonedDateTime fromDateTime
            , @Param("to_date_time") ZonedDateTime toDateTime);
}
