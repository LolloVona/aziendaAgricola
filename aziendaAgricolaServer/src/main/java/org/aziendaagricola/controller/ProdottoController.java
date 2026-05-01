package org.aziendaagricola.controller;
import org.aziendaagricola.DTO.ProdottoCreateDTO;
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
    * */
    public ResponseEntity<Object> aggiungi(@RequestBody ProdottoCreateDTO dto) {
        // 1. Validazione base dell'input
        if (dto.getIdUtente() == null) {
            Errore body=new Errore("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if (!dto.isValido()){
            Errore body=new Errore("Dati non validi");
            return ResponseEntity.status(400).body(body);//errore dati nella body
        }


        // 2. Chiamata al service
        boolean successo = prodottoService.salvaProdotto(dto);

        if (successo) {
            Errore body=new Errore("Prodotto creato");
            return ResponseEntity.status(201).body("Prodotto creato");
        } else {
            return ResponseEntity.status(403).body("Non sei admin");
        }
    }
}


