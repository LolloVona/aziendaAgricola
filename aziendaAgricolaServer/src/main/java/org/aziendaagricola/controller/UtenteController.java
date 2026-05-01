package org.aziendaagricola.controller;
import org.aziendaagricola.DTO.UtenteAccediDTO;
import org.aziendaagricola.DTO.UtenteCreateDTO;
import org.aziendaagricola.record.Errore;
import org.aziendaagricola.record.utente.RegistrazioneResponse;
import org.aziendaagricola.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/utente")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registra")
    private ResponseEntity<Object> registra(@RequestBody UtenteCreateDTO dto){
        if(dto.convalidaDati()) {
            if (utenteService.salva(dto)){
                int id= utenteService.getId(dto);
                RegistrazioneResponse body=new RegistrazioneResponse(id,"Utente creato");
                return ResponseEntity.status(201).body(body);//operazione riuscita
            }
            else{
                Errore body=new Errore("Utente già presente");
                return ResponseEntity.status(409).body(body);//errore user già presente
            }


        }
        else{
            Errore body=new Errore("Dati errati");
            return ResponseEntity.status(400).body(body);//errore inserimento dati
        }
    }

    @PostMapping("/accedi")
    private ResponseEntity<Object> accedi(@RequestBody UtenteAccediDTO dto){
        if(dto.convalidaDati()){
            int id= utenteService.credenzialiCorrette(dto);
            if(id!=-1){
                RegistrazioneResponse body=new RegistrazioneResponse(id,"Accesso effettuato");
                return ResponseEntity.status(201).body(body);
            }
            else{
                Errore body=new Errore("Credenziali sbagliate");
                return ResponseEntity.status(401).body(body);
            }


        }else{
            Errore body=new Errore("Dati errati");
            return ResponseEntity.status(400).body(body);//errore inserimento dati
        }

    }


}

