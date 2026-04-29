package org.aziendaagricola.entita;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "prossimoraccolto")
public class ProssimoRaccolto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_raccolto;

    @Column(nullable = false)
    private float disponibilita;

    @Column(nullable = false)
    private float totale;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public float getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(float disponibilita) {
        this.disponibilita = disponibilita;
    }

    public int getId_raccolto() {
        return id_raccolto;
    }

    public void setId_raccolto(int id_raccolto) {
        this.id_raccolto = id_raccolto;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public float getTotale() {
        return totale;
    }

    public void setTotale(float totale) {
        this.totale = totale;
    }
}
