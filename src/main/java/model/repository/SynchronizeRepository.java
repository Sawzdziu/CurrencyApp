package model.repository;

import model.entity.Synchronize;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SynchronizeRepository extends CrudRepository<Synchronize, Integer> {

    Synchronize findOne(Integer id);
    List<Synchronize> findAll();
    Optional<Synchronize> findTopByOrderByDateDesc();
}
