package org.aziendaagricola.repository;

import org.aziendaagricola.entita.ProssimoRaccolto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProssimoRaccoltoRepository extends JpaRepository<ProssimoRaccolto, Integer> {
}
