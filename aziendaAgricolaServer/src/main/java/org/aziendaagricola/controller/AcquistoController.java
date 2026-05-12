package org.aziendaagricola.controller;

import org.aziendaagricola.DTO.*;
import org.aziendaagricola.record.Acquisto.AcquistoResponse;
import org.aziendaagricola.record.Acquisto.OrdineDaErogareResponse;
import org.aziendaagricola.record.ErroreResponse;
import org.aziendaagricola.service.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/acquisto")
public class AcquistoController {
    @Autowired
    private AcquistoService acquistoService;

    @PostMapping()
    public ResponseEntity<Object> acquisto(@RequestBody AcquistoCreateDTO dto) {
        if(dto.getIdUtente()==null){
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        if(!acquistoService.isValido(dto)){//quantità>disponibilità
            ErroreResponse body=new ErroreResponse("Quantità prodotti eccessiva");
            return ResponseEntity.status(400).body(body);
        }
        InformazioniFatturaDTO i=acquistoService.aggiungiAcquisto(dto);
        AcquistoResponse body=new AcquistoResponse(i.getPrezzo(),i.getDataErogazione());
        return ResponseEntity.status(200).body(body);
    }
    @PostMapping("/conferma")
    public ResponseEntity<Object> confermaAcquisto(@RequestBody AcquistoCreateDTO dto) {
        if(dto.getIdUtente()==null){
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        if(!acquistoService.isValido(dto)){//controllo nuovamente (concorrenza)
            ErroreResponse body=new ErroreResponse("Quantità prodotti eccessiva");
            return ResponseEntity.status(400).body(body);
        }
        int numeroFattura=acquistoService.confermaAcquisto(dto);
        if(numeroFattura>0){
            acquistoService.scriviLog(numeroFattura, dto.getIdUtente());
            ErroreResponse body=new ErroreResponse("Acquisto confermato");
            return ResponseEntity.status(200).body(body);

        }
        ErroreResponse body=new ErroreResponse("Dati non validi");
        return ResponseEntity.status(400).body(body);

    }
    @GetMapping()
    public ResponseEntity<Object> getOrdiniDaErogare(@RequestBody AcquistoGetDTO dto) {
        if(!acquistoService.isAdmin(dto.getIdUtente())) {
            ErroreResponse body=new ErroreResponse("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        ArrayList<AcquistoReadDTO> ordini=acquistoService.getOrdiniDaErogare();
        OrdineDaErogareResponse ordine=new OrdineDaErogareResponse(ordini);
        return ResponseEntity.status(200).body(ordine);
    }
}
