package org.aziendaagricola.DTO;

import java.time.LocalDate;

public class RaccoltoCreateDTO {
    private LocalDate data;
    private Float disponibilita;
    private Float totale;
    private String nome;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Float getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Float disponibilita) {
        this.disponibilita = disponibilita;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Float getTotale() {
        return totale;
    }

    public void setTotale(Float totale) {
        this.totale = totale;
    }
}
