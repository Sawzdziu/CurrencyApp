package rest.service.clients;

import org.springframework.stereotype.Service;
import rest.mappings.PeriodCurrency;
import rest.service.AbstractClient;

@Service
public class DateParameterClient extends AbstractClient<PeriodCurrency>{

    private final static String API_PATH = "/rates/a/{currency}/{dateFrom}/{dateTo}/";

    public DateParameterClient() {
        super(PeriodCurrency.class, API_PATH);
    }
}
