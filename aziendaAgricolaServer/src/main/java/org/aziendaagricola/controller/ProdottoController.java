package org.aziendaagricola.controller;
import jakarta.transaction.Transactional;
import org.aziendaagricola.DTO.ProdottoCreateDTO;
import org.aziendaagricola.DTO.ProdottoDeleteDTO;
import org.aziendaagricola.DTO.ProdottoReadDTO;
import org.aziendaagricola.record.Errore;
import org.aziendaagricola.record.Prodotto.GetProdottiRecord;
import org.aziendaagricola.record.Prodotto.GetProdottoRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.aziendaagricola.service.ProdottoService;

import java.util.ArrayList;

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
            //TODO: file di log e aggiunta a tabella aggiornamento
            //Log log=new Log();
            Errore body=new Errore("Prodotto creato");

            return ResponseEntity.status(201).body(body);
        } else {
            Errore body=new Errore("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
    }

    @DeleteMapping("/{nomeProdotto}")
    @Transactional
    public ResponseEntity<Object> elimina(@PathVariable("nomeProdotto") String nome, @RequestBody ProdottoDeleteDTO dto){
        if(dto.getIdUtente()==null){
            Errore body=new Errore("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if(!prodottoService.isAdmin(dto.getIdUtente())){
            Errore body=new Errore("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        if(prodottoService.eliminaProdotto(nome)){
            //TODO:file di log e aggiunta a tabella aggiornamento
            Errore body=new Errore("Prodotto eliminato");
            return ResponseEntity.status(204).body(body);
        }
        else{
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getProdotti() {
        ArrayList <ProdottoReadDTO> prodotto=prodottoService.getProdotti();
        GetProdottiRecord body=new GetProdottiRecord(prodotto);
        return ResponseEntity.status(200).body(body);
    }

    @GetMapping("/{nomeProdotto}")
    public ResponseEntity<Object> getProdottoByNome(@PathVariable("nomeProdotto") String nome) {
        if(prodottoService.esisteProdotto(nome)){
            ProdottoReadDTO prodotto= prodottoService.getProdottoByNome(nome);
            GetProdottoRecord body=new GetProdottoRecord(prodotto);
            return ResponseEntity.status(200).body(body);
        }
        else{
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
    }

    @PutMapping
    public ResponseEntity<Object> putProdotto(@RequestBody ProdottoUpdateDTO dto) {}
}


