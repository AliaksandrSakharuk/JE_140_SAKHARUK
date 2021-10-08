package by.ita.je.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class AirCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameCompany;
    private long phoneNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "company")
    private Set<Plane> planes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adress_id")
    private Address address;
}
