package org.aziendaagricola.controller;
import org.aziendaagricola.DTO.UtenteAccediDTO;
import org.aziendaagricola.DTO.UtenteCreateDTO;
import org.aziendaagricola.record.ErroreResponse;
import org.aziendaagricola.record.utente.RegistrazioneResponse;
import org.aziendaagricola.record.utente.TipoResponse;
import org.aziendaagricola.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/utente")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registrazione")
    private ResponseEntity<Object> registra(@RequestBody UtenteCreateDTO dto){
        if(dto.convalidaDati()) {
            if (utenteService.salva(dto)){
                int id= utenteService.getId(dto);
                utenteService.scriviLog(id, "registrazione");
                RegistrazioneResponse body=new RegistrazioneResponse(id,"Utente creato");
                return ResponseEntity.status(201).body(body);//operazione riuscita
            }
            else{
                ErroreResponse body=new ErroreResponse("Utente già presente");
                return ResponseEntity.status(409).body(body);//errore user già presente
            }


        }
        else{
            ErroreResponse body=new ErroreResponse("Dati errati");
            return ResponseEntity.status(400).body(body);//errore inserimento dati
        }
    }

    @PostMapping("/accesso")
    private ResponseEntity<Object> accedi(@RequestBody UtenteAccediDTO dto){
        if(dto.convalidaDati()){
            int id= utenteService.credenzialiCorrette(dto);
            if(id!=-1){
                utenteService.scriviLog(id, "accesso");
                RegistrazioneResponse body=new RegistrazioneResponse(id,"Accesso effettuato");
                return ResponseEntity.status(201).body(body);
            }
            else{
                ErroreResponse body=new ErroreResponse("Credenziali sbagliate");
                return ResponseEntity.status(401).body(body);
            }


        }else{
            ErroreResponse body=new ErroreResponse("Dati errati");
            return ResponseEntity.status(400).body(body);//errore inserimento dati
        }

    }
    @GetMapping("/tipo/{idUtente}")
    private ResponseEntity<Object> tipo(@PathVariable("idUtente") Integer idUtente){
        if(idUtente==null){
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        String tipo=utenteService.getTipo(idUtente);
        if(tipo==null){
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
        TipoResponse body=new TipoResponse(tipo);
        return ResponseEntity.status(200).body(body);
    }


}

