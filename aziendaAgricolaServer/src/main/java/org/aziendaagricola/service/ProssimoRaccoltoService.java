package org.aziendaagricola.service;

import org.aziendaagricola.DTO.RaccoltoCreateDTO;
import org.aziendaagricola.LogRaccolto;
import org.aziendaagricola.entita.Prodotto;
import org.aziendaagricola.entita.ProssimoRaccolto;
import org.aziendaagricola.repository.ProdottoRepository;
import org.aziendaagricola.repository.ProssimoRaccoltoRepository;
import org.aziendaagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

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

    public int aggiungiRaccolto(RaccoltoCreateDTO dto) {

        if (prodottoRepository.existsByNome(dto.getNome())) {
            Prodotto p = prodottoRepository.findByNome(dto.getNome());

            ProssimoRaccolto nuovo = new ProssimoRaccolto();
            nuovo.setProdotto(p);
            nuovo.setTotale(dto.getTotale());
            nuovo.setDisponibilita(dto.getTotale());
            nuovo.setData(dto.getData());

            ProssimoRaccolto salvato = repository.save(nuovo);
            return salvato.getId_raccolto();
        }
        return-1;
    }

    public void controllaData() {
        LocalDate oggi=LocalDate.now();
        ArrayList<ProssimoRaccolto> raccolti= (ArrayList<ProssimoRaccolto>) repository.findByDataLessThanEqual(oggi);
        for(int i=0;i<raccolti.size();i++){
            ProssimoRaccolto r = raccolti.get(i);
            Prodotto p = r.getProdotto();
            p.setDisponibilita(p.getDisponibilita()+raccolti.get(i).getDisponibilita());
            p.setMagazzino(p.getMagazzino()+raccolti.get(i).getTotale());
            prodottoRepository.save(p);
            repository.deleteById(raccolti.get(i).getId_raccolto());
        }

    }

    public void scriviLog(int idRaccolto, Integer idUtente, String nomeProdotto) {
        String username=utenteRepository.getUsernameByIdUtente(idUtente).getUsername();
        String data=repository.findById_raccolto(idRaccolto).getData().toString();
        int idProdotto=prodottoRepository.getIdByNome(nomeProdotto).getIdProdotto();
        LogRaccolto log=new LogRaccolto(idProdotto,idRaccolto, idUtente,nomeProdotto,username,data);
        log.scrivi();
    }
}
