package by.ita.je.service.api;

import by.ita.je.exception.NotCorrectData;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Plane;
import by.ita.je.model.Ticket;

public interface TicketService {
    Ticket bookTicket(long id) throws NotCorrectData;

    Ticket readById(Long id) throws NotFoundData;

    void cancelTicket(Long id) throws NotFoundData;
}
