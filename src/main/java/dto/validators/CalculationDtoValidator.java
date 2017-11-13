package dto.validators;

import dto.CalculationDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Lazy
@Component
public class CalculationDtoValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return CalculationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        CalculationDto calculationDto = (CalculationDto) target;

        if (calculationDto == null)
            errors.reject("nullObject", "There's no body!");
        if (!errors.hasErrors())
            ValidationUtils.rejectIfEmpty(errors, "value", "value.empty", "Value field is empty!");
        if (!errors.hasErrors())
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "fromCurrency", "fromCurrency.empty", "fromCurrency field is empty!");
        if (!errors.hasErrors())
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "toCurrency", "toCurrency.empty", "toCurrency field is empty!");
        if (!errors.hasErrors())
            validateCurrencies(calculationDto, errors);
    }

    private void validateCurrencies(CalculationDto calculationDto, Errors errors) {
        Pattern pattern = Pattern.compile("PLN|USD|EUR", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(calculationDto.getToCurrency());
        if (!matcher.find())
            errors.rejectValue("toCurrency", "toCurrency.invalid", "toCurrency field is invalid! Use: PLN/EUR/USD");
        matcher = pattern.matcher(calculationDto.getFromCurrency());
        if (!matcher.find())
            errors.rejectValue("fromCurrency", "fromCurrency.invalid", "fromCurrency field is invalid! Use: PLN/EUR/USD");
    }
}
