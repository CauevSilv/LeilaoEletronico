package com.caue.lpII.repository;

import com.caue.lpII.entity.Leilao;
import com.caue.lpII.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

    List<Lote> findByTipo(String tipo);

    List<Lote> findByLanceInicialBetween(Double min, Double max);

    List<Lote> findByNomeContainingIgnoreCase(String keyword);

    @Query(value = "SELECT * from Lote l where l.ID_LEILAO = :leilaoId", nativeQuery = true)
    List<Lote> findAllByLeilaoId(@Param("leilaoId")Integer leilaoId);

}

