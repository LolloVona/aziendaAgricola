package org.aziendaagricola.DTO;

public class UtenteCreateDTO {
    private String nome,username,password;

    public boolean convalidaDati(){
        if(nome==null || username==null || password==null)
            return false;
        if(nome.isEmpty() || username.isEmpty() || password.isEmpty())
            return false;
        return true;
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


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
