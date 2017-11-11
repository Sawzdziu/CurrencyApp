package model.repository;

import model.entity.Currency;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends Repository<Currency, Integer> {

    Optional<Currency> findOne(Integer id);
    Optional<List<Currency>> findAll();
    Optional<Currency> findByName(String name);
}
