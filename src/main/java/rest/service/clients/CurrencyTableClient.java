package rest.service.clients;

import org.springframework.stereotype.Service;
import rest.mappings.TableA;
import rest.service.AbstractClient;

@Service
public class CurrencyTableClient extends AbstractClient<TableA[]> {

    private final static String API_PATH = "/tables/a/";

    public CurrencyTableClient() {
        super(TableA[].class, API_PATH);
    }

}
