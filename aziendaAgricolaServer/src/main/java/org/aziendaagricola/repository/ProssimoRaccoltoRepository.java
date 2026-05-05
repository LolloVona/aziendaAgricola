package org.aziendaagricola.repository;

import org.aziendaagricola.entita.Prodotto;
import org.aziendaagricola.entita.ProssimoRaccolto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProssimoRaccoltoRepository extends JpaRepository<ProssimoRaccolto, Integer> {
    boolean existsByNome(String nome);

    int getIdByNome(String nome);

    @Query("SELECT SUM(r.disponibilita) FROM ProssimoRaccolto r WHERE r.prodotto.idProdotto = :idProdotto")
    Float getSommaDisponibilitaById(@Param("idProdotto") int idProdotto);

    List<ProssimoRaccolto> findByProdottoIdOrderByDataAsc(int prodottoId);

}
