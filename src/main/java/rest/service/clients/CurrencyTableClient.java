package rest.service.clients;

import org.springframework.stereotype.Service;
import rest.mappings.Table;
import rest.service.AbstractClient;

@Service
public class CurrencyTableClient extends AbstractClient<Table[]> {

    private final static String API_PATH = "/tables/a/";

    public CurrencyTableClient() {
        super(Table[].class, API_PATH);
    }

}
