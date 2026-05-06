package org.aziendaagricola.service;

import org.aziendaagricola.DTO.RaccoltoCreateDTO;
import org.aziendaagricola.entita.Prodotto;
import org.aziendaagricola.entita.ProssimoRaccolto;
import org.aziendaagricola.repository.ProdottoRepository;
import org.aziendaagricola.repository.ProssimoRaccoltoRepository;
import org.aziendaagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProssimoRaccoltoService {

    @Autowired
    private ProssimoRaccoltoRepository repository;
    @Autowired
    private UtenteRepository utenteRepository;
    @Autowired
    private ProdottoRepository prodottoRepository;
    public boolean isAdmin(int idUtente) {
        boolean admin=utenteRepository.findById(idUtente)//restituisce un oggetto Optional di tipo Utente
                .map(u -> u.getTipo().equalsIgnoreCase("A"))//chiamo l'istanza dell'oggetto restituito u,
                .orElse(false);//se non è A returna false
        if(!admin)
            return false;
        return true;
    }
    public boolean esisteProdotto(String nome) {
        return repository.existsByProdottoNome(nome);
    }

    public boolean aggiungiRaccolto(RaccoltoCreateDTO dto) {
        /*/if(esisteProdotto(dto.getNome())){
            int id=repository.getIdByNome(dto.getNome());
            ProssimoRaccolto nuovo = new ProssimoRaccolto();
            Prodotto p=new Prodotto();
            p.setIdProdotto(id);
            nuovo.setProdotto(p);
            nuovo.setTotale(dto.getTotale());
            nuovo.setDisponibilita(dto.getTotale());
            nuovo.setData(dto.getData());
            repository.save(nuovo);
            return true;
        }
        return false;

    }*/
        // Chiedi al repository dei prodotti se il prodotto esiste
        if (prodottoRepository.existsByNome(dto.getNome())) {
            // Recupera l'ID direttamente dal repository prodotti
            Prodotto p = prodottoRepository.findByNome(dto.getNome());

            ProssimoRaccolto nuovo = new ProssimoRaccolto();
            nuovo.setProdotto(p);
            nuovo.setTotale(dto.getTotale());
            nuovo.setDisponibilita(dto.getTotale());
            nuovo.setData(dto.getData());

            repository.save(nuovo);
            return true;
        }
        return false;
    }
}
