package by.ita.je.dto;

import by.ita.je.module.Passenger;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String firstName;
    private String secondName;
    private long phoneNumber;
    private Set<PassengerDto> passengers;
}
