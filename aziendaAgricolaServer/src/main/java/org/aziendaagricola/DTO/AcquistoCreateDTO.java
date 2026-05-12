package org.aziendaagricola.DTO;

import java.util.ArrayList;

public class AcquistoCreateDTO {
    private Integer idUtente;
    private ArrayList<InformazioniDTO> prodotti;

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public ArrayList<InformazioniDTO> getProdotti() {
        return prodotti;
    }

    public void setProdotti(ArrayList<InformazioniDTO> prodotti) {
        this.prodotti = prodotti;
    }

    public boolean isValido() {
        if(idUtente == null || prodotti == null || prodotti.isEmpty())
            return false;
        return true;
    }
}
