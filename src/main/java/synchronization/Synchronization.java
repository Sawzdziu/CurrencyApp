package synchronization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rest.mappings.TableA;
import rest.service.clients.CurrencyTableClient;

@Slf4j
@Service
public class Synchronization {

    @Autowired
    CurrencyTableClient currencyTableClient;

    @Scheduled(cron = "*/10 * * * * *")
    private void synchronize() {
//        log.info("Synchronization start at: " + System.currentTimeMillis());
//        TableA[] table = currencyTableClient.sendRequest();
//        TableA test = table[0];
//        System.out.println(test.getRates().get(1).toString());
//        log.info(test.getRates().get(1).toString());
    }

}
