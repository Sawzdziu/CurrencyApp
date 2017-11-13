package controllers;

import calculation.CalculateService;
import dto.CalculationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dao.CurrencyDAO;
import model.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/currency")
@Api(description="Operation for simple currency calculations")
public class CurrencyController {

    @Autowired
    private CurrencyDAO currencyDAO;

    @Autowired
    private CalculateService calculateService;

    @GetMapping("/{id}")
    public String getCurrency(@PathVariable Integer id){
        return currencyDAO.getCurrencyById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAll(){
        return new ResponseEntity<>(currencyDAO.findAll(), HttpStatus.OK);
    }

    @PostMapping("/calculate")
    @ApiOperation(value= "Calculate converter for three currencies PLN/USD/EUR", response = Double.class)
    public Double getCalculation(@RequestBody CalculationDto calculationDto){
        return calculateService.calculate(calculationDto);
    }
}
