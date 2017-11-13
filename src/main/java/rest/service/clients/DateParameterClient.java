package rest.service.clients;

import org.springframework.stereotype.Service;
import rest.mappings.CurrencyDateJson;
import rest.service.AbstractClient;

@Service
public class DateParameterClient extends AbstractClient<CurrencyDateJson> {

    private final static String API_PATH = "/rates/a/{currency}/last/{last}/?format=json";

    public DateParameterClient() {
        super(CurrencyDateJson.class, API_PATH);
    }
}
