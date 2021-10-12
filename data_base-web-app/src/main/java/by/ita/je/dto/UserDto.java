package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    private List<PassengerDto> passengers;
}
