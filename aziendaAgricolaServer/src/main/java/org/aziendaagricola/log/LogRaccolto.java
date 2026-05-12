package org.aziendaagricola.log;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class LogRaccolto {
    private int idRaccolto, idProdotto, idUtente;
    private String nomeProdotto, username,data;

    public LogRaccolto(int idProdotto, int idRaccolto, int idUtente,String nomeProdotto, String username, String data) {
        this.idProdotto = idProdotto;
        this.idRaccolto = idRaccolto;
        this.idUtente = idUtente;
        this.nomeProdotto = nomeProdotto;
        this.username = username;
        this.data = data;
    }
    public void scrivi(){
        String ora="["+ LocalDateTime.now()+"] ";
        String msg=ora+"{idRaccolto:"+idRaccolto+"} "+username+" (id:"+idUtente+") ha aggiunto un nuovo raccolto di "+nomeProdotto+"(id:"+idProdotto+") Data prevista: "+data;
        try {
            FileWriter f = new FileWriter("log/raccolto.txt", true);
            PrintWriter fout = new PrintWriter(f);
            fout.println(msg);
            fout.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
