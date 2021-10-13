package by.ita.je.restController;

import by.ita.je.dto.AirCompanyDto;
import by.ita.je.model.AirCompany;
import by.ita.je.service.api.AirCompanyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
public class AirCompanyController {

    private final ObjectMapper objectMapper;
    private final AirCompanyService companyService;



    @GetMapping("/company/{id}")
    public AirCompanyDto findById(@PathVariable("id") String id){
        final AirCompany company=companyService.readById(Long.valueOf(id));
        return objectMapper.convertValue(company, AirCompanyDto.class);
    }

    @PutMapping(value = "/company")
    public AirCompanyDto update(@RequestParam(value = "id", required = false) String id, @RequestBody AirCompanyDto companyDto){
        final AirCompany company=objectMapper.convertValue(companyDto, AirCompany.class);
        AirCompany companyUpdate=companyService.update(Long.valueOf(id), company);
        return objectMapper.convertValue(companyUpdate, AirCompanyDto.class);
    }

    @GetMapping("/company/list")
    public  List<AirCompanyDto> findAll(){
        List <AirCompany> list=companyService.readAll();
        List<AirCompanyDto> listCompany=list.stream()
                .map(company -> objectMapper.convertValue(company, AirCompanyDto.class))
                .collect(Collectors.toList());
        return listCompany;
    }

    @DeleteMapping("/company/{id}")
    public void delleteById(@PathVariable("id") String id){
        companyService.deleteById(Long.valueOf(id));
    }




}
