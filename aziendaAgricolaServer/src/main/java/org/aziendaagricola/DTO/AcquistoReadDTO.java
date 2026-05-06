package org.aziendaagricola.DTO;

import java.time.LocalDate;

public class AcquistoReadDTO {
    private int numeroFattura;
    private float totale;
    private LocalDate dataErogazione;
    private String usernameCliente;

    public LocalDate getDataErogazione() {
        return dataErogazione;
    }

    public void setDataErogazione(LocalDate dataErogazione) {
        this.dataErogazione = dataErogazione;
    }

    public String getUsernameCliente() {
        return usernameCliente;
    }

    public void setUsernameCliente(String usernameCliente) {
        usernameCliente = usernameCliente;
    }

    public int getNumeroFattura() {
        return numeroFattura;
    }

    public void setNumeroFattura(int numeroFattura) {
        this.numeroFattura = numeroFattura;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
