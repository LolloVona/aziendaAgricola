package org.aziendaagricola.controller;

import org.aziendaagricola.DTO.AcquistoCreateDTO;
import org.aziendaagricola.DTO.InformazioniFattura;
import org.aziendaagricola.record.Acquisto.AcquistoResponse;
import org.aziendaagricola.record.Errore;
import org.aziendaagricola.service.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        AcquistoResponse body=new AcquistoResponse(i.getNumeroFattura(),i.getPrezzo(),i.getDataErogazione());
        return ResponseEntity.status(200).body(body);
    }
    //TODO:eliminazione acquisto e get ordini da erogare
}
