package org.aziendaagricola.entita;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "acquisto")
public class Acquisto {
        @Id
        private int numero_fattura;

        @Column(nullable = false)
        private LocalDate data;

        @Column(nullable = false)
        private float totale;

        @ManyToOne
        @JoinColumn(name = "id_utente")
        private Utente utente;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getNumero_fattura() {
        return numero_fattura;
    }

    public void setNumero_fattura(int numero_fattura) {
        this.numero_fattura = numero_fattura;
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
}
