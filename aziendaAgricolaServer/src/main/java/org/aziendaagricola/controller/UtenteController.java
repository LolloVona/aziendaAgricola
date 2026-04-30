package org.aziendaagricola.controller;

import org.aziendaagricola.DTO.ProdottoCreateDTO;
import org.aziendaagricola.DTO.UtenteCreateDTO;
import org.aziendaagricola.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/utente")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @PostMapping()
    private ResponseEntity<String> registra(@RequestBody UtenteCreateDTO dto){
        //TODO sistemare codici di stato
        if(dto.convalidaDati()) {
            if (utenteService.salva(dto))
                return ResponseEntity.status(201).body("Operazione non consentita!");//operazione riuscita
            return ResponseEntity.status(403).body("Operazione non consentita!");//errore user già presente
        }
        else{
            return ResponseEntity.status(404).body("Dati mancanti");//errore inserimento dati
        }
    }
}
