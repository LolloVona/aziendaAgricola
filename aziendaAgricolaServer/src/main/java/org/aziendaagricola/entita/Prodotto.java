package org.aziendaagricola.entita;

import jakarta.persistence.*;

@Entity
@Table(name="prodotto")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProdotto;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false)
    private float prezzo;

    @Column(nullable = false)
    private float magazzino;

    @Column(nullable = false)
    private float disponibilita;

    public float getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(float disponibilita) {
        this.disponibilita = disponibilita;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int id_prodotto) {
        this.idProdotto = id_prodotto;
    }

    public float getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(float magazzino) {
        this.magazzino = magazzino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }
}
