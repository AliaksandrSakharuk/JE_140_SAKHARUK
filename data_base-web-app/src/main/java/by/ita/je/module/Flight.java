package by.ita.je.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numberFlight;
    private String departureCity;
    private ZonedDateTime departureDateTime;
    private int durationFlight;
    private String arriveCity;
    private ZonedDateTime arriveDateTime;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "flight")
    private Set<Seat> seats;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "flight")
    private Set<Ticket> tickets;

    @ManyToOne
    @JoinColumn(name = "plane_id")
    private Plane plane;
}
