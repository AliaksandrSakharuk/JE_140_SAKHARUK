package by.ita.je.dao;

import by.ita.je.module.AirCompany;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirCompanyDao extends CrudRepository<AirCompany, Long> {
}
