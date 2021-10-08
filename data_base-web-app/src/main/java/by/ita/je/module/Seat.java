package by.ita.je.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numberSeat;
    private boolean booked;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;
}
