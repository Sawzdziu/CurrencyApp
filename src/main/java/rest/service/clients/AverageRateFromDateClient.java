package rest.service.clients;

import org.springframework.stereotype.Service;
import rest.mappings.CurrencyDateJson;
import rest.service.AbstractClient;

@Service
public class AverageRateFromDateClient extends AbstractClient<CurrencyDateJson> {

    private final static String API_PATH = "/rates/a/{currency}/{date}/";

    public AverageRateFromDateClient() {
        super(CurrencyDateJson.class, API_PATH);
    }
}
