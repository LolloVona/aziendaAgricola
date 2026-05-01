package org.aziendaagricola.DTO;

public class UtenteAccediDTO {
    private String username, password;

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
    public boolean convalidaDati() {
        if(username==null || password==null)
            return false;
        return true;
    }
}
