package synchronization;

import lombok.extern.slf4j.Slf4j;
import model.dao.SynchronizeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import synchronization.resolver.SyncService;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Slf4j
@Service
public class Synchronization {

    @Autowired
    private SyncService syncService;

    @Autowired
    private SynchronizeDAO synchronizeDAO;

//    @Scheduled(cron = "*/10 * * * * *")
    @Scheduled(cron = "0 0 14 * * 1,2,3,4,5")
    private void synchronize() {
        log.info("Synchronization start at: " + System.currentTimeMillis());
        Date lastSynchronization = synchronizeDAO.getLastSynchronizeDate();
        if (lastSynchronization == null || !compareDates(lastSynchronization)) {
            syncService.continueSync();
            log.info("Last synchronize date: " + lastSynchronization);
        } else {
            log.info("Synchronization up to date!");
        }
    }

    private boolean compareDates(Date date) {
        LocalDate lastSynchronization = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(date));
        LocalDate nowDate = LocalDate.parse(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        return lastSynchronization.isEqual(nowDate);
    }
}
