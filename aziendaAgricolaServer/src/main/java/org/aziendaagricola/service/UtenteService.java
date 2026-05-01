package org.aziendaagricola.service;

import org.aziendaagricola.DTO.UtenteAccediDTO;
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
        if(utenteRepository.existsByUsername(dto.getUsername()))
            return false;
        Utente user=new Utente();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setNome(dto.getNome());
        user.setTipo("C");
        utenteRepository.save(user);
        return true;
    }
    public int getId(UtenteCreateDTO dto) {
        Utente utente=utenteRepository.getUtenteByUsername(dto.getUsername());
        return utente.getIdUtente();
    }
    public int getId(UtenteAccediDTO dto) {
        Utente utente=utenteRepository.getUtenteByUsername(dto.getUsername());
        return utente.getIdUtente();
    }

    public int credenzialiCorrette(UtenteAccediDTO dto){
        if(utenteRepository.existsByUsername(dto.getUsername())){
            Utente u=utenteRepository.getUtenteByUsername(dto.getUsername());
            String pw=u.getPassword();
            if(pw.equals(dto.getPassword())) {
                return getId(dto);
            }
        }
        return -1;

    }
}
