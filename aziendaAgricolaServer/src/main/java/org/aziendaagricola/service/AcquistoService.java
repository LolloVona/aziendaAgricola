package org.aziendaagricola.service;

import jakarta.transaction.Transactional;
import org.aziendaagricola.DTO.*;
import org.aziendaagricola.DTO.Informazioni;

import org.aziendaagricola.entita.*;
import org.aziendaagricola.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcquistoService {
    @Autowired
    private AcquistoRepository repository;
    @Autowired
    private ProdottoRepository prodottoRepository;
    @Autowired
    private ProssimoRaccoltoRepository raccoltoRepository;
    @Autowired
    private RelativoRePository relativoRepository;
    @Autowired
    private UtenteRepository utenteRepository;
    public boolean isValido(AcquistoCreateDTO dto) {
        ArrayList<Informazioni> prodotti= new ArrayList<>();
        float somma,richiesta;
        int id;
        prodotti=dto.getProdotti();
        for(int i=0;i<prodotti.size();i++){
            somma=0;
            richiesta=prodotti.get(i).getQuantita();
            String nome=prodotti.get(i).getNome();
            somma=somma+prodottoRepository.getDisponibilitaByNome(nome);
            if(somma<richiesta){
                id=prodottoRepository.getIdByNome(nome);
                somma=somma+raccoltoRepository.getSommaDisponibilitaById(id);
                if(somma<richiesta)
                    return false;
            }
        }
        return true;
    }

    public InformazioniFattura aggiungiAcquisto(AcquistoCreateDTO dto) {
        LocalDate dataProntoOrdine = LocalDate.now();
        float totalePrezzoOrdine=0;
        for (int i = 0; i < dto.getProdotti().size(); i++) {
            Informazioni info = dto.getProdotti().get(i);
            float daScalare = info.getQuantita();
            String nome = info.getNome();
            float richiesta = info.getQuantita();
            Prodotto p = prodottoRepository.findByNome(nome);
            float dispMagazzino = p.getDisponibilita();
            totalePrezzoOrdine=totalePrezzoOrdine+ (p.getPrezzo() * richiesta);
            if (dispMagazzino > 0) {
                float sottratto = Math.min(dispMagazzino, daScalare);
                p.setDisponibilita(dispMagazzino - sottratto);
                daScalare=daScalare-sottratto;
                p.setMagazzino(p.getMagazzino() - sottratto);
            }
            if (daScalare > 0) {
                List<ProssimoRaccolto> raccolti = raccoltoRepository.findByProdottoIdProdottoOrderByDataAsc(p.getIdProdotto());

                for (int j = 0; j < raccolti.size(); j++) {
                    if (daScalare <= 0)
                        break;
                    ProssimoRaccolto r = raccolti.get(j);
                    float dispRaccolto = r.getDisponibilita();
                    if (dispRaccolto > 0) {
                        float sottratto = Math.min(dispRaccolto, daScalare);
                        r.setDisponibilita(dispRaccolto - sottratto);
                        daScalare -= sottratto;
                        if (r.getData().isAfter(dataProntoOrdine)) {
                            dataProntoOrdine = r.getData();
                        }
                    }
                }
            }
        }
        Acquisto acquisto = new Acquisto();
        acquisto.setData(LocalDate.now());
        acquisto.setTotale(totalePrezzoOrdine);
        Utente u=new Utente();
        u.setIdUtente(dto.getIdUtente());
        acquisto.setUtente(u);
        acquisto.setDataErogazione(dataProntoOrdine);
        ArrayList<Informazioni> prodotti=new ArrayList<>();
        for(int i=0;i<prodotti.size();i++){
            Relativo relativo = new Relativo();
            relativo.setAcquisto(acquisto);
            String nome = prodotti.get(i).getNome();
            float richiesta = prodotti.get(i).getQuantita();
            Prodotto p = prodottoRepository.findByNome(nome);
            relativo.setProdotto(p);
            relativo.setQuantita(richiesta);
        }
        InformazioniFattura fattura = new InformazioniFattura();
        fattura.setDataErogazione(dataProntoOrdine);
        fattura.setPrezzo(totalePrezzoOrdine);
        return fattura;
    }

    @Transactional
    public boolean confermaAcquisto(AcquistoCreateDTO dto){
        LocalDate dataProntoOrdine = LocalDate.now();
        float totalePrezzoOrdine=0;
        for (int i = 0; i < dto.getProdotti().size(); i++) {
            Informazioni info = dto.getProdotti().get(i);
            float daScalare = info.getQuantita();
            String nome = info.getNome();
            float richiesta = info.getQuantita();
            Prodotto p = prodottoRepository.findByNome(nome);
            float dispMagazzino = p.getDisponibilita();
            totalePrezzoOrdine=totalePrezzoOrdine+ (p.getPrezzo() * richiesta);
            if (dispMagazzino > 0) {
                float sottratto = Math.min(dispMagazzino, daScalare);
                p.setDisponibilita(dispMagazzino - sottratto);
                daScalare=daScalare-sottratto;
                p.setMagazzino(p.getMagazzino() - sottratto);
                prodottoRepository.save(p);
            }
            if (daScalare > 0) {
                List<ProssimoRaccolto> raccolti = raccoltoRepository.findByProdottoIdProdottoOrderByDataAsc(p.getIdProdotto());

                for (int j = 0; j < raccolti.size(); j++) {
                    if (daScalare <= 0)
                        break;
                    ProssimoRaccolto r = raccolti.get(j);
                    float dispRaccolto = r.getDisponibilita();
                    if (dispRaccolto > 0) {
                        float sottratto = Math.min(dispRaccolto, daScalare);
                        r.setDisponibilita(dispRaccolto - sottratto);
                        daScalare -= sottratto;
                        if (r.getData().isAfter(dataProntoOrdine)) {
                            dataProntoOrdine = r.getData();
                        }
                        raccoltoRepository.save(r);
                    }
                }
            }
        }
        Acquisto acquisto = new Acquisto();
        acquisto.setData(LocalDate.now());
        acquisto.setTotale(totalePrezzoOrdine);
        Utente u=new Utente();
        u.setIdUtente(dto.getIdUtente());
        acquisto.setUtente(u);
        acquisto.setDataErogazione(dataProntoOrdine);
        repository.save(acquisto);
        ArrayList<Informazioni> prodotti=new ArrayList<>();
        for(int i=0;i<prodotti.size();i++){
            Relativo relativo = new Relativo();
            relativo.setAcquisto(acquisto);
            String nome = prodotti.get(i).getNome();
            float richiesta = prodotti.get(i).getQuantita();
            Prodotto p = prodottoRepository.findByNome(nome);
            relativo.setProdotto(p);
            relativo.setQuantita(richiesta);
            relativoRepository.save(relativo);
        }

        return true;
    }

    public ArrayList<AcquistoReadDTO> getOrdiniDaErogare() {
        ArrayList<Acquisto> a= repository.findByDataErogazioneAfter(LocalDate.now());
        ArrayList<AcquistoReadDTO> ordini=new ArrayList<>();
        for(int i=0;i<a.size();i++){
            AcquistoReadDTO dto = new AcquistoReadDTO();
            dto.setDataErogazione(a.get(i).getDataErogazione());
            dto.setUsernameCliente(a.get(i).getUtente().getUsername());
            dto.setNumeroFattura(a.get(i).getNumeroFattura());
            dto.setTotale(a.get(i).getTotale());
            ordini.add(dto);
        }
        return ordini;
    }
    public boolean isAdmin(int idUtente){
        boolean admin=utenteRepository.findById(idUtente)//restituisce un oggetto Optional di tipo Utente
                .map(u -> u.getTipo().equalsIgnoreCase("A"))//chiamo l'istanza dell'oggetto restituito u,
                .orElse(false);//se non è A returna false
        if(!admin)
            return false;
        return true;
    }
}
