package org.aziendaagricola.controller;
import org.aziendaagricola.entita.Aggiornamento;
import org.aziendaagricola.repository.AggiornamentoRepository;
import org.aziendaagricola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/prodotto")
public class ProdottoController {
    @Autowired
    private ProdottoRepository prodottoRepo;
    //private AggiornamentoController aggiornamentoController=new AggiornamentoController();

    @DeleteMapping("/{id}")//http://localhost:8080/api/prodotto/1
    public String deleteProdottoById(@PathVariable("id") int idProdotto, @RequestBody Map<String, Object> bodyRequest) {
        int idUtente=(int) bodyRequest.get("idUtente");
        if (prodottoRepo.existsById(idProdotto)) {
            prodottoRepo.deleteById(idProdotto);

            //aggiornamentoController.inserisciModifica(idProdotto,"D",idUtente);
            return "{'message':'ok, prodotto eliminato','messageCode':'204'}";
        } else {
            return "{'message':'errore, prodotto non trovato','messageCode':'404'}";
        }

    }
}
