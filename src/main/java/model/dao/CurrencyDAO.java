package model.dao;

import model.entity.Currency;
import model.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CurrencyDAO {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Transactional
    public String getCurrencyById(Integer id){
        return currencyRepository.findOne(id).isPresent() ? currencyRepository.findOne(id).get().getName() : "There's no currency with given id!";
    }

    @Transactional
    public String getCurrencyByName(String name){
        return currencyRepository.findByName(name).isPresent() ? currencyRepository.findByName(name).get().getName() : "There's no currency with given name!";
    }

    @Transactional
    public List<Currency> findAll(){
        return currencyRepository.findAll().get();
    }
}
