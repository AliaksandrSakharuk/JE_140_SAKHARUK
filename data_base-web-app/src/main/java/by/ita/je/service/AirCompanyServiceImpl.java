package by.ita.je.service;

import by.ita.je.dao.AirCompanyDao;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.AirCompany;
import by.ita.je.model.Plane;
import by.ita.je.service.api.AirCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class AirCompanyServiceImpl implements AirCompanyService {

    @Autowired
    private AirCompanyDao companyServiceDao;

    @Override
    public AirCompany save(AirCompany company) {
        createIfNotRelationshipAirCompanyToPlanes(company);
        return companyServiceDao.save(company);
    }

    @Override
    public AirCompany update(Long id, AirCompany companyNew) {
        final AirCompany airCompany = companyServiceDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "AirCompany"));
        if(companyNew.getNameCompany()!="") airCompany.setNameCompany(companyNew.getNameCompany());
        if(companyNew.getPhoneNumber()!=0) airCompany.setPhoneNumber(companyNew.getPhoneNumber());
        return companyServiceDao.save(airCompany);
    }

    @Override
    public AirCompany readById(Long id) {
        final AirCompany company=companyServiceDao.findById(id)
                .orElseThrow(() -> new NotFoundData("AirCompany"));
        return company;
    }

    @Override
    public List<AirCompany> readAll() {
        final Spliterator<AirCompany> result = companyServiceDao.findAll().spliterator();
        return StreamSupport
                .stream(result, false)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        try {
            companyServiceDao.deleteById(id);
        }catch (Exception e){
            throw new NotFoundData("AirCompany");
        }
    }

    private void createIfNotRelationshipAirCompanyToPlanes(AirCompany company){
        if (Objects.isNull(company.getPlanes()) || company.getPlanes().isEmpty()){
            Plane boeing=cteateBoeing737_500();
            Plane embrare=createEmbraer195();
            company.setPlanes(List.of(boeing, embrare));
        }
    }


    private Plane cteateBoeing737_500(){
       return Plane.builder()
                .namePlane("Boeing 737-500")
                .namePilot("James Bond")
                .quantitySeats(144)
                .seatsInLine(6)
                .quantityLines(24)
                .invertorNumber(123456432L)
                .build();
    }

    private Plane createEmbraer195(){
        return Plane.builder()
                .namePlane("Embraer 195")
                .namePilot("SCALA")
                .quantitySeats(114)
                .seatsInLine(6)
                .quantityLines(19)
                .invertorNumber(7777777L)
                .build();
    }

}
