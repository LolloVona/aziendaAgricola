package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Acquisto;
import org.aziendaagricola.entita.IdRelativo;
import org.aziendaagricola.entita.Relativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelativoRePository extends JpaRepository<Relativo, IdRelativo> {
}
