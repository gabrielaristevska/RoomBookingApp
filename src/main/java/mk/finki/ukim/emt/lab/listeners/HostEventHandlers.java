package mk.finki.ukim.emt.lab.listeners;

import mk.finki.ukim.emt.lab.events.HostEvent;
import mk.finki.ukim.emt.lab.service.domain.HostService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class HostEventHandlers {
    private final HostService hostService;

    public HostEventHandlers(HostService hostService) {
        this.hostService = hostService;
    }

    @EventListener
    public void onHostEvent(HostEvent event){
        hostService.refreshMaterializedView();
    }
}
