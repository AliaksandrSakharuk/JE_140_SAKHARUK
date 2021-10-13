package by.ita.je.dao.impl;

import by.ita.je.dao.SearcherFlightByDurationDao;
import by.ita.je.model.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SearcherFlightByDurationDaoImpl implements SearcherFlightByDurationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flight> findFlightByDuration(int duration) {
        Query query= entityManager.createNamedQuery("FindFlightByDuration", Flight.class);
        query.setParameter("duration", duration);
        List<Flight> flightList=query.getResultList();
        return flightList;
    }
}
