package org.aziendaagricola.controller;

import org.aziendaagricola.DTO.*;
import org.aziendaagricola.entita.Acquisto;
import org.aziendaagricola.record.Acquisto.AcquistoResponse;
import org.aziendaagricola.record.Acquisto.OrdineDaErogareRecord;
import org.aziendaagricola.record.Errore;
import org.aziendaagricola.service.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/acquisto")
public class AcquistoController {
    @Autowired
    private AcquistoService acquistoService;

    @PostMapping()
    public ResponseEntity<Object> acquisto(@RequestBody AcquistoCreateDTO dto) {
        if(dto.getIdUtente()==null){
            Errore body=new Errore("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        if(!acquistoService.isValido(dto)){//quantità>disponibilità
            Errore body=new Errore("Quantità prodotti eccessiva");
            return ResponseEntity.status(400).body(body);
        }
        InformazioniFattura i=acquistoService.aggiungiAcquisto(dto);
        AcquistoResponse body=new AcquistoResponse(i.getPrezzo(),i.getDataErogazione());
        return ResponseEntity.status(200).body(body);
    }
    @PostMapping("/conferma")
    public ResponseEntity<Object> confermaAcquisto(@RequestBody AcquistoCreateDTO dto) {
        if(dto.getIdUtente()==null){
            Errore body=new Errore("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        if(!acquistoService.isValido(dto)){//controllo nuovamente (concorrenza)
            Errore body=new Errore("Quantità prodotti eccessiva");
            return ResponseEntity.status(400).body(body);
        }
        if(acquistoService.confermaAcquisto(dto)){
            Errore body=new Errore("Acquisto confermato");
            return ResponseEntity.status(200).body(body);

        }
        Errore body=new Errore("Dati non validi");
        return ResponseEntity.status(400).body(body);

    }
    @GetMapping()
    public ResponseEntity<Object> getOrdiniDaErogare(@RequestBody AcquistoGetDTO dto) {
        if(!acquistoService.isAdmin(dto.getIdUtente())) {
            Errore body=new Errore("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        ArrayList<AcquistoReadDTO> ordini=acquistoService.getOrdiniDaErogare();
        OrdineDaErogareRecord ordine=new OrdineDaErogareRecord(ordini);
        return ResponseEntity.status(200).body(ordine);
    }
}
