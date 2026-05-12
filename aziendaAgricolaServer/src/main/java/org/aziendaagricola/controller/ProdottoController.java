package org.aziendaagricola.controller;
import jakarta.transaction.Transactional;
import org.aziendaagricola.DTO.*;
import org.aziendaagricola.record.ErroreResponse;
import org.aziendaagricola.record.Prodotto.GetProdottiResponse;
import org.aziendaagricola.record.Prodotto.GetProdottoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.aziendaagricola.service.ProdottoService;

import java.util.ArrayList;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoService prodottoService;



    @PostMapping()
    public ResponseEntity<Object> aggiungi(@RequestBody ProdottoCreateDTO dto) {
        if (dto.getIdUtente() == null) {
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        boolean successo = prodottoService.salvaProdotto(dto);

        if (successo) {
            int idAggiornamento= prodottoService.aggiornamento(dto.getNome(), dto.getIdUtente(), "C");
            prodottoService.scriviLog(dto.getNome(),dto.getIdUtente(),"C",idAggiornamento);

            ErroreResponse body=new ErroreResponse("Prodotto creato");

            return ResponseEntity.status(201).body(body);
        } else {
            ErroreResponse body=new ErroreResponse("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
    }

    @DeleteMapping("/{nomeProdotto}")
    @Transactional
    public ResponseEntity<Object> elimina(@PathVariable("nomeProdotto") String nome, @RequestBody ProdottoDeleteDTO dto){
        if(dto.getIdUtente()==null){
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){//controllo se dto è valido?
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        if(!prodottoService.isAdmin(dto.getIdUtente())){
            ErroreResponse body=new ErroreResponse("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        int idAggiornamento= prodottoService.aggiornamento(nome, dto.getIdUtente(), "D");
        prodottoService.scriviLog(nome,dto.getIdUtente(),"D",idAggiornamento);
        if(prodottoService.eliminaProdotto(nome)){
            ErroreResponse body=new ErroreResponse("Prodotto eliminato");
            return ResponseEntity.status(204).body(body);
        }
        else{
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
    }

    @PutMapping("/nome/{nuovoNome}")
    public ResponseEntity<Object> cambiaNome(@RequestBody ProdottoUpdateNomeDTO dto, @PathVariable("nuovoNome") String nuovoNome) {
        if(dto.getIdUtente()==null){
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){//controllo se dto è valido?
            ErroreResponse body=new ErroreResponse("Dati non validi1");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }
        if(!prodottoService.isAdmin(dto.getIdUtente())){
            ErroreResponse body=new ErroreResponse("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        int idProdotto = prodottoService.getIdProdottoByNome(dto.getNome());
        if(prodottoService.modificaNomeProdotto(nuovoNome, dto.getNome())){
            int idAggiornamento= prodottoService.aggiornamento(dto.getNome(), dto.getIdUtente(), "U","nome",nuovoNome);
            prodottoService.scriviLog(dto.getNome(),dto.getIdUtente(),"U",idAggiornamento,nuovoNome,"nome",idProdotto);
            ErroreResponse body=new ErroreResponse("Prodotto modificato");
            return ResponseEntity.status(204).body(body);
        }
        else{
            ErroreResponse body=new ErroreResponse("Dati non validi2");
            return ResponseEntity.status(400).body(body);
        }
    }

    @PutMapping("/prezzo/{prezzo}")
    public ResponseEntity<Object> cambiaPrezzo(@RequestBody ProdottoUpdatePrezzoDTO dto, @PathVariable("prezzo") float nuovoPrezzo) {
        if(dto.getIdUtente()==null){
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            ErroreResponse body=new ErroreResponse("Dati non validi1");
            return ResponseEntity.status(400).body(body);
        }
        if(!prodottoService.isAdmin(dto.getIdUtente())){
            ErroreResponse body=new ErroreResponse("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        float vecchioPrezzo=prodottoService.vecchioPrezzo(dto.getNome());
        if(prodottoService.modificaPrezzoProdotto(nuovoPrezzo, dto.getNome())){
            int idAggiornamento= prodottoService.aggiornamento(dto.getNome(), dto.getIdUtente(), "U","prezzo",""+nuovoPrezzo,""+vecchioPrezzo);
            prodottoService.scriviLog(dto.getNome(), dto.getIdUtente(), "U",idAggiornamento, "prezzo",""+nuovoPrezzo,""+vecchioPrezzo);
            ErroreResponse body=new ErroreResponse("Prodotto modificato");
            return ResponseEntity.status(204).body(body);
        }
        else{
            ErroreResponse body=new ErroreResponse("Dati non validi2");
            return ResponseEntity.status(400).body(body);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getProdotti() {
        ArrayList <ProdottoReadDTO> prodotto=prodottoService.getProdotti();
        GetProdottiResponse body=new GetProdottiResponse(prodotto,"Prodotto trovato");
        return ResponseEntity.status(200).body(body);
    }

    @GetMapping("/{nomeProdotto}")
    public ResponseEntity<Object> getProdottoByNome(@PathVariable("nomeProdotto") String nome) {
        if(prodottoService.esisteProdotto(nome)){
            ProdottoReadDTO prodotto= prodottoService.getProdottoByNome(nome);
            GetProdottoResponse body=new GetProdottoResponse(prodotto);
            return ResponseEntity.status(200).body(body);
        }
        else{
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
    }


}


