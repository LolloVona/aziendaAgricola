package org.aziendaagricola.DTO;

import java.time.LocalDate;

public class RaccoltoCreateDTO {
    private Integer totale;
    private LocalDate data;
    private String nome;
    private Integer idUtente;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNomeProdotto(String nome) {
        this.nome = nome;
    }

    public Integer getTotale() {
        return totale;
    }

    public void setTotale(Integer totale) {
        this.totale = totale;
    }

    public boolean isValido() {
        if(data==null||nome==null||totale==null||idUtente==null||nome.isEmpty())
            return false;
        if(data.isBefore(LocalDate.now())||data.isEqual(LocalDate.now())||totale<=0)
            return false;
        return true;
    }
}
