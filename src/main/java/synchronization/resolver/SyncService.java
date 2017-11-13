package synchronization.resolver;

import lombok.extern.slf4j.Slf4j;
import model.dao.CurrencyDAO;
import model.dao.SynchronizeDAO;
import model.entity.Currency;
import model.entity.Synchronize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.mappings.CurrencyJSON;
import rest.mappings.Table;
import rest.service.clients.CurrencyTableClient;

import java.sql.Date;
import java.util.List;

@Slf4j
@Service
public class SyncService {

    @Autowired
    private CurrencyTableClient currencyTableClient;

    @Autowired
    private CurrencyDAO currencyDAO;

    @Autowired
    private SynchronizeDAO synchronizeDAO;

    public void continueSync() {
        resolveResponse();
    }

    private void resolveResponse() {
        Table[] table = currencyTableClient.sendRequest();
        parseCurrencyTable(table[0].getRates(), new Date(table[0].getEffectiveDate().getTime()));
    }

    private void parseCurrencyTable(List<CurrencyJSON> currencyJSON, Date date) {
        for (CurrencyJSON item : currencyJSON) {
            for (CurrencyEnum currencyEnum : CurrencyEnum.values()) {
                if (item.getCode().equals(currencyEnum.toString())) {
                    save(item, date);
                }
            }
        }
    }

    private void save(CurrencyJSON currencyJSON, Date date) {
        Synchronize synchronize = new Synchronize();
        synchronize.setCurrencyByCurrencyId(getCurrency(currencyJSON.getCode()));
        synchronize.setValue(currencyJSON.getMid());
        synchronize.setDate(date);
        synchronizeDAO.save(synchronize);
    }

    private Currency getCurrency(String name){
        return currencyDAO.getCurrencyByName(name);
    }
}
