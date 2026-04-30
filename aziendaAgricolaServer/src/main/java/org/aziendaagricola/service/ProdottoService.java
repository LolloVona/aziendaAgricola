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
        // 1. Chiedo al DTO se i numeri sono coerenti

        // 2. Controllo sul database se il nome esiste già
        if (repository.existsByNome(dto.getNome()))
            return false;

        /*
        // 1. Cerco l'utente nel database (ritorna una "scatola" Optional)
        Optional<Utente> utenteOpt = utenteRepository.findById(dto.getIdUtente());
        boolean isAdmin = false;
        // 2. Controllo SE la scatola contiene un utente
        if (utenteOpt.isPresent()) {
            // 3. SE esiste, estraggo l'oggetto Utente reale
            Utente u = utenteOpt.get();
            // 4. Controllo se il tipo è "A"
            if (u.getTipo().equalsIgnoreCase("A")) {
                isAdmin = true;
            }
        }
        // 5. Se non è admin (o l'utente non esisteva), esco subito
        if (!isAdmin) {
            return false;
        }
        */
        boolean isAdmin= utenteRepository.findById(dto.getIdUtente())//restituisce un oggetto Optional di tipo Utente
                .map(u -> u.getTipo().equalsIgnoreCase("A"))//chiamo l'istanza dell'oggetto restituito u,
                .orElse(false);//se non è A returna false

        if (!isAdmin) return false;

        // 3. Se tutto ok, trasformo DTO in Entità e salvo
        Prodotto nuovo = new Prodotto();
        nuovo.setNome(dto.getNome());
        nuovo.setPrezzo(dto.getPrezzo());
        nuovo.setMagazzino(dto.getMagazzino());
        nuovo.setDisponibilita(dto.getDisponibilita());

        repository.save(nuovo);
        return true;
    }
}