package org.aziendaagricola.service;
import org.aziendaagricola.DTO.ProdottoCreateDTO;
import org.aziendaagricola.DTO.ProdottoReadDTO;
import org.aziendaagricola.entita.Prodotto;
import org.aziendaagricola.repository.ProdottoRepository;
import org.aziendaagricola.repository.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


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

    public boolean esisteProdotto(String nome) {
        return repository.existsByNome(nome);
    }
    public ProdottoReadDTO getProdottoByNome(String nome) {
        Prodotto pro=repository.findByNome(nome);
        ProdottoReadDTO p=new ProdottoReadDTO();
        p.setNome(pro.getNome());
        p.setPrezzo(pro.getPrezzo());
        p.setDisponibilita(pro.getDisponibilita());
        return p;

    }

    public ArrayList<ProdottoReadDTO> getProdotti() {
        List<Prodotto>p=repository.findAll();
        ArrayList<ProdottoReadDTO> prodotti=new ArrayList<>();
        for(int i=0;i<p.size();i++){
            ProdottoReadDTO dto = new ProdottoReadDTO();
            dto.setNome(p.get(i).getNome());
            dto.setPrezzo(p.get(i).getPrezzo());
            dto.setDisponibilita(p.get(i).getDisponibilita());
            prodotti.add(dto);
        }
        return prodotti;
    }

    public boolean modificaNomeProdotto(String nuovoNome, String vecchioNome) {
        if(!esisteProdotto(vecchioNome))
            return false;
        Prodotto p=repository.findByNome(vecchioNome);
        System.out.println(nuovoNome);
        p.setNome(nuovoNome);
        repository.save(p);
        return true;
    }

    public boolean modificaPrezzoProdotto(float nuovoPrezzo, String nome) {
        if(!esisteProdotto(nome))
            return false;
        Prodotto p=repository.findByNome(nome);
        p.setPrezzo(nuovoPrezzo);
        repository.save(p);
        return true;
    }
}