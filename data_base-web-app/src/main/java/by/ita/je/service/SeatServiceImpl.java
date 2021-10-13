package by.ita.je.service;

import by.ita.je.dao.SeatDao;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Seat;
import by.ita.je.service.api.SeatSericve;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SeatServiceImpl implements SeatSericve {
    @Autowired
    private final SeatDao seatDao;

    @Override
    public void cancelBooked(Long id) throws NotFoundData {
        Seat seat=seatDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Seat"));
        seat.setBooked(false);
        seatDao.save(seat);
    }

    @Override
    public Seat readById(Long id) throws NotFoundData {
        final Seat seat=seatDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Seat"));

        return seat;
    }
}
