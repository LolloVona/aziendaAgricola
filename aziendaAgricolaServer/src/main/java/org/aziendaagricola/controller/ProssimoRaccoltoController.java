package org.aziendaagricola.controller;

import org.aziendaagricola.DTO.RaccoltoCreateDTO;
import org.aziendaagricola.record.Errore;
import org.aziendaagricola.service.ProssimoRaccoltoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/raccolto")
public class ProssimoRaccoltoController {
    @Autowired
    private ProssimoRaccoltoService proxRaccoltoService;
    @PostMapping()
    public ResponseEntity<Object> aggiungiRaccolto(@RequestBody RaccoltoCreateDTO dto){
        if (dto.getIdUtente() == null) {
            Errore body=new Errore("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if(!dto.isValido()){
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
        if(!proxRaccoltoService.isAdmin((dto.getIdUtente()))){
            Errore body=new Errore("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        if(proxRaccoltoService.aggiungiRaccolto(dto)){
            //TODO file di log
            Errore body=new Errore("Aggiunto nuovo raccolto");
            return ResponseEntity.status(204).body(body);
        }
        else{
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
    }
}
