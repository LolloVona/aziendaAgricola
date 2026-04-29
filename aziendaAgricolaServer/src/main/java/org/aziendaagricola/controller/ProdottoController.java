package org.aziendaagricola.controller;

import org.aziendaagricola.entita.Aggiornamento;
import org.aziendaagricola.entita.Prodotto;
import org.aziendaagricola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoRepository prodottoRepo;
    //private ;

    @DeleteMapping("/{id}")//http://localhost:8080/api/prodotto/1
    public String deleteProdottoById(@PathVariable("id") int id) {
        if (prodottoRepo.existsById(id)) {
            prodottoRepo.deleteById(id);
            //aggiornamento.inserisciAggiornamento(id,"D");
            return "{'message':'ok, prodotto eliminato','messageCode':'204'}";
        } else {
            return "{'message':'errore, prodotto non trovato','messageCode':'404'}";
        }

    }
}
