package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Acquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface AcquistoRepository extends JpaRepository<Acquisto, Integer> {
    ArrayList<Acquisto> findByDataErogazioneAfter(LocalDate data);
}
