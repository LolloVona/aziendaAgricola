package org.aziendaagricola.log;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogAcquisto {
    private int numeroFattura,idUtente;
    private String username;
    public LogAcquisto(int numeroFattura, int idUtente, String username) {
        this.numeroFattura = numeroFattura;
        this.idUtente = idUtente;
        this.username = username;
    }

    public void scrivi() {
        String ora= "["+ LocalDateTime.now()+"] ";
        String msg=ora+username+" (id:"+idUtente+")";
        msg=msg+" ha effettuato un acquisto (numero fattura:"+numeroFattura+")";
        try {
            FileWriter f = new FileWriter("log/acquisto.txt", true);
            PrintWriter fout = new PrintWriter(f);
            fout.println(msg);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
