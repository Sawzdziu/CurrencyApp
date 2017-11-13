package controllers;

import calculation.CalculateService;
import calculation.StatisticService;
import dto.CalculationDto;
import dto.StatisticDto;
import dto.StatisticInputDto;
import dto.validators.CalculationDtoValidator;
import dto.validators.StatisticInputDtoValidator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.dao.CurrencyDAO;
import model.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/currency")
@Api(description = "Operation for simple currency calculations")
public class CurrencyController {

    @Autowired
    private CurrencyDAO currencyDAO;

    @Autowired
    private CalculationDtoValidator calculationDtoValidator;

    @Autowired
    private StatisticInputDtoValidator statisticInputDtoValidator;

    @Autowired
    private CalculateService calculateService;

    @Autowired
    private StatisticService statisticService;


    @GetMapping("/{id}")
    public String getCurrency(@PathVariable Integer id) {
        return currencyDAO.getCurrencyById(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Currency>> getAll() {
        return new ResponseEntity<>(currencyDAO.findAll(), HttpStatus.OK);
    }

    @PostMapping("/calculate")
    @ApiOperation(value = "Calculate converter for three currencies PLN/USD/EUR(http://www.nbp.pl/home.aspx?f=/kursy/kursya.html)", response = Double.class)
    public Double getCalculation(@RequestBody CalculationDto calculationDto, BindingResult bindingResult) throws BindException {

        calculationDtoValidator.validate(calculationDto, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        } else
            return calculateService.calculate(calculationDto);
    }

    @PostMapping("/statistic")
    @ApiOperation(value = "Finds statistics for all currencies(http://www.nbp.pl/home.aspx?f=/kursy/kursya.html), expect for PLN", response = Double.class)
    public ResponseEntity<StatisticDto> getStatistic(@RequestBody StatisticInputDto statisticInputDto, BindingResult bindingResult) throws BindException {

        statisticInputDtoValidator.validate(statisticInputDto, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        } else {
            return new ResponseEntity<>(statisticService.resolve(statisticInputDto), HttpStatus.OK);
        }
    }
}
