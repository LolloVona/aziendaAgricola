package org.aziendaagricola;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
public class LogAggiornamento {
    private String nomeFile,nomeUtente,tipo,nomeProdotto,attributo,vecchioValore,nuovoValore;
    private int idProdotto,idUtente, idAggiornamento;

    public LogAggiornamento(int idProdotto, int idUtente, String nomeProdotto, String nomeUtente, String tipo, int idAggiornamento) {
        //Create e Delete
        this.idProdotto = idProdotto;
        this.idUtente = idUtente;
        this.nomeFile = "aggiornamento.txt";
        this.nomeProdotto = nomeProdotto;
        this.nomeUtente = nomeUtente;
        this.tipo = tipo;
        this.idAggiornamento = idAggiornamento;
    }

    public LogAggiornamento(String attributo, int idProdotto, int idUtente, String nomeProdotto, String nomeUtente, String nuovoValore, String tipo, String vecchioValore, int idAggiornamento) {
        //Update
        this.attributo = attributo;
        this.idProdotto = idProdotto;
        this.idUtente = idUtente;
        this.nomeFile = "aggiornamento.txt";
        this.nomeProdotto = nomeProdotto;
        this.nomeUtente = nomeUtente;
        this.nuovoValore = nuovoValore;
        this.tipo = tipo;
        this.vecchioValore = vecchioValore;
        this.idAggiornamento = idAggiornamento;
    }

    public void scrivi() {
        try{
            FileWriter f=new FileWriter(nomeFile,true);
            PrintWriter fout=new PrintWriter(f);
            LocalDateTime ora=LocalDateTime.now();
            String aggiornamento="{id:"+idAggiornamento+"} ";
            String utente=nomeUtente+"(id:"+idUtente+") ";
            String prodotto=nomeProdotto+"(id:"+idProdotto+") ";
            String azione="";
            switch(tipo){
                case "C":
                    azione="ha creato ";
                    break;
                case "U":
                    azione="ha modificato ";
                    break;
                case "D":
                    azione="ha eliminato ";
                    break;
            }
            String frase="["+ora+"] "+aggiornamento+utente+azione+prodotto;
            if(tipo.equals("U"))
                frase=frase+"(Attributo modificato "+attributo+" vecchio valore:"+vecchioValore+" nuovo valore:"+nuovoValore+")";
            fout.println(frase);
            fout.close();
            f.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
