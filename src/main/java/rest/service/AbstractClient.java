package rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

public abstract class AbstractClient<T> {

    @Autowired
    private RestEngine restEngine;

    private String REST_API_PATH = "http://api.nbp.pl/api/exchangerates";

    private final String apiPath;
    private final HttpEntity httpEntity;
    private final Class<T> responseClass;

    public AbstractClient(Class<T> responseClass, String apiPath) {
        this.responseClass = responseClass;
        this.apiPath = REST_API_PATH + apiPath;
        this.httpEntity = initialize();
    }

    private HttpEntity initialize() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    public T sendRequest(String... vars){
        return restEngine.getRestTemplate().exchange(apiPath, HttpMethod.GET, httpEntity, responseClass, vars).getBody();
    }
}
