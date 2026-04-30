package org.aziendaagricola.controller;
import org.aziendaagricola.DTO.ProdottoCreateDTO;
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
    //private AggiornamentoController aggiornamentoController=new AggiornamentoController();
/*
    @DeleteMapping("/{id}")//http://localhost:8080/api/prodotto/1
    /*
    body
    idUtente:int idDell'utente che sta effettuando la modifica

    public String eliminaProdottoById(@PathVariable("id") int idProdotto, @RequestBody Map<String, Object> bodyRequest) {
        int idUtente=(int) bodyRequest.get("idUtente");
        if (prodottoRepo.existsById(idProdotto)) {
            prodottoRepo.deleteById(idProdotto);
            //controllare che l'utente sia di tipo A
            //aggiornamentoController.inserisciModifica(idProdotto,"D",idUtente);
            return "{'message':'ok, prodotto eliminato','messageCode':'204'}";
        } else {
            return "{'message':'errore, prodotto non trovato','messageCode':'404'}";
        }
    }
*/
    @PostMapping()
    /*body:
    idUtente:int
    nome:String
    prezzo:float
    magazzino:float
    disponibilita:float
    * */
    public ResponseEntity<String> aggiungi(@RequestBody ProdottoCreateDTO dto) {
        //TODO sistemare codici
        // 1. Validazione base dell'input
        if (dto.getIdUtente() == null) {
            return ResponseEntity.status(400).body("ID utente mancante.");
        }
        if (!dto.isValido())
            return ResponseEntity.badRequest().body("Dati non validi");//errore dati nella body

        // 2. Chiamata al service
        boolean successo = prodottoService.salvaProdotto(dto);

        if (successo) {
            return ResponseEntity.status(201).body("Ottimo! Prodotto creato.");
        } else {
            return ResponseEntity.status(403).body("Operazione non consentita o dati non validi.");
        }
    }
}


