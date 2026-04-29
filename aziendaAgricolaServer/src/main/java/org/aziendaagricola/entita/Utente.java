package org.aziendaagricola.entita;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name="utente")
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtente;
   // @Column()//nullable->può essere null udpatable->può essere modificato name->se uso nome diverso rispetto a db

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 1)
    private String tipo; // Vincolo: 'C' o 'A'

    @OneToMany(mappedBy = "utente")
    private ArrayList<Acquisto> acquisti;

    public ArrayList<Acquisto> getAcquisti() {
        return acquisti;
    }

    public void setAcquisti(ArrayList<Acquisto> acquisti) {
        this.acquisti = acquisti;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
