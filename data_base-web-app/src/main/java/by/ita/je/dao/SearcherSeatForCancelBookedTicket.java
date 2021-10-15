//package by.ita.je.dao;
//
//import by.ita.je.model.Flight;
//import by.ita.je.model.Seat;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public interface SearcherSeatForCancelBookedTicket extends JpaRepository<Seat, Long> {
//    @Query(value = "SELECT DISTINCT  seat.* FROM seat RIGHT JOIN flight on flight.id=seat.flight_id  " +
//            "WHERE number_flight=:numberFlight AND number_seat=:numberSeat"
//            , nativeQuery = true)
//    Seat findSeatForCancelBookedTicket(String numberFlight, String numberSeat);
//}
