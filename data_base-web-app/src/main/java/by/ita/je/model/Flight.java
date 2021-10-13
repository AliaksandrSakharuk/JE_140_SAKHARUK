package by.ita.je.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity

@NamedQuery(name="FindFlightByAirCompany", query = "SELECT flight FROM Flight flight JOIN flight.plane " +
        "JOIN flight.plane.company WHERE flight.plane.company.nameCompany=:name")
@NamedQuery(name="FindFlightByDuration", query = "SELECT flight FROM Flight flight WHERE flight.durationFlight<:duration")
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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private List<Seat> seats;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "flight")
//    private List<Ticket> tickets;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "plane_id")
    private Plane plane;
}
