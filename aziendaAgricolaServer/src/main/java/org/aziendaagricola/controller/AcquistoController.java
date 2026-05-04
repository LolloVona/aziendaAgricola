package org.aziendaagricola.controller;

import org.aziendaagricola.entita.Acquisto;
import org.aziendaagricola.service.AcquistoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/acquisto")
public class AcquistoController {
    @Autowired
    private AcquistoService acquistoService;

    @PostMapping()
    public ResponseEntity<String> acquisto(@RequestBody AcquistoCreateDTO dto) {

    }
}
