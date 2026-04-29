package org.aziendaagricola.service;
import org.aziendaagricola.DTO.ProdottoCreateDTO;
import org.aziendaagricola.entita.Prodotto;
import org.aziendaagricola.repository.ProdottoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdottoService {

    @Autowired
    private ProdottoRepository repository;

    public boolean salvaProdotto(ProdottoCreateDTO dto) {
        // 1. Chiedo al DTO se i numeri sono coerenti
        if (!dto.isValido())
            return false;

        // 2. Controllo sul database se il nome esiste già
        if (repository.existsByNomeProdotto(dto.getNomeProdotto()))
            return false;

        // 3. Se tutto ok, trasformo DTO in Entità e salvo
        Prodotto nuovo = new Prodotto();
        nuovo.setNome(dto.getNomeProdotto());
        nuovo.setPrezzo(dto.getPrezzo());
        nuovo.setMagazzino(dto.getQuantita());
        nuovo.setDisponibilita(dto.getDisponibilita());

        repository.save(nuovo);
        return true;
    }
}