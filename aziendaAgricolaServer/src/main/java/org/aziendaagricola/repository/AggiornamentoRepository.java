package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Aggiornamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AggiornamentoRepository extends JpaRepository<Aggiornamento, Integer> {

}
