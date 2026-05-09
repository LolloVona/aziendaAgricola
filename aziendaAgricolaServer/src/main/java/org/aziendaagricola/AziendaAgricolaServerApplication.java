package org.aziendaagricola;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AziendaAgricolaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AziendaAgricolaServerApplication.class, args);
    }

}
