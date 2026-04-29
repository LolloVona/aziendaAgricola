package org.aziendaagricola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProssimoRaccolto extends JpaRepository<ProssimoRaccolto, Integer> {
}
