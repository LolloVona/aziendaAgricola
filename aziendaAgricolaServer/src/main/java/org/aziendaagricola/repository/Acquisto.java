package org.aziendaagricola.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Acquisto extends JpaRepository<Acquisto, Integer> {
}
