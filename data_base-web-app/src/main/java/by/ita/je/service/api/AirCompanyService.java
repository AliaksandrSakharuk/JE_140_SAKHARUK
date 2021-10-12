package by.ita.je.service.api;

import by.ita.je.model.AirCompany;
import java.util.List;
public interface AirCompanyService {

    AirCompany save(AirCompany company);

    AirCompany update(Long id, AirCompany company);

    AirCompany readById(Long id);

    List<AirCompany> readAll();

    void deleteById(Long id);


}
