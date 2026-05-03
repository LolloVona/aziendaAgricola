package org.aziendaagricola.controller;

import org.aziendaagricola.DTO.ProdottoCreateDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/raccolto")
public class ProssimoRaccoltoController {
    @PostMapping()
    public ResponseEntity<Object> aggiungiRaccolto(@RequestBody ProdottoCreateDTO dto){

    }
}
