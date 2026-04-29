package org.aziendaagricola.entita;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "aggiornamento")
public class Aggiornamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_aggiornamento;

    @Column(nullable = false, length = 1)
    private String tipo; // Vincolo: 'C', 'U', 'D'

    @Column(nullable = false)
    private LocalDate data;

    private String attributo_modificato;
    private String nuovo_valore;
    private String vecchio_valore;

    @ManyToOne
    @JoinColumn(name = "id_utente")
    private Utente utente;

    @ManyToOne
    @JoinColumn(name = "id_prodotto")
    private Prodotto prodotto;

    public String getAttributo_modificato() {
        return attributo_modificato;
    }

    public void setAttributo_modificato(String attributo_modificato) {
        this.attributo_modificato = attributo_modificato;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getId_aggiornamento() {
        return id_aggiornamento;
    }

    public void setId_aggiornamento(int id_aggiornamento) {
        this.id_aggiornamento = id_aggiornamento;
    }

    public String getNuovo_valore() {
        return nuovo_valore;
    }

    public void setNuovo_valore(String nuovo_valore) {
        this.nuovo_valore = nuovo_valore;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public String getVecchio_valore() {
        return vecchio_valore;
    }

    public void setVecchio_valore(String vecchio_valore) {
        this.vecchio_valore = vecchio_valore;
    }
}
