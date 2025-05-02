package mk.finki.ukim.emt.lab.jobs;

import mk.finki.ukim.emt.lab.service.domain.AccommodationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {
    private final AccommodationService accommodationService;

    public ScheduledTasks(AccommodationService accommodationService) {
        this.accommodationService = accommodationService;
    }

    @Scheduled(cron = "00 13 * * * *", zone = "Europe/Skopje")
    public void refreshMaterializedView(){
        accommodationService.refreshMaterializedView();
    }
}
