package controllers;

import model.dao.CurrencyDAO;
import model.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyDAO currencyDAO;

    @GetMapping("/index")
    public String index(){
        return "Hello World!";
    }

    @GetMapping("/currency/{id}")
    public String getCurrency(@PathVariable Integer id){
        return currencyDAO.getCurrencyById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAll(){
        return new ResponseEntity<>(currencyDAO.findAll(), HttpStatus.OK);
    }
}
