package by.ita.je.dao.impl;

import by.ita.je.dao.SearcherFlightByAirCompanyDao;
import by.ita.je.model.Flight;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
@Repository
public class SearcherFlightByAirCompanyDaoImpl implements SearcherFlightByAirCompanyDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Flight> findFlightByAirCompany(String nameCompany) {
        Query query= entityManager.createNamedQuery("FindFlightByAirCompany", Flight.class);
        query.setParameter("name", nameCompany);
        List<Flight> flightList=query.getResultList();
        return flightList;
    }
}
