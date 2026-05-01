package org.aziendaagricola.controller;
import org.aziendaagricola.DTO.ProdottoCreateDTO;
import org.aziendaagricola.DTO.ProdottoDeleteDTO;
import org.aziendaagricola.record.Errore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.aziendaagricola.service.ProdottoService;

import java.util.Map;

@RestController
@RequestMapping("/api/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;

    @PostMapping()
    public ResponseEntity<Object> aggiungi(@RequestBody ProdottoCreateDTO dto) {
        if (dto.getIdUtente() == null) {
            Errore body=new Errore("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        boolean successo = prodottoService.salvaProdotto(dto);

        if (successo) {
            //TODO: file di log
            //Log log=new Log();
            Errore body=new Errore("Prodotto creato");

            return ResponseEntity.status(201).body(body);
        } else {
            Errore body=new Errore("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> elimina(@PathVariable("id") int id, @RequestBody ProdottoDeleteDTO dto){
        return null;
    }
}


