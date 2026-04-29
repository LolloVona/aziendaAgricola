package org.aziendaagricola.DTO;

public class ProdottoCreateDTO {
    private String nomeProdotto;
    private Float prezzo;
    private Float quantita;
    private Float disponibilita;

    // Metodo di validazione interna (non tocca il DB)
    public boolean isValido() {
        if (prezzo == null || quantita == null || disponibilita == null) return false;
        return prezzo >= 0 && quantita >= 0 && disponibilita >= 0 && disponibilita <= quantita;
    }

    public Float getDisponibilita() {
        return disponibilita;
    }

    public void setDisponibilita(Float disponibilita) {
        this.disponibilita = disponibilita;
    }

    public String getNomeProdotto() {
        return nomeProdotto;
    }

    public void setNomeProdotto(String nomeProdotto) {
        this.nomeProdotto = nomeProdotto;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Float getQuantita() {
        return quantita;
    }

    public void setQuantita(Float quantita) {
        this.quantita = quantita;
    }
}

