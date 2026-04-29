package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
}
