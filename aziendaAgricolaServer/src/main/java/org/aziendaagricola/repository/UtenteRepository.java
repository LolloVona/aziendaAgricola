package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Integer> {
    boolean existsByUsername(String username);

    Utente getUtenteByUsername(String username);
}
