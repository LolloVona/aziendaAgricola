package org.aziendaagricola.repository;

import org.aziendaagricola.entita.ProssimoRaccolto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProssimoRaccoltoRepository extends JpaRepository<ProssimoRaccolto, Integer> {
    boolean existsByProdottoNome(String nome);

    int getIdByProdottoNome(String nome);

    @Query("SELECT SUM(r.disponibilita) FROM ProssimoRaccolto r WHERE r.prodotto.idProdotto = :idProdotto")
    Float getSommaDisponibilitaById(@Param("idProdotto") int idProdotto);

    List<ProssimoRaccolto> findByProdottoIdProdottoOrderByDataAsc(int idProdotto);

    List<ProssimoRaccolto> findByDataLessThanEqual(LocalDate data);

    @Query("SELECT p FROM ProssimoRaccolto p WHERE p.id_raccolto = :id")
    ProssimoRaccolto findById_raccolto(@Param("id") int id);
}
