package org.aziendaagricola.repository;

import org.aziendaagricola.entita.IdRelativo;
import org.aziendaagricola.entita.Relativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RelativoRepository extends JpaRepository<Relativo, IdRelativo> {
}
