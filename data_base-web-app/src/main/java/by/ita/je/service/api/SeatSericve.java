package by.ita.je.service.api;

import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Seat;
import by.ita.je.model.Ticket;

public interface SeatSericve {

    void cancelBooked(Long id) throws NotFoundData;

    Seat readById(Long id) throws NotFoundData ;
}
