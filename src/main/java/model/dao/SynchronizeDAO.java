package model.dao;

import model.entity.Synchronize;
import model.repository.SynchronizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public class SynchronizeDAO {

    @Autowired
    private SynchronizeRepository synchronizeRepository;

    @Transactional
    public Date getLastSynchronizeDate(){
        return synchronizeRepository.findTopByOrderByDateDesc().isPresent() ? synchronizeRepository.findTopByOrderByDateDesc().get().getDate() : null;
    }

    @Transactional
    public void save(Synchronize synchronize){
        synchronizeRepository.save(synchronize);
    }
}
