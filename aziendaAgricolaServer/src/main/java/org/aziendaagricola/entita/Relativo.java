package org.aziendaagricola.entita;

import jakarta.persistence.*;

@Entity
@Table(name = "relativo")
public class Relativo {
    @EmbeddedId
    private IdRelativo id;

    @ManyToOne
    @MapsId("numero_fattura")
    @JoinColumn(name = "numero_fattura")
    private Acquisto acquisto;

    @ManyToOne
    @MapsId("id_prodotto")
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;

    private float quantita;

    public Acquisto getAcquisto() {
        return acquisto;
    }

    public void setAcquisto(Acquisto acquisto) {
        this.acquisto = acquisto;
    }

    public IdRelativo getId() {
        return id;
    }

    public void setId(IdRelativo id) {
        this.id = id;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public float getQuantita() {
        return quantita;
    }

    public void setQuantita(float quantita) {
        this.quantita = quantita;
    }
}
