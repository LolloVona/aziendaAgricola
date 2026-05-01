package org.aziendaagricola.DTO;

public class ProdottoCreateDTO {
    private String nome;
    private Float prezzo;
    private Float magazzino;
    private Integer idUtente;

    // Metodo di validazione interna (non tocca il DB)
    public boolean isValido() {
        if(magazzino==null)
            magazzino=(float)0;
        if(prezzo >= 0 && magazzino >= 0 &&prezzo!=null&&nome!=null&&nome.isBlank()==false)
            return true;
        return false;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nomeProdotto) {
        this.nome = nomeProdotto;
    }

    public Float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Float prezzo) {
        this.prezzo = prezzo;
    }

    public Float getMagazzino() {
        return magazzino;
    }

    public void setMagazzino(Float magazzino) {
        this.magazzino = magazzino;
    }

    public Integer getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(Integer idUtente) {
        this.idUtente = idUtente;
    }
}

