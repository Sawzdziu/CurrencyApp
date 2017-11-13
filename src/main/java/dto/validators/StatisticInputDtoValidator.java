package dto.validators;

import dto.StatisticInputDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Lazy
@Component
public class StatisticInputDtoValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return StatisticInputDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        StatisticInputDto statisticInputDto = (StatisticInputDto) target;

        if (statisticInputDto == null)
            errors.reject("nullObject", "There's no body!");
        if (!errors.hasErrors())
            ValidationUtils.rejectIfEmpty(errors, "code", "code.empty", "Code field is empty!");
        if (!errors.hasErrors())
            ValidationUtils.rejectIfEmpty(errors, "days", "days.empty", "Days field is empty!");
        if (!errors.hasErrors())
            validateCode(statisticInputDto, errors);
        if (!errors.hasErrors())
            validateDays(statisticInputDto, errors);
    }

    private void validateCode(StatisticInputDto statisticInputDto, Errors errors) {
        Pattern pattern = Pattern.compile("USD|EUR", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(statisticInputDto.getCode());
        if (!matcher.find())
            errors.rejectValue("code", "code.invalid", "Code field is invalid! Use: EUR or USD or from : http://www.nbp.pl/home.aspx?f=/kursy/kursya.html");
    }

    private void validateDays(StatisticInputDto statisticInputDto, Errors errors){
        if(statisticInputDto.getDays() <= 0)
            errors.rejectValue("days", "days.invalid", "Days field is invalid! Value must be grater than 0");
        if(statisticInputDto.getDays() > 255)
            errors.rejectValue("days", "days.invalid", "Maximum size of 255 data series has been exceeded");
    }
}
