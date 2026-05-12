package org.aziendaagricola.controller;

import org.aziendaagricola.DTO.RaccoltoCreateDTO;
import org.aziendaagricola.record.ErroreResponse;
import org.aziendaagricola.service.ProssimoRaccoltoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/raccolto")
public class ProssimoRaccoltoController {
    @Autowired
    private ProssimoRaccoltoService proxRaccoltoService;
    @PostMapping()
    public ResponseEntity<Object> aggiungiRaccolto(@RequestBody RaccoltoCreateDTO dto){
        if (dto.getIdUtente() == null) {
            ErroreResponse body=new ErroreResponse("Id utente mancante");
            return ResponseEntity.status(400).body(body);
        }
        if(!dto.isValido()){
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
        if(!proxRaccoltoService.isAdmin((dto.getIdUtente()))){
            ErroreResponse body=new ErroreResponse("Non sei admin");
            return ResponseEntity.status(403).body(body);
        }
        int idRaccolto=proxRaccoltoService.aggiungiRaccolto(dto);
        if(idRaccolto>=0){
            proxRaccoltoService.scriviLog(idRaccolto,dto.getIdUtente(),dto.getNome());
            ErroreResponse body=new ErroreResponse("Aggiunto nuovo raccolto");
            return ResponseEntity.status(204).body(body);
        }
        else{
            ErroreResponse body=new ErroreResponse("Dati non validi");
            return ResponseEntity.status(400).body(body);
        }
    }
}
