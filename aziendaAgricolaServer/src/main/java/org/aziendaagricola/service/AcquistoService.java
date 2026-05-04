package org.aziendaagricola.service;

import org.aziendaagricola.repository.AcquistoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcquistoService {
    @Autowired
    private AcquistoRepository repository;
}
