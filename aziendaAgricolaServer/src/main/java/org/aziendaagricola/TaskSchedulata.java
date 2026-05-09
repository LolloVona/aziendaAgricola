package org.aziendaagricola;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedulata {
    @Scheduled(cron = "0 0 0 * * *")
    public void taskGiornaliera(){}
    @EventListener(ApplicationReadyEvent.class)
    public void avvio(){
        controlloDati();
    }
    public void controlloDati(){
        //prossimo raccolto
        //acquisto
    }
}
