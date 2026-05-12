package org.aziendaagricola.log;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogUtente {
    private int id;
    private String tipo, username;
    public LogUtente(int id, String tipo, String username) {
        this.id = id;
        this.tipo = tipo;
        this.username = username;
    }
    public void scrivi(){
        String ora= "["+ LocalDateTime.now()+"] ";
        String msg=ora+username+" (id:"+id+")";
        switch(tipo){
            case "registrazione":
                 msg=msg+" ha effettuato la registrazione";
                 break;
            case "accesso":
                msg=msg+" ha effettuato l'accesso";
        }
        try {
            FileWriter f = new FileWriter("log/utente.txt", true);
            PrintWriter fout = new PrintWriter(f);
            fout.println(msg);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
