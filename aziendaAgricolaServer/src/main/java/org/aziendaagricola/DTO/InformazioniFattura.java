package org.aziendaagricola.DTO;

import java.time.LocalDate;

public class InformazioniFattura {
    private int numeroFattura;
    private LocalDate dataErogazione;
    private float prezzo;

    public LocalDate getDataErogazione() {
        return dataErogazione;
    }

    public void setDataErogazione(LocalDate dataErogazione) {
        this.dataErogazione = dataErogazione;
    }

    public int getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(int numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
}
