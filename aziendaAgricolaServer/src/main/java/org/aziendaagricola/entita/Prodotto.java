package org.aziendaagricola.entita;

import jakarta.persistence.*;

@Entity
@Table(name="prodotto")
public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_prodotto;

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

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
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
