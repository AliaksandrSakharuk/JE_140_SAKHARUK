package by.ita.je.service.api;

import by.ita.je.model.AirCompany;
import by.ita.je.model.Flight;
import by.ita.je.model.Ticket;

import java.util.List;

public interface BusinessService {

    public Flight createNewFlight(Flight flight);

    public Flight findById(long id);

    public List<Flight> findAll();

    public AirCompany createNewAirCompany(AirCompany company);

    public Ticket bookTicket(long id);

    public void cancelBookedTicket(Long id);

}
