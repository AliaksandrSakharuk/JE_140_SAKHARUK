package by.ita.je.dto;

import by.ita.je.module.Address;
import by.ita.je.module.Plane;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirCompanyDto {
    private Long id;
    private String nameCompany;
    private long phoneNumber;
    private AddressDto address;
    private List<PlaneDto> planes;
}
