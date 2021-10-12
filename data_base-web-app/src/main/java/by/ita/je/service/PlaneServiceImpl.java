package by.ita.je.service;

import by.ita.je.dao.PlaneDao;
import by.ita.je.exception.NotFoundData;
import by.ita.je.model.Plane;
import by.ita.je.service.api.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class PlaneServiceImpl implements PlaneService {

    @Autowired
    PlaneDao planeDao;

    @Override
    public Plane save(Plane plane) {
        return planeDao.save(plane);
    }

    @Override
    public Plane update(Long id, Plane planeNew) throws NotFoundData{
        final Plane plane = planeDao.findById(id)
                .orElseThrow(() -> new NotFoundData( "Plane"));
        if(planeNew.getNamePlane()!="") plane.setNamePlane(planeNew.getNamePlane());
        if(planeNew.getInvertorNumber()!=0) plane.setInvertorNumber(planeNew.getInvertorNumber());
        if(planeNew.getNamePilot()!="") plane.setNamePilot(planeNew.getNamePilot());
        return planeDao.save(plane);

    }

    @Override
    public Plane readById(Long id) throws NotFoundData{
        final Plane plane=planeDao.findById(id)
                .orElseThrow(() -> new NotFoundData("Plane"));

        return plane;
    }

    @Override
    public void deleteById(Long id) throws NotFoundData{
        try {
            planeDao.deleteById(id);
        }catch (Exception e){
            throw new NotFoundData("Plane");
        }
    }
}
