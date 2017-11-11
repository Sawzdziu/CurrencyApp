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
    public Currency getCurrencyById(Integer id){
        return currencyRepository.findOne(id);
    }

    @Transactional
    public Currency getCurrencyByName(String name){
        return currencyRepository.findByName(name);
    }

    @Transactional
    public List<Currency> findAll(){
        return currencyRepository.findAll();
    }
}
