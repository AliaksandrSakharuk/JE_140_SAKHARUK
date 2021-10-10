package by.ita.je.dto;

import by.ita.je.module.AirCompany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaneDto {
    private Long id;
    private Long invertorNumber;
    private String namePlane;
    private String namePilot;
}
