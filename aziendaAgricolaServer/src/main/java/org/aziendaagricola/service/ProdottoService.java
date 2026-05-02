package org.aziendaagricola.service;
import org.aziendaagricola.DTO.ProdottoCreateDTO;
import org.aziendaagricola.entita.Prodotto;
import org.aziendaagricola.entita.Utente;
import org.aziendaagricola.repository.ProdottoRepository;
import org.aziendaagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository repository;
    @Autowired
    private UtenteRepository utenteRepository;

    public boolean salvaProdotto(ProdottoCreateDTO dto) {
        if (repository.existsByNome(dto.getNome()))
            return false;
        if (!isAdmin(dto.getIdUtente()))
            return false;
        Prodotto nuovo = new Prodotto();
        nuovo.setNome(dto.getNome());
        nuovo.setPrezzo(dto.getPrezzo());
        nuovo.setMagazzino(dto.getMagazzino());
        nuovo.setDisponibilita(dto.getMagazzino());
        repository.save(nuovo);
        return true;
    }
    public boolean isAdmin(int idUtente){
        boolean admin=utenteRepository.findById(idUtente)//restituisce un oggetto Optional di tipo Utente
                .map(u -> u.getTipo().equalsIgnoreCase("A"))//chiamo l'istanza dell'oggetto restituito u,
                .orElse(false);//se non è A returna false
        if(!admin)
            return false;
        return true;
    }

    public boolean eliminaProdotto(String nome) {
        if(!repository.existsByNome(nome)){
            return false;
        }
       repository.deleteByNome(nome);
        return true;
    }
}