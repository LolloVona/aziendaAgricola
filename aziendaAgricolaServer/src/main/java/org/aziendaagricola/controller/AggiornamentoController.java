package org.aziendaagricola.controller;

import org.aziendaagricola.entita.Aggiornamento;
import org.aziendaagricola.repository.AggiornamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggiornamentoController {
    @Autowired
    private AggiornamentoRepository aggiornamentoRepo;
}
