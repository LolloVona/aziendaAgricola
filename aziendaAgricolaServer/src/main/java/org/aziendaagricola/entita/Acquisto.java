package org.aziendaagricola.entita;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "acquisto")
public class Acquisto {
        @Id
        private int numeroFattura;

        @Column(nullable = false)
        private LocalDate data;

        @Column(nullable = false)
        private float totale;

        @ManyToOne
        @JoinColumn(name = "id_utente")
        private Utente utente;

        @Column(nullable = false)
        private LocalDate dataErogazione;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
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

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public LocalDate getDataErogazione() {
        return dataErogazione;
    }

    public void setDataErogazione(LocalDate dataErogazione) {
        this.dataErogazione = dataErogazione;
    }
}
