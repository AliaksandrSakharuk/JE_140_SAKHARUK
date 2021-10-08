package by.ita.je.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long invertorNumber;
    private String namePilot;
    private  int quantitySeats;
    private byte seatsInLine;
    private byte quantityLine;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private AirCompany company;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "plane")
    private Set<Flight> flights;

}
