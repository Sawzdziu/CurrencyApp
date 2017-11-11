package model.repository;

import model.entity.Currency;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CurrencyRepository extends CrudRepository<Currency, Integer> {

    Currency findOne(Integer id);
    List<Currency> findAll();
    Currency findByName(String name);
}
