package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottoRepository extends JpaRepository<Prodotto, Integer> {
    boolean existsByNome(String nome);

    void deleteByNome(String nome);
}
