package org.aziendaagricola;

import org.aziendaagricola.service.AcquistoService;
import org.aziendaagricola.service.ProssimoRaccoltoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TaskSchedulata {
    @Autowired
    private ProssimoRaccoltoService prossimoRaccoltoService;
    @Autowired
    private AcquistoService acquistoService;
    @Scheduled(cron = "0 0 0 * * *")
    public void taskGiornaliera(){
        controlloData();
    }
    @EventListener(ApplicationReadyEvent.class)
    public void avvio(){
        controlloData();
    }
    public void controlloData(){
        //prossimo raccolto
        prossimoRaccoltoService.controllaData();
        //acquisto
        acquistoService.controllaData();
    }
}
