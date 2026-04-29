package org.aziendaagricola.repository;

import org.aziendaagricola.entita.IdRelativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Relativo extends JpaRepository<Relativo, IdRelativo> {
}
