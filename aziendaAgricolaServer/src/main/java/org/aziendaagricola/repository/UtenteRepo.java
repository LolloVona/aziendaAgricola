package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtenteRepo extends JpaRepository<Utente, Integer> {
}
