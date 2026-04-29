package org.aziendaagricola.entita;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
@Embeddable
public class IdRelativo implements Serializable {
    private int numero_fattura;
    private int id_prodotto;

    public int getId_prodotto() {
        return id_prodotto;
    }

    public void setId_prodotto(int id_prodotto) {
        this.id_prodotto = id_prodotto;
    }

    public int getNumero_fattura() {
        return numero_fattura;
    }

    public void setNumero_fattura(int numero_fattura) {
        this.numero_fattura = numero_fattura;
    }
}
