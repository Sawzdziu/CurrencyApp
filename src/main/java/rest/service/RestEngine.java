package rest.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class RestEngine {

    private final RestTemplate restTemplate = new RestTemplate();

    protected RestTemplate getRestTemplate() {
        return this.restTemplate;
    }
}
