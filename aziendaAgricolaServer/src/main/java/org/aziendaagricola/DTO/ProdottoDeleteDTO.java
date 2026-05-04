package org.aziendaagricola.DTO;

public class ProdottoDeleteDTO {
    private Integer idUtente;

    public Integer getIdUtente() {
        return idUtente;
    }
    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }

    public boolean isValido() {
        if (idUtente == null) {
            return false;
        }
        return true;
    }
}
