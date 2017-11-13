package rest.service.clients;

import org.springframework.stereotype.Service;
import rest.mappings.CurrencyDateJSON;
import rest.service.AbstractClient;

@Service
public class AverageRateFromDateClient extends AbstractClient<CurrencyDateJSON> {

    private final static String API_PATH = "/rates/a/{currency}/{date}/";

    public AverageRateFromDateClient() {
        super(CurrencyDateJSON.class, API_PATH);
    }
}
