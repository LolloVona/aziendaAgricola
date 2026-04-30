package org.aziendaagricola.service;

import org.aziendaagricola.DTO.UtenteCreateDTO;
import org.aziendaagricola.entita.Utente;
import org.aziendaagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UtenteService {
    @Autowired
    private UtenteRepository utenteRepository;

    public boolean salva(UtenteCreateDTO dto) {
        if(utenteRepository.existByUsername(dto.getUsername()))
            return false;
        Utente user=new Utente();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setNome(dto.getNome());
        user.setTipo("C");
        utenteRepository.save(user);
        return true;



    }
}
